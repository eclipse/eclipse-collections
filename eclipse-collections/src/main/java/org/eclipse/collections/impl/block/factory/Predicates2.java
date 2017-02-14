/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.impl.block.predicate.checked.CheckedPredicate2;
import org.eclipse.collections.impl.block.predicate.checked.ThrowingPredicate2;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * Predicates2 is a static version of Predicates.  All of its values are statically initialized, with the exception
 * of and, or, not and all of the attribute combination methods.  Predicates2 can only work with one parameter,
 * so it is limited to handling only conditions of a simple nature.
 */
public abstract class Predicates2<T, P>
        implements Predicate2<T, P>
{
    private static final long serialVersionUID = 1L;
    private static final Predicates2<Object, Object> NOT_EQUAL = new NotEqual();
    private static final Predicates2<Object, Iterable<?>> IN = new In();
    private static final Predicates2<Object, Object> EQUAL = new Equal();
    private static final Predicates2<Object, Iterable<?>> NOT_IN = new NotIn();
    private static final Predicates2<?, ?> LESS_THAN = new LessThan();
    private static final Predicates2<?, ?> LESS_THAN_OR_EQUAL = new LessThanOrEqual();
    private static final Predicates2<?, ?> GREATER_THAN = new GreaterThan();
    private static final Predicates2<?, ?> GREATER_THAN_OR_EQUAL = new GreaterThanOrEqual();
    private static final Predicates2<Object, Class<?>> INSTANCE_OF = new IsInstanceOf();
    private static final Predicates2<Object, Object> IS_IDENTICAL = new IsIdentical();
    private static final Predicates2<Object, Object> NOT_IDENTITICAL = new NotIdentitical();
    private static final Predicates2<Object, Class<?>> NOT_INSTANCE_OF = new NotInstanceOf();
    private static final Predicates2<Object, Object> ALWAYS_TRUE = new AlwaysTrue();
    private static final Predicates2<Object, Object> ALWAYS_FALSE = new AlwaysFalse();
    private static final Predicates2<Object, Object> IS_NULL = new IsNull();
    private static final Predicates2<Object, Object> NOT_NULL = new NotNull();

    public Predicates2<T, P> and(Predicate2<? super T, ? super P> op)
    {
        return Predicates2.and(this, op);
    }

    public Predicates2<T, P> or(Predicate2<? super T, ? super P> op)
    {
        return Predicates2.or(this, op);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Predicate2 that will throw a RuntimeException, wrapping the checked exception that is the cause.
     */
    public static <T, P> Predicate2<T, P> throwing(ThrowingPredicate2<T, P> throwingPredicate2)
    {
        return new ThrowingPredicate2Adapter<>(throwingPredicate2);
    }

    /**
     * Allows a lambda or anonymous inner class that needs to throw a checked exception to be safely wrapped as a
     * Procedure2 that will throw a user specified RuntimeException based on the provided function. The function
     * is passed the current element and the checked exception that was thrown as context arguments.
     */
    public static <T1, T2> Predicate2<T1, T2> throwing(
            ThrowingPredicate2<T1, T2> throwingPredicate2,
            Function3<T1, T2, ? super Throwable, ? extends RuntimeException> rethrow)
    {
        return (one, two) ->
        {
            try
            {
                return throwingPredicate2.safeAccept(one, two);
            }
            catch (RuntimeException e)
            {
                throw e;
            }
            catch (Throwable t)
            {
                throw rethrow.value(one, two, t);
            }
        };
    }

    public static <T, P> Predicates2<T, P> not(Predicate2<T, P> predicate)
    {
        return new Not<>(predicate);
    }

    public static <T, P> Predicates2<T, P> or(
            Predicate2<? super T, ? super P> left,
            Predicate2<? super T, ? super P> right)
    {
        return new Or<>(left, right);
    }

    public static <T, P> Predicates2<T, P> and(
            Predicate2<? super T, ? super P> left,
            Predicate2<? super T, ? super P> right)
    {
        return new And<>(left, right);
    }

    public static <T> Predicates2<T, Iterable<?>> attributeIn(Function<T, ?> function)
    {
        return new AttributePredicates2(function, Predicates2.in());
    }

    public static <T> Predicates2<T, Iterable<?>> attributeNotIn(Function<T, ?> function)
    {
        return new AttributePredicates2(function, Predicates2.notIn());
    }

    public static Predicates2<Object, Object> alwaysTrue()
    {
        return ALWAYS_TRUE;
    }

    public static Predicates2<Object, Object> alwaysFalse()
    {
        return ALWAYS_FALSE;
    }

    public static Predicates2<Object, Object> isNull()
    {
        return IS_NULL;
    }

    public static Predicates2<Object, Object> notNull()
    {
        return NOT_NULL;
    }

    public static Predicates2<Object, Object> equal()
    {
        return EQUAL;
    }

    public static Predicates2<Object, Object> notEqual()
    {
        return NOT_EQUAL;
    }

    public static Predicates2<Object, Iterable<?>> in()
    {
        return IN;
    }

    public static Predicates2<Object, Iterable<?>> notIn()
    {
        return NOT_IN;
    }

    public static <T extends Comparable<T>> Predicates2<T, T> lessThan()
    {
        return (Predicates2<T, T>) LESS_THAN;
    }

    public static <T extends Comparable<T>> Predicates2<T, T> lessThanOrEqualTo()
    {
        return (Predicates2<T, T>) LESS_THAN_OR_EQUAL;
    }

    public static <T extends Comparable<T>> Predicates2<T, T> greaterThan()
    {
        return (Predicates2<T, T>) GREATER_THAN;
    }

    public static Predicates2<Object, Object> sameAs()
    {
        return IS_IDENTICAL;
    }

    public static Predicates2<Object, Object> notSameAs()
    {
        return NOT_IDENTITICAL;
    }

    public static Predicates2<Object, Class<?>> instanceOf()
    {
        return INSTANCE_OF;
    }

    public static Predicates2<Object, Class<?>> notInstanceOf()
    {
        return NOT_INSTANCE_OF;
    }

    public static <T extends Comparable<T>> Predicates2<T, T> greaterThanOrEqualTo()
    {
        return (Predicates2<T, T>) GREATER_THAN_OR_EQUAL;
    }

    public static <T> Predicates2<T, Object> attributeNotEqual(Function<T, ?> function)
    {
        return new AttributePredicates2<>(function, Predicates2.notEqual());
    }

    public static <T, P extends Comparable<? super P>> Predicates2<T, P> attributeLessThan(Function<T, P> function)
    {
        return new AttributePredicates2<>(function, (Predicate2<P, P>) LESS_THAN);
    }

    public static <T, P extends Comparable<? super P>> Predicates2<T, P> attributeLessThanOrEqualTo(
            Function<T, P> function)
    {
        return new AttributePredicates2<>(function, (Predicate2<P, P>) LESS_THAN_OR_EQUAL);
    }

    public static <T, P extends Comparable<? super P>> Predicates2<T, P> attributeGreaterThan(Function<T, P> function)
    {
        return new AttributePredicates2<>(function, (Predicate2<P, P>) GREATER_THAN);
    }

    public static <T, P extends Comparable<? super P>> Predicates2<T, P> attributeGreaterThanOrEqualTo(
            Function<T, P> function)
    {
        return new AttributePredicates2<>(function, (Predicate2<P, P>) GREATER_THAN_OR_EQUAL);
    }

    public static <T> Predicates2<T, Object> attributeEqual(Function<T, ?> function)
    {
        return new AttributePredicates2<>(function, Predicates2.equal());
    }

    private static final class Or<T, P>
            extends Predicates2<T, P>
    {
        private static final long serialVersionUID = 1L;

        private final Predicate2<? super T, ? super P> left;
        private final Predicate2<? super T, ? super P> right;

        private Or(Predicate2<? super T, ? super P> one, Predicate2<? super T, ? super P> two)
        {
            this.left = one;
            this.right = two;
        }

        @Override
        public boolean accept(T each, P injectedValue)
        {
            return this.left.accept(each, injectedValue) || this.right.accept(each, injectedValue);
        }

        @Override
        public String toString()
        {
            return "(" + this.left + " or " + this.right + ')';
        }
    }

    private static final class And<T, P>
            extends Predicates2<T, P>
    {
        private static final long serialVersionUID = 1L;

        private final Predicate2<? super T, ? super P> left;
        private final Predicate2<? super T, ? super P> right;

        private And(Predicate2<? super T, ? super P> one, Predicate2<? super T, ? super P> two)
        {
            this.left = one;
            this.right = two;
        }

        @Override
        public boolean accept(T each, P injectedValue)
        {
            return this.left.accept(each, injectedValue) && this.right.accept(each, injectedValue);
        }

        @Override
        public String toString()
        {
            return "(" + this.left + " and " + this.right + ')';
        }
    }

    private static final class AttributePredicates2<T, P>
            extends Predicates2<T, P>
    {
        private static final long serialVersionUID = 1L;

        private final Function<? super T, ? extends P> function;
        private final Predicate2<? super P, ? super P> predicate;

        private AttributePredicates2(
                Function<? super T, ? extends P> function,
                Predicate2<? super P, ? super P> predicate)
        {
            this.function = function;
            this.predicate = predicate;
        }

        @Override
        public boolean accept(T each, P injectedValue)
        {
            return this.predicate.accept(this.function.valueOf(each), injectedValue);
        }

        @Override
        public String toString()
        {
            return this.function + " " + this.predicate;
        }
    }

    private static final class Not<T, P>
            extends Predicates2<T, P>
    {
        private static final long serialVersionUID = 1L;

        private final Predicate2<T, P> predicate;

        private Not(Predicate2<T, P> predicate)
        {
            this.predicate = predicate;
        }

        @Override
        public boolean accept(T each, P injectedValue)
        {
            return !this.predicate.accept(each, injectedValue);
        }

        @Override
        public String toString()
        {
            return "not " + this.predicate;
        }
    }

    private static final class Equal
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object compareTo)
        {
            if (compareTo == null)
            {
                return each == null;
            }
            return compareTo.equals(each);
        }

        @Override
        public String toString()
        {
            return "= <method parameter>";
        }
    }

    private static final class In
            extends Predicates2<Object, Iterable<?>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Iterable<?> injectedIterable)
        {
            return Iterate.contains(injectedIterable, each);
        }

        @Override
        public String toString()
        {
            return "in <method parameter>";
        }
    }

    private static final class NotIn
            extends Predicates2<Object, Iterable<?>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Iterable<?> injectedIterable)
        {
            return !Iterate.contains(injectedIterable, each);
        }

        @Override
        public String toString()
        {
            return "not in <method parameter>";
        }
    }

    private static class LessThan<T extends Comparable<T>>
            extends Predicates2<T, T>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(T each, T comparison)
        {
            return each.compareTo(comparison) < 0;
        }

        @Override
        public String toString()
        {
            return "< <method parameter>";
        }
    }

    private static class LessThanOrEqual<T extends Comparable<T>>
            extends Predicates2<T, T>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(T each, T comparison)
        {
            return each.compareTo(comparison) <= 0;
        }

        @Override
        public String toString()
        {
            return "<= <method parameter>";
        }
    }

    private static class GreaterThan<T extends Comparable<T>>
            extends Predicates2<T, T>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(T each, T comparison)
        {
            return each.compareTo(comparison) > 0;
        }

        @Override
        public String toString()
        {
            return "> <method parameter>";
        }
    }

    private static class GreaterThanOrEqual<T extends Comparable<T>>
            extends Predicates2<T, T>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(T each, T comparison)
        {
            return each.compareTo(comparison) >= 0;
        }

        @Override
        public String toString()
        {
            return ">= <method parameter>";
        }
    }

    private static final class NotEqual
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object compareTo)
        {
            if (compareTo == null)
            {
                return each != null;
            }
            return !compareTo.equals(each);
        }

        @Override
        public String toString()
        {
            return "not = <method parameter>";
        }
    }

    private static final class IsInstanceOf
            extends Predicates2<Object, Class<?>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Class<?> injectedClass)
        {
            return injectedClass.isInstance(each);
        }

        @Override
        public String toString()
        {
            return "is a(n) <method parameter>";
        }
    }

    private static final class NotInstanceOf
            extends Predicates2<Object, Class<?>>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Class<?> injectedClass)
        {
            return !injectedClass.isInstance(each);
        }

        @Override
        public String toString()
        {
            return "is not a(n) <method parameter>";
        }
    }

    private static final class IsIdentical
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object injectedValue)
        {
            return each == injectedValue;
        }

        @Override
        public String toString()
        {
            return "is identical to <method parameter>";
        }
    }

    private static final class NotIdentitical
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object injectedValue)
        {
            return each != injectedValue;
        }

        @Override
        public String toString()
        {
            return "is not identical to <method parameter>";
        }
    }

    private static final class IsNull
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object notImportant)
        {
            return each == null;
        }

        @Override
        public String toString()
        {
            return "is null";
        }
    }

    private static final class NotNull
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object notImportant)
        {
            return each != null;
        }

        @Override
        public String toString()
        {
            return "not null";
        }
    }

    private static final class AlwaysTrue
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object notImportant)
        {
            return true;
        }

        @Override
        public String toString()
        {
            return "always true";
        }
    }

    private static final class AlwaysFalse
            extends Predicates2<Object, Object>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Object each, Object notImportant)
        {
            return false;
        }

        @Override
        public String toString()
        {
            return "always false";
        }
    }

    private static final class ThrowingPredicate2Adapter<T, P> extends CheckedPredicate2<T, P>
    {
        private static final long serialVersionUID = 1L;
        private final ThrowingPredicate2<T, P> throwingPredicate2;

        private ThrowingPredicate2Adapter(ThrowingPredicate2<T, P> throwingPredicate2)
        {
            this.throwingPredicate2 = throwingPredicate2;
        }

        @Override
        public boolean safeAccept(T object, P param) throws Exception
        {
            return this.throwingPredicate2.safeAccept(object, param);
        }
    }
}
