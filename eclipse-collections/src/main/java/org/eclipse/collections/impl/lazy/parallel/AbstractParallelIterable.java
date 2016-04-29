/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.ParallelIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MapIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions2;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.procedure.CollectionAddProcedure;
import org.eclipse.collections.impl.block.procedure.DoubleSumResultHolder;
import org.eclipse.collections.impl.block.procedure.MapCollectProcedure;
import org.eclipse.collections.impl.block.procedure.MutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.NonMutatingAggregationProcedure;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure2;
import org.eclipse.collections.impl.list.mutable.CompositeFastList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMapUnsafe;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.set.mutable.SetAdapter;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

@Beta
public abstract class AbstractParallelIterable<T, B extends Batch<T>> implements ParallelIterable<T>
{
    protected static <T> void forEach(final AbstractParallelIterable<T, ? extends RootBatch<T>> parallelIterable, final Procedure<? super T> procedure)
    {
        LazyIterable<Future<?>> futures = parallelIterable.split().collect(new Function<RootBatch<T>, Future<?>>()
        {
            public Future<?> valueOf(final RootBatch<T> chunk)
            {
                return parallelIterable.getExecutorService().submit(new Runnable()
                {
                    public void run()
                    {
                        chunk.forEach(procedure);
                    }
                });
            }
        });
        // The call to toList() is important to stop the lazy evaluation and force all the Runnables to start executing.
        MutableList<Future<?>> futuresList = futures.toList();
        for (Future<?> future : futuresList)
        {
            try
            {
                future.get();
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            catch (ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    protected static <T> boolean anySatisfy(AbstractParallelIterable<T, ? extends RootBatch<T>> parallelIterable, final Predicate<? super T> predicate)
    {
        final CompletionService<Boolean> completionService = new ExecutorCompletionService<Boolean>(parallelIterable.getExecutorService());
        MutableSet<Future<Boolean>> futures = parallelIterable.split().collect(new Function<RootBatch<T>, Future<Boolean>>()
        {
            public Future<Boolean> valueOf(final RootBatch<T> batch)
            {
                return completionService.submit(new Callable<Boolean>()
                {
                    public Boolean call()
                    {
                        return batch.anySatisfy(predicate);
                    }
                });
            }
        }, UnifiedSet.<Future<Boolean>>newSet());

        while (futures.notEmpty())
        {
            try
            {
                Future<Boolean> future = completionService.take();
                if (future.get())
                {
                    for (Future<Boolean> eachFuture : futures)
                    {
                        eachFuture.cancel(true);
                    }
                    return true;
                }
                futures.remove(future);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            catch (ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    protected static <T> boolean allSatisfy(AbstractParallelIterable<T, ? extends RootBatch<T>> parallelIterable, final Predicate<? super T> predicate)
    {
        final CompletionService<Boolean> completionService = new ExecutorCompletionService<Boolean>(parallelIterable.getExecutorService());
        MutableSet<Future<Boolean>> futures = parallelIterable.split().collect(new Function<RootBatch<T>, Future<Boolean>>()
        {
            public Future<Boolean> valueOf(final RootBatch<T> batch)
            {
                return completionService.submit(new Callable<Boolean>()
                {
                    public Boolean call()
                    {
                        return batch.allSatisfy(predicate);
                    }
                });
            }
        }, UnifiedSet.<Future<Boolean>>newSet());

        while (futures.notEmpty())
        {
            try
            {
                Future<Boolean> future = completionService.take();
                if (!future.get())
                {
                    for (Future<Boolean> eachFuture : futures)
                    {
                        eachFuture.cancel(true);
                    }
                    return false;
                }
                futures.remove(future);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            catch (ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    protected static <T> T detect(final AbstractParallelIterable<T, ? extends RootBatch<T>> parallelIterable, final Predicate<? super T> predicate)
    {
        LazyIterable<? extends RootBatch<T>> chunks = parallelIterable.split();
        LazyIterable<Future<T>> futures = chunks.collect(new Function<RootBatch<T>, Future<T>>()
        {
            public Future<T> valueOf(final RootBatch<T> chunk)
            {
                return parallelIterable.getExecutorService().submit(new Callable<T>()
                {
                    public T call()
                    {
                        return chunk.detect(predicate);
                    }
                });
            }
        });
        // The call to toList() is important to stop the lazy evaluation and force all the Runnables to start executing.
        MutableList<Future<T>> futuresList = futures.toList();
        for (Future<T> future : futuresList)
        {
            try
            {
                T eachResult = future.get();
                if (eachResult != null)
                {
                    for (Future<T> eachFutureToCancel : futuresList)
                    {
                        eachFutureToCancel.cancel(true);
                    }
                    return eachResult;
                }
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            catch (ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public abstract ExecutorService getExecutorService();

    public abstract int getBatchSize();

    public abstract LazyIterable<B> split();

    protected abstract boolean isOrdered();

    protected <S, V> void collectCombine(Function<Batch<T>, V> function, Procedure2<S, V> combineProcedure, S state)
    {
        if (this.isOrdered())
        {
            this.collectCombineOrdered(function, combineProcedure, state);
        }
        else
        {
            this.collectCombineUnordered(function, combineProcedure, state);
        }
    }

    private <S, V> void collectCombineOrdered(final Function<Batch<T>, V> function, Procedure2<S, V> combineProcedure, S state)
    {
        LazyIterable<? extends Batch<T>> chunks = this.split();
        LazyIterable<Future<V>> futures = chunks.collect(new Function<Batch<T>, Future<V>>()
        {
            public Future<V> valueOf(final Batch<T> chunk)
            {
                return AbstractParallelIterable.this.getExecutorService().submit(new Callable<V>()
                {
                    public V call()
                    {
                        return function.valueOf(chunk);
                    }
                });
            }
        });
        // The call to toList() is important to stop the lazy evaluation and force all the Runnables to start executing.
        MutableList<Future<V>> futuresList = futures.toList();
        for (Future<V> future : futuresList)
        {
            try
            {
                combineProcedure.value(state, future.get());
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            catch (ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private <S, V> void collectCombineUnordered(final Function<Batch<T>, V> function, Procedure2<S, V> combineProcedure, S state)
    {
        LazyIterable<? extends Batch<T>> chunks = this.split();
        MutableList<Callable<V>> callables = chunks.collect(new Function<Batch<T>, Callable<V>>()
        {
            public Callable<V> valueOf(final Batch<T> chunk)
            {
                return new Callable<V>()
                {
                    public V call()
                    {
                        return function.valueOf(chunk);
                    }
                };
            }
        }).toList();

        final ExecutorCompletionService<V> completionService = new ExecutorCompletionService<V>(this.getExecutorService());
        callables.each(completionService::submit);

        int numTasks = callables.size();
        while (numTasks > 0)
        {
            try
            {
                Future<V> future = completionService.take();
                combineProcedure.value(state, future.get());
                numTasks--;
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            catch (ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private T collectReduce(Function<Batch<T>, T> map, Function2<T, T, T> function2)
    {
        return this.isOrdered()
                ? this.collectReduceOrdered(map, function2)
                : this.collectReduceUnordered(map, function2);
    }

    private T collectReduceOrdered(final Function<Batch<T>, T> map, Function2<T, T, T> function2)
    {
        LazyIterable<? extends Batch<T>> chunks = this.split();
        LazyIterable<Future<T>> futures = chunks.collect(new Function<Batch<T>, Future<T>>()
        {
            public Future<T> valueOf(final Batch<T> chunk)
            {
                return AbstractParallelIterable.this.getExecutorService().submit(new Callable<T>()
                {
                    public T call()
                    {
                        return map.valueOf(chunk);
                    }
                });
            }
        });
        // The call to toList() is important to stop the lazy evaluation and force all the Runnables to start executing.
        MutableList<Future<T>> futuresList = futures.toList();
        try
        {
            T result = futuresList.getFirst().get();
            for (int i = 1; i < futuresList.size(); i++)
            {
                T next = futuresList.get(i).get();
                if (next != null)
                {
                    if (result == null)
                    {
                        result = next;
                    }
                    else
                    {
                        result = function2.value(result, next);
                    }
                }
            }
            if (result == null)
            {
                throw new NoSuchElementException();
            }
            return result;
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        catch (ExecutionException e)
        {
            if (e.getCause() instanceof NullPointerException)
            {
                throw (NullPointerException) e.getCause();
            }
            throw new RuntimeException(e);
        }
    }

    private T collectReduceUnordered(final Function<Batch<T>, T> map, Function2<T, T, T> function2)
    {
        LazyIterable<? extends Batch<T>> chunks = this.split();
        MutableList<Callable<T>> callables = chunks.collect(new Function<Batch<T>, Callable<T>>()
        {
            public Callable<T> valueOf(final Batch<T> chunk)
            {
                return new Callable<T>()
                {
                    public T call()
                    {
                        return map.valueOf(chunk);
                    }
                };
            }
        }).toList();

        final ExecutorCompletionService<T> completionService = new ExecutorCompletionService<T>(this.getExecutorService());
        callables.each(completionService::submit);

        try
        {
            T result = completionService.take().get();
            int numTasks = callables.size() - 1;
            while (numTasks > 0)
            {
                T next = completionService.take().get();
                if (next != null)
                {
                    if (result == null)
                    {
                        result = next;
                    }
                    else
                    {
                        result = function2.value(result, next);
                    }
                }
                numTasks--;
            }
            if (result == null)
            {
                throw new NoSuchElementException();
            }
            return result;
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        catch (ExecutionException e)
        {
            if (e.getCause() instanceof NullPointerException)
            {
                throw (NullPointerException) e.getCause();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString()
    {
        return this.makeString("[", ", ", "]");
    }

    public String makeString()
    {
        return this.makeString(", ");
    }

    public String makeString(String separator)
    {
        return this.makeString("", separator, "");
    }

    public String makeString(String start, String separator, String end)
    {
        Appendable stringBuilder = new StringBuilder();
        this.appendString(stringBuilder, start, separator, end);
        return stringBuilder.toString();
    }

    public void appendString(Appendable appendable)
    {
        this.appendString(appendable, ", ");
    }

    public void appendString(Appendable appendable, String separator)
    {
        this.appendString(appendable, "", separator, "");
    }

    public void appendString(final Appendable appendable, String start, final String separator, String end)
    {
        try
        {
            appendable.append(start);
            Function<Batch<T>, String> map = new Function<Batch<T>, String>()
            {
                public String valueOf(Batch<T> batch)
                {
                    return batch.makeString(separator);
                }
            };
            Procedure2<Appendable, String> reduce = new CheckedProcedure2<Appendable, String>()
            {
                private boolean first = true;

                public void safeValue(Appendable accumulator, String each) throws IOException
                {
                    if ("".equals(each))
                    {
                        return;
                    }
                    if (this.first)
                    {
                        this.first = false;
                    }
                    else
                    {
                        appendable.append(separator);
                    }
                    appendable.append(each);
                }
            };
            this.collectCombine(map, reduce, appendable);

            appendable.append(end);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public <P> void forEachWith(Procedure2<? super T, ? super P> procedure, P parameter)
    {
        this.forEach(Procedures.bind(procedure, parameter));
    }

    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.anySatisfy(Predicates.bind(predicate, parameter));
    }

    public <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.allSatisfy(Predicates.bind(predicate, parameter));
    }

    public boolean noneSatisfy(Predicate<? super T> predicate)
    {
        return this.allSatisfy(Predicates.not(predicate));
    }

    public <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.noneSatisfy(Predicates.bind(predicate, parameter));
    }

    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.detect(Predicates.bind(predicate, parameter));
    }

    public T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function)
    {
        T result = this.detect(predicate);
        return result == null ? function.value() : result;
    }

    public <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function)
    {
        return this.detectIfNone(Predicates.bind(predicate, parameter), function);
    }

    public Object[] toArray()
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".toArray() not implemented yet");
    }

    public <E> E[] toArray(E[] array)
    {
        throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".toArray() not implemented yet");
    }

    public MutableList<T> toList()
    {
        Function<Batch<T>, FastList<T>> map = new Function<Batch<T>, FastList<T>>()
        {
            public FastList<T> valueOf(Batch<T> batch)
            {
                FastList<T> list = FastList.newList();
                batch.forEach(CollectionAddProcedure.on(list));
                return list;
            }
        };
        MutableList<T> state = new CompositeFastList<T>();
        this.collectCombine(map, MutableList<T>::addAll, state);
        return state;
    }

    public MutableList<T> toSortedList()
    {
        return this.toList().toSortedList();
    }

    public MutableList<T> toSortedList(Comparator<? super T> comparator)
    {
        return this.toList().toSortedList(comparator);
    }

    public <V extends Comparable<? super V>> MutableList<T> toSortedListBy(Function<? super T, ? extends V> function)
    {
        return this.toSortedList(Comparators.byFunction(function));
    }

    public MutableSet<T> toSet()
    {
        ConcurrentHashMapUnsafe<T, Boolean> map = ConcurrentHashMapUnsafe.newMap();
        Set<T> result = Collections.newSetFromMap(map);
        this.forEach(CollectionAddProcedure.on(result));
        return SetAdapter.adapt(map.keySet());
    }

    public MutableSortedSet<T> toSortedSet()
    {
        MutableSortedSet<T> result = TreeSortedSet.<T>newSet().asSynchronized();
        this.forEach(CollectionAddProcedure.on(result));
        return result;
    }

    public <V extends Comparable<? super V>> MutableSortedSet<T> toSortedSetBy(Function<? super T, ? extends V> function)
    {
        return this.toSortedSet(Comparators.byFunction(function));
    }

    public MutableBag<T> toBag()
    {
        MutableBag<T> result = HashBag.<T>newBag().asSynchronized();
        this.forEach(CollectionAddProcedure.on(result));
        return result;
    }

    public MutableSortedBag<T> toSortedBag()
    {
        MutableSortedBag<T> result = TreeBag.<T>newBag().asSynchronized();
        this.forEach(CollectionAddProcedure.on(result));
        return result;
    }

    public MutableSortedBag<T> toSortedBag(Comparator<? super T> comparator)
    {
        MutableSortedBag<T> result = TreeBag.newBag(comparator).asSynchronized();
        this.forEach(CollectionAddProcedure.on(result));
        return result;
    }

    public <V extends Comparable<? super V>> MutableSortedBag<T> toSortedBagBy(Function<? super T, ? extends V> function)
    {
        return this.toSortedBag(Comparators.byFunction(function));
    }

    public MutableSortedSet<T> toSortedSet(Comparator<? super T> comparator)
    {
        MutableSortedSet<T> result = TreeSortedSet.newSet(comparator).asSynchronized();
        this.forEach(CollectionAddProcedure.on(result));
        return result;
    }

    public <NK, NV> MutableMap<NK, NV> toMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        MutableMap<NK, NV> map = UnifiedMap.<NK, NV>newMap().asSynchronized();
        this.forEach(new MapCollectProcedure<T, NK, NV>(map, keyFunction, valueFunction));
        return map;
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        MutableSortedMap<NK, NV> sortedMap = TreeSortedMap.<NK, NV>newMap().asSynchronized();
        this.forEach(new MapCollectProcedure<T, NK, NV>(sortedMap, keyFunction, valueFunction));
        return sortedMap;
    }

    public <NK, NV> MutableSortedMap<NK, NV> toSortedMap(Comparator<? super NK> comparator,
            Function<? super T, ? extends NK> keyFunction,
            Function<? super T, ? extends NV> valueFunction)
    {
        MutableSortedMap<NK, NV> sortedMap = TreeSortedMap.<NK, NV>newMap(comparator).asSynchronized();
        this.forEach(new MapCollectProcedure<T, NK, NV>(sortedMap, keyFunction, valueFunction));
        return sortedMap;
    }

    public <K, V> MapIterable<K, V> aggregateBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Function2<? super V, ? super T, ? extends V> nonMutatingAggregator)
    {
        MutableMap<K, V> map = ConcurrentHashMapUnsafe.newMap();
        this.forEach(new NonMutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, nonMutatingAggregator));
        return map;
    }

    public <K, V> MapIterable<K, V> aggregateInPlaceBy(
            Function<? super T, ? extends K> groupBy,
            Function0<? extends V> zeroValueFactory,
            Procedure2<? super V, ? super T> mutatingAggregator)
    {
        MutableMap<K, V> map = ConcurrentHashMapUnsafe.newMap();
        this.forEach(new MutatingAggregationProcedure<T, K, V>(map, groupBy, zeroValueFactory, mutatingAggregator));
        return map;
    }

    public int count(final Predicate<? super T> predicate)
    {
        Function<Batch<T>, Integer> map = new Function<Batch<T>, Integer>()
        {
            public Integer valueOf(Batch<T> batch)
            {
                return batch.count(predicate);
            }
        };

        Counter state = new Counter();
        this.collectCombineUnordered(map, Counter::add, state);
        return state.getCount();
    }

    public <P> int countWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.count(Predicates.bind(predicate, parameter));
    }

    public T min(final Comparator<? super T> comparator)
    {
        Function<Batch<T>, T> map = new Function<Batch<T>, T>()
        {
            public T valueOf(Batch<T> batch)
            {
                return batch.min(comparator);
            }
        };
        return this.collectReduce(map, Functions2.min(comparator));
    }

    public T max(final Comparator<? super T> comparator)
    {
        Function<Batch<T>, T> map = new Function<Batch<T>, T>()
        {
            public T valueOf(Batch<T> batch)
            {
                return batch.max(comparator);
            }
        };
        return this.collectReduce(map, Functions2.max(comparator));
    }

    public T min()
    {
        return this.min(Comparators.naturalOrder());
    }

    public T max()
    {
        return this.max(Comparators.naturalOrder());
    }

    public <V extends Comparable<? super V>> T minBy(final Function<? super T, ? extends V> function)
    {
        Function<Batch<T>, T> map = new Function<Batch<T>, T>()
        {
            public T valueOf(Batch<T> batch)
            {
                return batch.minBy(function);
            }
        };
        return this.collectReduce(map, Functions2.minBy(function));
    }

    public <V extends Comparable<? super V>> T maxBy(final Function<? super T, ? extends V> function)
    {
        Function<Batch<T>, T> map = new Function<Batch<T>, T>()
        {
            public T valueOf(Batch<T> batch)
            {
                return batch.maxBy(function);
            }
        };
        return this.collectReduce(map, Functions2.maxBy(function));
    }

    public long sumOfInt(final IntFunction<? super T> function)
    {
        LongFunction<Batch<T>> map = new LongFunction<Batch<T>>()
        {
            public long longValueOf(Batch<T> batch)
            {
                return batch.sumOfInt(function);
            }
        };
        return this.sumOfLongOrdered(map);
    }

    public double sumOfFloat(final FloatFunction<? super T> function)
    {
        Function<Batch<T>, DoubleSumResultHolder> map = new Function<Batch<T>, DoubleSumResultHolder>()
        {
            public DoubleSumResultHolder valueOf(Batch<T> batch)
            {
                return batch.sumOfFloat(function);
            }
        };
        return this.sumOfDoubleOrdered(map);
    }

    public long sumOfLong(final LongFunction<? super T> function)
    {
        LongFunction<Batch<T>> map = new LongFunction<Batch<T>>()
        {
            public long longValueOf(Batch<T> batch)
            {
                return batch.sumOfLong(function);
            }
        };
        return this.sumOfLongOrdered(map);
    }

    public double sumOfDouble(final DoubleFunction<? super T> function)
    {
        Function<Batch<T>, DoubleSumResultHolder> map = new Function<Batch<T>, DoubleSumResultHolder>()
        {
            public DoubleSumResultHolder valueOf(Batch<T> batch)
            {
                return batch.sumOfDouble(function);
            }
        };
        return this.sumOfDoubleOrdered(map);
    }

    private long sumOfLongOrdered(final LongFunction<Batch<T>> map)
    {
        LazyIterable<? extends Batch<T>> chunks = this.split();
        LazyIterable<Future<Long>> futures = chunks.collect(new Function<Batch<T>, Future<Long>>()
        {
            public Future<Long> valueOf(final Batch<T> chunk)
            {
                return AbstractParallelIterable.this.getExecutorService().submit(new Callable<Long>()
                {
                    public Long call()
                    {
                        return map.longValueOf(chunk);
                    }
                });
            }
        });
        // The call to toList() is important to stop the lazy evaluation and force all the Runnables to start executing.
        MutableList<Future<Long>> futuresList = futures.toList();
        try
        {
            long result = 0;
            for (int i = 0; i < futuresList.size(); i++)
            {
                result += futuresList.get(i).get();
            }
            return result;
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        catch (ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }

    private double sumOfDoubleOrdered(final Function<Batch<T>, DoubleSumResultHolder> map)
    {
        LazyIterable<? extends Batch<T>> chunks = this.split();
        LazyIterable<Future<DoubleSumResultHolder>> futures = chunks.collect(new Function<Batch<T>, Future<DoubleSumResultHolder>>()
        {
            public Future<DoubleSumResultHolder> valueOf(final Batch<T> chunk)
            {
                return AbstractParallelIterable.this.getExecutorService().submit(new Callable<DoubleSumResultHolder>()
                {
                    public DoubleSumResultHolder call()
                    {
                        return map.valueOf(chunk);
                    }
                });
            }
        });
        // The call to toList() is important to stop the lazy evaluation and force all the Runnables to start executing.
        MutableList<Future<DoubleSumResultHolder>> futuresList = futures.toList();
        try
        {
            double sum = 0.0d;
            double compensation = 0.0d;
            for (int i = 0; i < futuresList.size(); i++)
            {
                compensation += futuresList.get(i).get().getCompensation();
                double adjustedValue = futuresList.get(i).get().getResult() - compensation;
                double nextSum = sum + adjustedValue;
                compensation = nextSum - sum - adjustedValue;
                sum = nextSum;
            }
            return sum;
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        catch (ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }

    public <V> MapIterable<V, T> groupByUniqueKey(final Function<? super T, ? extends V> function)
    {
        final MutableMap<V, T> result = ConcurrentHashMap.newMap();
        this.forEach(new Procedure<T>()
        {
            public void value(T value)
            {
                V key = function.valueOf(value);
                if (result.put(key, value) != null)
                {
                    throw new IllegalStateException("Key " + key + " already exists in map!");
                }
            }
        });
        return result;
    }
}
