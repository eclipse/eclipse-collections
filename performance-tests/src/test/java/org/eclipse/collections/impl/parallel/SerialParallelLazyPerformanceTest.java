/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.IntProcedure;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.ParallelTests;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.Iterate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("Convert2MethodRef")
public class SerialParallelLazyPerformanceTest
{
    public static final MutableList<Predicate<Integer>> PREDICATES_LAMBDA = FastList.newListWith(
            item -> item > 0 && (item & 1) != 0,
            item -> item > 0 && (item & 1) == 0,
            item -> item < 0 && (item & 1) != 0,
            item -> item > 1000000);

    public static final MutableList<Predicate<Integer>> PREDICATES_METHOD_REF = FastList.newListWith(
            SerialParallelLazyPerformanceTest::isPositiveAndEven,
            SerialParallelLazyPerformanceTest::isPositiveAndEven,
            SerialParallelLazyPerformanceTest::isNegativeAndOdd,
            SerialParallelLazyPerformanceTest::isGreaterThanMillion);

    public static final MutableList<java.util.function.Predicate<Integer>> JAVA_PREDICATES_LAMBDA = FastList.newListWith(
            item -> item > 0 && (item & 1) != 0,
            item -> item > 0 && (item & 1) == 0,
            item -> item < 0 && (item & 1) != 0,
            item -> item > 1000000);

    public static final MutableList<java.util.function.Predicate<Integer>> JAVA_PREDICATES_METHOD_REF = FastList.newListWith(
            SerialParallelLazyPerformanceTest::isPositiveAndEven,
            SerialParallelLazyPerformanceTest::isPositiveAndEven,
            SerialParallelLazyPerformanceTest::isNegativeAndOdd,
            SerialParallelLazyPerformanceTest::isGreaterThanMillion);

    public static final Predicate<Integer> PREDICATE_1 = IntegerPredicates.isPositive().and(IntegerPredicates.isOdd());
    public static final Predicate<Integer> PREDICATE_2 = IntegerPredicates.isPositive().and(IntegerPredicates.isEven());
    public static final Predicate<Integer> PREDICATE_3 = IntegerPredicates.isNegative().and(IntegerPredicates.isOdd());
    public static final Predicate<Integer> PREDICATE_4 = Predicates.greaterThan(1000000);
    public static final MutableList<Predicate<Integer>> PREDICATES = FastList.newListWith(PREDICATE_1, PREDICATE_2, PREDICATE_3, PREDICATE_4);

    public static final java.util.function.Predicate<Integer> JAVA_PREDICATE_1 = item -> item > 0 && (item & 1) != 0;
    public static final java.util.function.Predicate<Integer> JAVA_PREDICATE_2 = item -> item > 0 && (item & 1) == 0;
    public static final java.util.function.Predicate<Integer> JAVA_PREDICATE_3 = item -> item < 0 && (item & 1) != 0;
    public static final java.util.function.Predicate<Integer> JAVA_PREDICATE_4 = item -> item > 1000000;
    public static final MutableList<java.util.function.Predicate<Integer>> JAVA_PREDICATES = FastList.newListWith(JAVA_PREDICATE_1, JAVA_PREDICATE_2, JAVA_PREDICATE_3, JAVA_PREDICATE_4);

    public static final Function<Integer, ?> LONG_FUNCTION = value -> value.longValue();

    public static final Function<Integer, ?> SHORT_FUNCTION = value -> value.shortValue();

    public static final java.util.function.Function<Integer, ?> JAVA_LONG_FUNCTION = value -> value.longValue();

    public static final java.util.function.Function<Integer, ?> JAVA_SHORT_FUNCTION = value -> value.shortValue();

    public static final MutableList<Function<Integer, ?>> FUNCTIONS = FastList.newListWith(LONG_FUNCTION, SHORT_FUNCTION);
    public static final MutableList<java.util.function.Function<Integer, ?>> JAVA_FUNCTIONS = FastList.newListWith(JAVA_LONG_FUNCTION, JAVA_SHORT_FUNCTION);

    public static final MutableList<Function<Integer, ?>> FUNCTIONS_LAMBDA = FastList.newListWith(
            value -> value.longValue(),
            value -> value.shortValue());
    public static final MutableList<java.util.function.Function<Integer, ?>> JAVA_FUNCTIONS_LAMBDA = FastList.newListWith(
            value -> value.longValue(),
            value -> value.shortValue());
    public static final MutableList<Function<Integer, ?>> FUNCTIONS_METHOD_REF = FastList.newListWith(
            Integer::longValue,
            Integer::shortValue);
    public static final MutableList<java.util.function.Function<Integer, ?>> JAVA_FUNCTIONS_METHOD_REF = FastList.newListWith(
            Integer::longValue,
            Integer::shortValue);

    private static final Function<String, Alphagram> ALPHAGRAM_LAMBDA = value -> new Alphagram(value);
    private static final java.util.function.Function<String, Alphagram> JAVA_ALPHAGRAM_LAMBDA = value -> new Alphagram(value);
    private static final Function<String, Alphagram> ALPHAGRAM_FUNCTION = (Function<String, Alphagram>) each -> new Alphagram(each);
    private static final java.util.function.Function<String, Alphagram> JAVA_ALPHAGRAM_FUNCTION = each -> new Alphagram(each);
    private static final Function<String, Alphagram> ALPHAGRAM_METHOD_REF = Alphagram::new;
    private static final java.util.function.Function<String, Alphagram> JAVA_ALPHAGRAM_METHOD_REF = Alphagram::new;

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialParallelLazyPerformanceTest.class);

    private static final int SCALE_FACTOR = Integer.parseInt(System.getProperty("scaleFactor", "100"));

    private static final int WARM_UP_COUNT = Integer.parseInt(System.getProperty("WarmupCount", "100"));
    private static final int PARALLEL_RUN_COUNT = Integer.parseInt(System.getProperty("ParallelRunCount", "200"));
    private static final int SERIAL_RUN_COUNT = Integer.parseInt(System.getProperty("SerialRunCount", "200"));

    private static final int SMALL_COUNT = 100 * SCALE_FACTOR;
    private static final int MEDIUM_COUNT = 1000 * SCALE_FACTOR;
    private static final int LARGE_COUNT = 10000 * SCALE_FACTOR;

    private static boolean isPositiveAndEven(Integer item)
    {
        return item > 0 && (item & 1) == 0;
    }

    private static boolean isNegativeAndOdd(Integer item)
    {
        return item < 0 && (item & 1) != 0;
    }

    private static boolean isGreaterThanMillion(Integer item)
    {
        return item > 1000000;
    }

    @Before
    public void setup()
    {
        Procedure<java.util.function.Function<Integer, ?>> applyConsumer = each -> each.apply(0);

        JAVA_FUNCTIONS.forEach(applyConsumer);
        JAVA_FUNCTIONS_LAMBDA.forEach(applyConsumer);
        JAVA_FUNCTIONS_METHOD_REF.forEach(applyConsumer);

        JAVA_ALPHAGRAM_FUNCTION.apply("test");
        JAVA_ALPHAGRAM_LAMBDA.apply("test");
        JAVA_ALPHAGRAM_METHOD_REF.apply("test");

        Procedure<java.util.function.Predicate<Integer>> applyPredicate = each -> each.test(0);

        JAVA_PREDICATES.forEach(applyPredicate);
        JAVA_PREDICATES_LAMBDA.forEach(applyPredicate);
        JAVA_PREDICATES_METHOD_REF.forEach(applyPredicate);
    }

    @Test
    @Category(ParallelTests.class)
    public void forEach()
    {
        this.measureAlgorithmForIntegerIterable("forEach", each -> this.forEach(each.value()), true);
    }

    @Test
    @Category(ParallelTests.class)
    public void toList()
    {
        this.measureAlgorithmForIntegerIterable("toList", each -> this.toList(each.value()), true);
    }

    @Test
    @Category(ParallelTests.class)
    public void select()
    {
        this.measureAlgorithmForIntegerIterable("Select", each -> this.select(each.value()), true);
    }

    @Test
    @Category(ParallelTests.class)
    public void reject()
    {
        this.measureAlgorithmForIntegerIterable("Reject", each -> this.reject(each.value()), true);
    }

    @Test
    @Category(ParallelTests.class)
    public void anySatisfyShortCircuitInBeginning()
    {
        this.measureAlgorithmForIntegerIterable("AnySatisfy Short Circuit In The Beginning", each -> this.anySatisfy(each.value(), 2, true), false);
    }

    @Test
    @Category(ParallelTests.class)
    public void anySatisfyShortCircuitInMiddle()
    {
        this.measureAlgorithmForIntegerIterable(
                "AnySatisfy Short Circuit In The Middle",
                each -> this.anySatisfy(each.value(), 0, true),
                false);
    }

    @Test
    @Category(ParallelTests.class)
    public void anySatisfyShortCircuitInEnd()
    {
        this.measureAlgorithmForIntegerIterable(
                "AnySatisfy Short Circuit In The End",
                each -> this.anySatisfy(each.value(), 3, false),
                false);
    }

    @Test
    @Category(ParallelTests.class)
    public void detectShortCircuitInBeginning()
    {
        this.measureAlgorithmForIntegerIterable(
                "Detect Short Circuit In The Beginning",
                each -> this.detect(each.value(), 2, true),
                false);
    }

    @Test
    @Category(ParallelTests.class)
    public void detectShortCircuitInMiddle()
    {
        this.measureAlgorithmForIntegerIterable(
                "Detect Short Circuit In The Middle",
                each -> this.detect(each.value(), 0, true),
                false);
    }

    @Test
    @Category(ParallelTests.class)
    public void detectShortCircuitInEnd()
    {
        this.measureAlgorithmForIntegerIterable(
                "Detect Short Circuit In The End",
                each -> this.detect(each.value(), 3, false),
                false);
    }

    @Test
    @Category(ParallelTests.class)
    public void collect()
    {
        this.measureAlgorithmForIntegerIterable("Collect", each -> this.collect(each.value()), true);
    }

    @Test
    @Category(ParallelTests.class)
    public void groupBy()
    {
        this.measureAlgorithmForRandomStringIterable("GroupBy", each -> this.groupBy(each.value()));
    }

    @After
    public void tearDown()
    {
        SerialParallelLazyPerformanceTest.forceGC();
    }

    private static void forceGC()
    {
        IntInterval.oneTo(20).forEach((IntProcedure) each -> {
            System.gc();
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        });
    }

    public void printMachineAndTestConfiguration(String serialParallelAlgorithm)
    {
        LOGGER.info("*** Algorithm: {}", serialParallelAlgorithm);
        LOGGER.info("Available Processors: {}", Runtime.getRuntime().availableProcessors());
        LOGGER.info("Default Thread Pool Size: {}", ParallelIterate.getDefaultMaxThreadPoolSize());
        LOGGER.info("Default Task Count: {}", ParallelIterate.getDefaultTaskCount());
        LOGGER.info("Scale Factor: {}", SCALE_FACTOR);
        LOGGER.info("Warm up count: {}", WARM_UP_COUNT);
        LOGGER.info("Parallel Run Count: {}", PARALLEL_RUN_COUNT);
        LOGGER.info("Serial** Run Count: {}", SERIAL_RUN_COUNT);
    }

    private MutableList<Integer> getSizes()
    {
        MutableList<Integer> sizes = FastList.newListWith(LARGE_COUNT, MEDIUM_COUNT, SMALL_COUNT);
        return sizes.shuffleThis();
    }

    private MutableList<Function0<FastList<Integer>>> getIntegerListGenerators(int count, boolean shuffle)
    {
        Interval interval = Interval.fromTo(-(count / 2), count / 2 - 1);
        MutableList<Function0<FastList<Integer>>> generators = FastList.newList();
        generators.add(() -> {
            FastList<Integer> integers = FastList.newList(interval);
            if (shuffle)
            {
                integers.shuffleThis();
            }
            return integers;
        });
        return generators.shuffleThis();
    }

    private void measureAlgorithmForIntegerIterable(String algorithmName, Procedure<Function0<FastList<Integer>>> algorithm, boolean shuffle)
    {
        this.printMachineAndTestConfiguration(algorithmName);
        for (int i = 0; i < 4; i++)
        {
            this.getSizes().forEach(Procedures.cast(count -> this.getIntegerListGenerators(count, shuffle).forEach(algorithm)));
        }
    }

    private MutableList<Function0<UnifiedSet<String>>> getRandomWordsGenerators(int count)
    {
        MutableList<Function0<UnifiedSet<String>>> generators = FastList.newList();
        generators.add(() -> this.generateWordsList(count));
        return generators;
    }

    public UnifiedSet<String> generateWordsList(int count)
    {
        UnifiedSet<String> words = UnifiedSet.newSet();
        while (words.size() < count)
        {
            words.add(RandomStringUtils.randomAlphabetic(5));
        }
        return words;
    }

    private void measureAlgorithmForRandomStringIterable(String algorithmName, Procedure<Function0<UnifiedSet<String>>> algorithm)
    {
        this.printMachineAndTestConfiguration(algorithmName);
        for (int i = 0; i < 4; i++)
        {
            this.getSizes().forEach(Procedures.cast(count -> this.getRandomWordsGenerators(count).forEach(algorithm)));
        }
    }

    private void shuffleAndRun(MutableList<Runnable> runnables)
    {
        runnables.shuffleThis();
        runnables.forEach(Procedures.cast(Runnable::run));
    }

    private void forEach(FastList<Integer> collection)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialForEachPerformance(collection, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> {
            MutableMap<Integer, Boolean> map = new ConcurrentHashMap<>();
            this.basicParallelLazyForEachPerformance(collection, "Lambda", item -> map.put(item, Boolean.TRUE), PARALLEL_RUN_COUNT, cores, service);
        });
        runnables.add(() -> {
            MutableMap<Integer, Boolean> map = new ConcurrentHashMap<>();
            this.basicParallelLazyForEachPerformance(collection, "Procedure", (Procedure<Integer>) each -> map.put(each, Boolean.TRUE), PARALLEL_RUN_COUNT, cores, service);
        });
        runnables.add(() -> {
            MutableMap<Integer, Boolean> map = new ConcurrentHashMap<>();
            this.basicJava8ParallelLazyForEachPerformance(collection, "Lambda", item -> map.put(item, Boolean.TRUE), PARALLEL_RUN_COUNT);
        });
        List<Integer> arrayList = new ArrayList<>(collection);
        runnables.add(() -> {
            MutableMap<Integer, Boolean> map = new ConcurrentHashMap<>();
            this.basicJava8ParallelLazyForEachPerformance(arrayList, "Lambda", item -> map.put(item, Boolean.TRUE), PARALLEL_RUN_COUNT);
        });
        runnables.add(() -> {
            MutableMap<Integer, Boolean> map = new ConcurrentHashMap<>();
            this.basicJava8ParallelLazyForEachPerformance(arrayList, "Consumer", each -> map.put(each, Boolean.TRUE), PARALLEL_RUN_COUNT);
        });
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void toList(FastList<Integer> collection)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialToListPerformance(collection, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> this.basicParallelLazyToListPerformance(collection, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicJava8ParallelLazyToListPerformance(collection, PARALLEL_RUN_COUNT));
        List<Integer> arrayList = new ArrayList<>(collection);
        runnables.add(() -> this.basicJava8ParallelLazyToListPerformance(arrayList, PARALLEL_RUN_COUNT));
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void select(FastList<Integer> collection)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialSelectPerformance(collection, PREDICATES_LAMBDA, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> this.basicParallelLazySelectPerformance(collection, "Lambda", PREDICATES_LAMBDA, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazySelectPerformance(collection, "Predicate", PREDICATES, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazySelectPerformance(collection, "MethodRef", PREDICATES_METHOD_REF, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicJava8ParallelLazySelectPerformance(collection, "Lambda", JAVA_PREDICATES_LAMBDA, PARALLEL_RUN_COUNT));
        List<Integer> arrayList = new ArrayList<>(collection);
        runnables.add(() -> this.basicJava8ParallelLazySelectPerformance(arrayList, "Lambda", JAVA_PREDICATES_LAMBDA, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazySelectPerformance(arrayList, "Predicate", JAVA_PREDICATES, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazySelectPerformance(arrayList, "MethodRef", JAVA_PREDICATES_METHOD_REF, PARALLEL_RUN_COUNT));
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void reject(FastList<Integer> collection)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialRejectPerformance(collection, PREDICATES_LAMBDA, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> this.basicParallelLazyRejectPerformance(collection, "Lambda", PREDICATES_LAMBDA, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyRejectPerformance(collection, "Predicate", PREDICATES, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyRejectPerformance(collection, "MethodRef", PREDICATES_METHOD_REF, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicJava8ParallelLazyRejectPerformance(collection, "Lambda", JAVA_PREDICATES_LAMBDA, PARALLEL_RUN_COUNT));
        List<Integer> arrayList = new ArrayList<>(collection);
        runnables.add(() -> this.basicJava8ParallelLazyRejectPerformance(arrayList, "Lambda", JAVA_PREDICATES_LAMBDA, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazyRejectPerformance(arrayList, "Predicate", JAVA_PREDICATES, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazyRejectPerformance(arrayList, "MethodRef", JAVA_PREDICATES_METHOD_REF, PARALLEL_RUN_COUNT));
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void anySatisfy(FastList<Integer> collection, int index, boolean expectedResult)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialAnySatisfyPerformance(collection, PREDICATES_LAMBDA.get(index), expectedResult, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> this.basicParallelLazyAnySatisfyPerformance(collection, PREDICATES_LAMBDA.get(index), "Lambda", expectedResult, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyAnySatisfyPerformance(collection, PREDICATES.get(index), "Predicate", expectedResult, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyAnySatisfyPerformance(collection, PREDICATES_METHOD_REF.get(index), "MethodRef", expectedResult, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicJava8ParallelLazyAnySatisfyPerformance(collection, "Lambda", JAVA_PREDICATES_LAMBDA.get(index), expectedResult, PARALLEL_RUN_COUNT));
        List<Integer> arrayList = new ArrayList<>(collection);
        runnables.add(() -> this.basicJava8ParallelLazyAnySatisfyPerformance(arrayList, "Lambda", JAVA_PREDICATES_LAMBDA.get(index), expectedResult, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazyAnySatisfyPerformance(arrayList, "Predicate", JAVA_PREDICATES.get(index), expectedResult, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazyAnySatisfyPerformance(arrayList, "MethodRef", JAVA_PREDICATES_METHOD_REF.get(index), expectedResult, PARALLEL_RUN_COUNT));
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void detect(FastList<Integer> collection, int index, boolean expectedResult)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialDetectPerformance(collection, PREDICATES_LAMBDA.get(index), expectedResult, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> this.basicParallelLazyDetectPerformance(collection, PREDICATES_LAMBDA.get(index), "Lambda", expectedResult, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyDetectPerformance(collection, PREDICATES.get(index), "Predicate", expectedResult, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyDetectPerformance(collection, PREDICATES_METHOD_REF.get(index), "MethodRef", expectedResult, PARALLEL_RUN_COUNT, cores, service));
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void collect(FastList<Integer> collection)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialCollectPerformance(collection, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> this.basicParallelLazyCollectPerformance(collection, "Lambda", FUNCTIONS_LAMBDA, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyCollectPerformance(collection, "Function", FUNCTIONS, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyCollectPerformance(collection, "MethodRef", FUNCTIONS_METHOD_REF, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicJava8ParallelLazyCollectPerformance(collection, "Lambda", JAVA_FUNCTIONS_LAMBDA, PARALLEL_RUN_COUNT));
        List<Integer> arrayList = new ArrayList<>(collection);
        runnables.add(() -> this.basicJava8ParallelLazyCollectPerformance(arrayList, "Lambda", JAVA_FUNCTIONS_LAMBDA, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazyCollectPerformance(arrayList, "Function", JAVA_FUNCTIONS, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicJava8ParallelLazyCollectPerformance(arrayList, "MethodRef", JAVA_FUNCTIONS_METHOD_REF, PARALLEL_RUN_COUNT));
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void groupBy(UnifiedSet<String> words)
    {
        MutableList<Runnable> runnables = FastList.newList();
        runnables.add(() -> this.basicSerialGroupByPerformance(words, SERIAL_RUN_COUNT));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        runnables.add(() -> this.basicParallelLazyGroupByPerformance(words, "Lambda", ALPHAGRAM_LAMBDA, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyGroupByPerformance(words, "Function", ALPHAGRAM_FUNCTION, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyGroupByPerformance(words, "MethodRef", ALPHAGRAM_METHOD_REF, PARALLEL_RUN_COUNT, cores, service));
        runnables.add(() -> this.basicParallelLazyJava8GroupByPerformance(words, "Lambda", JAVA_ALPHAGRAM_LAMBDA, PARALLEL_RUN_COUNT));
        Set<String> hashSet = new HashSet<>(words);
        runnables.add(() -> this.basicParallelLazyJava8GroupByPerformance(hashSet, "Lambda", JAVA_ALPHAGRAM_LAMBDA, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicParallelLazyJava8GroupByPerformance(hashSet, "Function", JAVA_ALPHAGRAM_FUNCTION, PARALLEL_RUN_COUNT));
        runnables.add(() -> this.basicParallelLazyJava8GroupByPerformance(hashSet, "MethodRef", JAVA_ALPHAGRAM_METHOD_REF, PARALLEL_RUN_COUNT));
        this.shuffleAndRun(runnables);
        service.shutdown();
        try
        {
            service.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private double basicSerialToListPerformance(
            FastList<Integer> iterable,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* toList: "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> Verify.assertNotEmpty(iterable.toList()), count, WARM_UP_COUNT);
    }

    private double basicSerialForEachPerformance(
            FastList<Integer> iterable,
            int count)
    {
        MutableMap<Integer, Boolean> map = new ConcurrentHashMap<>();
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* ForEach using: Lambda"
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> iterable.forEach((Procedure<Integer>) item -> map.put(item, Boolean.TRUE)), count, WARM_UP_COUNT);
    }

    private double basicSerialSelectPerformance(
            FastList<Integer> iterable,
            MutableList<Predicate<Integer>> predicateList,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* Select using: Lambda "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> {
            Verify.assertNotEmpty(iterable.select(predicateList.get(0)).toList());
            Verify.assertNotEmpty(iterable.select(predicateList.get(1)).toList());
            Verify.assertNotEmpty(iterable.select(predicateList.get(2)).toList());
        }, count, WARM_UP_COUNT);
    }

    private double basicSerialRejectPerformance(
            FastList<Integer> iterable,
            MutableList<Predicate<Integer>> predicateList,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* Reject using: Lambda "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> {
            Verify.assertNotEmpty(iterable.reject(predicateList.get(0)).toList());
            Verify.assertNotEmpty(iterable.reject(predicateList.get(1)).toList());
            Verify.assertNotEmpty(iterable.reject(predicateList.get(2)).toList());
        }, count, WARM_UP_COUNT);
    }

    private double basicSerialAnySatisfyPerformance(
            FastList<Integer> iterable,
            Predicate<? super Integer> predicate,
            boolean expectedResult,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* AnySatisfy using: Lambda "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> Assert.assertEquals(expectedResult, iterable.anySatisfy(predicate)), count, WARM_UP_COUNT);
    }

    private double basicSerialDetectPerformance(
            FastList<Integer> iterable,
            Predicate<? super Integer> predicate,
            boolean expectedResult,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* Detect using: Lambda "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> Assert.assertEquals(expectedResult, iterable.detect(predicate) != null), count, WARM_UP_COUNT);
    }

    private double basicSerialCollectPerformance(
            FastList<Integer> iterable,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* Collect using: Lambda "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> {
            Verify.assertNotEmpty(iterable.collect(value -> value.shortValue()).toList());
            Verify.assertNotEmpty(iterable.collect(value -> value.longValue()).toList());
        }, count, 10);
    }

    private double basicSerialGroupByPerformance(
            UnifiedSet<String> iterable,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Serial******* GroupBy using: Lambda "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: 1", () -> Verify.assertNotEmpty(iterable.groupBy(ALPHAGRAM_LAMBDA)), count, 10);
    }

    private String formatSizeOf(Iterable<?> iterable)
    {
        return NumberFormat.getInstance().format(Iterate.sizeOf(iterable));
    }

    private static int getBatchSizeFor(Collection<?> iterable)
    {
        return iterable.size() == 1000000 ? 10000 : 1000;
    }

    private double basicParallelLazyForEachPerformance(
            FastList<Integer> iterable,
            String parameterType,
            Procedure<Integer> procedure,
            int count,
            int cores,
            ExecutorService service)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy ForEach using: "
                + parameterType + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> iterable.asParallel(service, iterable.size() / (cores * 3)).forEach(procedure), count, WARM_UP_COUNT);
    }

    private double basicJava8ParallelLazyForEachPerformance(
            List<Integer> iterable,
            String parameterType,
            Consumer<Integer> consumer,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Java8 ForEach using: "
                + parameterType + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: ?", () -> iterable.parallelStream().forEach(consumer), count, WARM_UP_COUNT);
    }

    private double basicParallelLazyToListPerformance(
            FastList<Integer> iterable,
            int count,
            int cores,
            ExecutorService service)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy toList: "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> Verify.assertSize(iterable.size(), iterable.asParallel(service, iterable.size() / (cores * 3)).toList()), count, WARM_UP_COUNT);
    }

    private double basicJava8ParallelLazyToListPerformance(
            List<Integer> iterable,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Java8 toList: "
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: ?", () -> Verify.assertSize(iterable.size(), iterable.parallelStream().collect(Collectors.toList())), count, WARM_UP_COUNT);
    }

    private double basicJava8ParallelLazySelectPerformance(
            List<Integer> iterable,
            String parameterType,
            MutableList<java.util.function.Predicate<Integer>> predicatesList,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Java8 Select using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: ?", () -> {
            iterable.parallelStream().filter(predicatesList.get(0)).collect(Collectors.toList());
            iterable.parallelStream().filter(predicatesList.get(1)).collect(Collectors.toList());
            iterable.parallelStream().filter(predicatesList.get(2)).collect(Collectors.toList());
        }, count, WARM_UP_COUNT);
    }

    private double basicParallelLazySelectPerformance(
            FastList<Integer> iterable,
            String parameterType,
            MutableList<Predicate<Integer>> predicateList,
            int count,
            int cores,
            ExecutorService service)
    {
        int batchSize = SerialParallelLazyPerformanceTest.getBatchSizeFor(iterable);
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy Select using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> {
            iterable.asParallel(service, batchSize).select(predicateList.get(0)).toList();
            iterable.asParallel(service, batchSize).select(predicateList.get(1)).toList();
            iterable.asParallel(service, batchSize).select(predicateList.get(2)).toList();
        }, count, WARM_UP_COUNT);
    }

    private double basicParallelLazyRejectPerformance(
            FastList<Integer> iterable,
            String parameterType,
            MutableList<Predicate<Integer>> predicateList,
            int count,
            int cores,
            ExecutorService service)
    {
        int batchSize = SerialParallelLazyPerformanceTest.getBatchSizeFor(iterable);
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy Reject using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> {
            Verify.assertNotEmpty(iterable.asParallel(service, batchSize).reject(predicateList.get(0)).toList());
            Verify.assertNotEmpty(iterable.asParallel(service, batchSize).reject(predicateList.get(1)).toList());
            Verify.assertNotEmpty(iterable.asParallel(service, batchSize).reject(predicateList.get(2)).toList());
        }, count, WARM_UP_COUNT);
    }

    private double basicJava8ParallelLazyRejectPerformance(
            List<Integer> iterable,
            String parameterType,
            MutableList<java.util.function.Predicate<Integer>> predicateList,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Java8 Reject using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: ?", () -> {
            Verify.assertNotEmpty(iterable.parallelStream().filter(predicateList.get(0).negate()).collect(Collectors.toList()));
            Verify.assertNotEmpty(iterable.parallelStream().filter(predicateList.get(1).negate()).collect(Collectors.toList()));
            Verify.assertNotEmpty(iterable.parallelStream().filter(predicateList.get(2).negate()).collect(Collectors.toList()));
        }, count, WARM_UP_COUNT);
    }

    private double basicParallelLazyAnySatisfyPerformance(
            FastList<Integer> iterable,
            Predicate<? super Integer> predicate,
            String parameterType,
            boolean expectedResult,
            int count,
            int cores,
            ExecutorService service)
    {
        int batchSize = SerialParallelLazyPerformanceTest.getBatchSizeFor(iterable);
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy AnySatisfy using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> Assert.assertEquals(expectedResult, iterable.asParallel(service, batchSize).anySatisfy(predicate)), count, WARM_UP_COUNT);
    }

    private double basicJava8ParallelLazyAnySatisfyPerformance(
            List<Integer> iterable,
            String parameterType,
            java.util.function.Predicate<? super Integer> predicate,
            boolean expectedResult,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Java8 AnySatisfy using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: ?", () -> Assert.assertEquals(expectedResult, iterable.parallelStream().anyMatch(predicate)), count, WARM_UP_COUNT);
    }

    private double basicParallelLazyDetectPerformance(
            FastList<Integer> iterable,
            Predicate<? super Integer> predicate,
            String parameterType,
            boolean expectedResult,
            int count,
            int cores,
            ExecutorService service)
    {
        int batchSize = SerialParallelLazyPerformanceTest.getBatchSizeFor(iterable);
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy Detect using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> Assert.assertEquals(expectedResult, iterable.asParallel(service, batchSize).detect(predicate) != null), count, WARM_UP_COUNT);
    }

    private double basicParallelLazyCollectPerformance(
            FastList<Integer> iterable,
            String parameterType,
            MutableList<Function<Integer, ?>> functionsList,
            int count,
            int cores,
            ExecutorService service)
    {
        int batchSize = SerialParallelLazyPerformanceTest.getBatchSizeFor(iterable);

        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy Collect using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> {
            Verify.assertNotEmpty(iterable.asParallel(service, batchSize).collect(functionsList.get(0)).toList());
            Verify.assertNotEmpty(iterable.asParallel(service, batchSize).collect(functionsList.get(1)).toList());
        }, count, 10);
    }

    private double basicJava8ParallelLazyCollectPerformance(
            List<Integer> iterable,
            String parameterType,
            MutableList<java.util.function.Function<Integer, ?>> functionsList,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Java8 Collect using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: ?", () -> {
            Verify.assertNotEmpty(iterable.parallelStream().map(functionsList.get(0)).collect(Collectors.toList()));
            Verify.assertNotEmpty(iterable.parallelStream().map(functionsList.get(1)).collect(Collectors.toList()));
        }, count, 10);
    }

    private double basicParallelLazyGroupByPerformance(UnifiedSet<String> iterable,
            String parameterType,
            Function<String, Alphagram> function,
            int count,
            int cores,
            ExecutorService service)
    {
        int batchSize = SerialParallelLazyPerformanceTest.getBatchSizeFor(iterable);
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Lazy GroupBy using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: " + cores, () -> Verify.assertNotEmpty(iterable.asParallel(service, batchSize).groupBy(function)), count, WARM_UP_COUNT);
    }

    private double basicParallelLazyJava8GroupByPerformance(Set<String> iterable,
            String parameterType,
            java.util.function.Function<String, Alphagram> function,
            int count)
    {
        return TimeKeeper.logAverageMillisecondsToRun("Parallel Java8 GroupBy using: "
                + parameterType
                + ' '
                + this.getSimpleName(iterable)
                + " size: "
                + this.formatSizeOf(iterable) + " cores: ?", () -> Verify.assertNotEmpty(iterable.parallelStream().collect(Collectors.groupingBy(function))), count, WARM_UP_COUNT);
    }

    private String getSimpleName(Object collection)
    {
        return collection.getClass().getSimpleName();
    }

    static final class TimeKeeper
    {
        private static final SystemTimeProvider PROVIDER = new SystemTimeProvider();
        private static final long PAIN_THRESHOLD = 10000L;
        private static final int MILLIS_TO_NANOS = 1000000;

        private TimeKeeper()
        {
            throw new AssertionError("Suppress default constructor for noninstantiability");
        }

        /**
         * This method can take either a Runnable or a RunnableWithSetup.  In the case of RunnableWithSetup, the setup
         * method will be called first, without impacting the timing.
         */
        public static long millisecondsToRun(Runnable runnable)
        {
            return TimeKeeper.nanosecondsToRun(runnable) / (long) MILLIS_TO_NANOS;
        }

        public static long currentTimeNanoseconds()
        {
            return PROVIDER.currentTimeNanoseconds();
        }

        public static long currentTimeMilliseconds()
        {
            return PROVIDER.currentTimeMilliseconds();
        }

        /**
         * This method can take either a Runnable or a RunnableWithSetup.  In the case of RunnableWithSetup, the setup
         * method will be called first, without impacting the timing.
         */
        public static long nanosecondsToRun(Runnable runnable)
        {
            long start = TimeKeeper.getCurrentTimeAsNanos();
            runnable.run();
            long end = TimeKeeper.getCurrentTimeAsNanos();
            return end - start;
        }

        private static long getCurrentTimeAsNanos()
        {
            return TimeKeeper.currentTimeNanoseconds();
        }

        private static void doLog(String message, int count, long total, double average)
        {
            LOGGER.info("{} Count: {} Total(ms): {} Avg(ms): {}", message, count, TimeKeeper.longNanosToMillisString(total), TimeKeeper.doubleNanosToMillisString(average));
        }

        public static double logAverageMillisecondsToRun(
                String message,
                Runnable runnable,
                int count)
        {
            long start = TimeKeeper.getCurrentTimeAsNanos();
            for (int i = 0; i < count; i++)
            {
                runnable.run();
            }
            long totalNanos = TimeKeeper.getCurrentTimeAsNanos() - start;
            double averageTime = (double) totalNanos / (double) count;
            TimeKeeper.doLog(message, count, totalNanos, averageTime);
            return averageTime / (double) TimeKeeper.MILLIS_TO_NANOS;
        }

        private static String doubleNanosToMillisString(double nanos)
        {
            return NumberFormat.getInstance().format(nanos / MILLIS_TO_NANOS);
        }

        private static String longNanosToMillisString(long nanos)
        {
            return NumberFormat.getInstance().format(nanos / MILLIS_TO_NANOS);
        }

        public static double logAverageMillisecondsToRun(
                String message,
                Runnable runnable,
                int count,
                int warmUpCount)
        {
            TimeKeeper.warmUp(warmUpCount, runnable);
            TimeKeeper.gcAndYield();
            return TimeKeeper.logAverageMillisecondsToRun(message, runnable, count);
        }

        private static void gcAndYield()
        {
            SerialParallelLazyPerformanceTest.forceGC();
        }

        private static void warmUp(int warmUpCount, Runnable runnable)
        {
            long start = TimeKeeper.currentTimeMilliseconds();
            for (int i = 0; i < warmUpCount; i++)
            {
                TimeKeeper.millisecondsToRun(runnable);
                if (TimeKeeper.currentTimeMilliseconds() - start > PAIN_THRESHOLD)
                {
                    break;
                }
            }
        }

        private static class SystemTimeProvider
        {
            public long currentTimeMilliseconds()
            {
                return System.currentTimeMillis();
            }

            public long currentTimeNanoseconds()
            {
                return System.nanoTime();
            }
        }
    }

    private static final class Alphagram
    {
        private final char[] key;
        private final int hashCode;

        private Alphagram(String string)
        {
            this.key = string.toLowerCase().toCharArray();
            Arrays.sort(this.key);
            this.hashCode = Arrays.hashCode(this.key);
        }

        @Override
        public boolean equals(Object o)
        {
            return this == o || Arrays.equals(this.key, ((Alphagram) o).key);
        }

        @Override
        public int hashCode()
        {
            return this.hashCode;
        }

        @Override
        public String toString()
        {
            return new String(this.key);
        }
    }

    public static final class AtomicIntegerWithEquals extends AtomicInteger
    {
        private AtomicIntegerWithEquals(int initialValue)
        {
            super(initialValue);
        }

        @Override
        public int hashCode()
        {
            return this.get();
        }

        @Override
        public boolean equals(Object obj)
        {
            return (obj instanceof AtomicIntegerWithEquals) && ((AtomicIntegerWithEquals) obj).get() == this.get();
        }
    }
}
