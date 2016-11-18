/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory.primitive;

import org.eclipse.collections.api.block.predicate.primitive.BooleanPredicate;

/**
 * Provides a set of common predicates for boolean values.
 */
public final class BooleanPredicates
{
    private static final BooleanPredicate IS_TRUE_BOOLEAN_PREDICATE = new IsTrueBooleanPredicate();
    private static final BooleanPredicate IS_FALSE_BOOLEAN_PREDICATE = new IsFalseBooleanPredicate();
    private static final BooleanPredicate FALSE_PREDICATE = new FalsePredicate();
    private static final BooleanPredicate TRUE_PREDICATE = new TruePredicate();
    private static final BooleanPredicate ALWAYS_TRUE = new AlwaysTrueBooleanPredicate();
    private static final BooleanPredicate ALWAYS_FALSE = new AlwaysFalseBooleanPredicate();

    private BooleanPredicates()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    @SuppressWarnings("MisspelledEquals")
    public static BooleanPredicate equal(boolean expected)
    {
        return expected ? IS_TRUE_BOOLEAN_PREDICATE : IS_FALSE_BOOLEAN_PREDICATE;
    }

    public static BooleanPredicate not(boolean expected)
    {
        return expected ? IS_FALSE_BOOLEAN_PREDICATE : IS_TRUE_BOOLEAN_PREDICATE;
    }

    public static BooleanPredicate isTrue()
    {
        return IS_TRUE_BOOLEAN_PREDICATE;
    }

    public static BooleanPredicate isFalse()
    {
        return IS_FALSE_BOOLEAN_PREDICATE;
    }

    public static BooleanPredicate alwaysTrue()
    {
        return ALWAYS_TRUE;
    }

    public static BooleanPredicate alwaysFalse()
    {
        return ALWAYS_FALSE;
    }

    public static BooleanPredicate and(BooleanPredicate one, BooleanPredicate two)
    {
        if (one == IS_TRUE_BOOLEAN_PREDICATE && two == IS_TRUE_BOOLEAN_PREDICATE)
        {
            return IS_TRUE_BOOLEAN_PREDICATE;
        }
        if (one == IS_FALSE_BOOLEAN_PREDICATE && two == IS_TRUE_BOOLEAN_PREDICATE || one == IS_TRUE_BOOLEAN_PREDICATE && two == IS_FALSE_BOOLEAN_PREDICATE)
        {
            return FALSE_PREDICATE;
        }
        if (one == IS_FALSE_BOOLEAN_PREDICATE && two == IS_FALSE_BOOLEAN_PREDICATE)
        {
            return IS_FALSE_BOOLEAN_PREDICATE;
        }
        return new AndBooleanPredicate(one, two);
    }

    public static BooleanPredicate or(BooleanPredicate one, BooleanPredicate two)
    {
        if (one == IS_TRUE_BOOLEAN_PREDICATE && two == IS_TRUE_BOOLEAN_PREDICATE)
        {
            return IS_TRUE_BOOLEAN_PREDICATE;
        }
        if (one == IS_FALSE_BOOLEAN_PREDICATE && two == IS_TRUE_BOOLEAN_PREDICATE || one == IS_TRUE_BOOLEAN_PREDICATE && two == IS_FALSE_BOOLEAN_PREDICATE)
        {
            return TRUE_PREDICATE;
        }
        if (one == IS_FALSE_BOOLEAN_PREDICATE && two == IS_FALSE_BOOLEAN_PREDICATE)
        {
            return IS_FALSE_BOOLEAN_PREDICATE;
        }
        return new OrBooleanPredicate(one, two);
    }

    public static BooleanPredicate not(BooleanPredicate negate)
    {
        if (negate == IS_TRUE_BOOLEAN_PREDICATE)
        {
            return IS_FALSE_BOOLEAN_PREDICATE;
        }
        if (negate == IS_FALSE_BOOLEAN_PREDICATE)
        {
            return IS_TRUE_BOOLEAN_PREDICATE;
        }
        return new NotBooleanPredicate(negate);
    }

    private static final class IsTrueBooleanPredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(boolean value)
        {
            return value;
        }
    }

    private static final class IsFalseBooleanPredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(boolean value)
        {
            return !value;
        }
    }

    private static final class AndBooleanPredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        private final BooleanPredicate one;
        private final BooleanPredicate two;

        private AndBooleanPredicate(BooleanPredicate one, BooleanPredicate two)
        {
            this.one = one;
            this.two = two;
        }

        @Override
        public boolean accept(boolean actual)
        {
            return this.one.accept(actual) && this.two.accept(actual);
        }
    }

    private static final class OrBooleanPredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        private final BooleanPredicate one;
        private final BooleanPredicate two;

        private OrBooleanPredicate(BooleanPredicate one, BooleanPredicate two)
        {
            this.one = one;
            this.two = two;
        }

        @Override
        public boolean accept(boolean actual)
        {
            return this.one.accept(actual) || this.two.accept(actual);
        }
    }

    private static final class NotBooleanPredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        private final BooleanPredicate negate;

        private NotBooleanPredicate(BooleanPredicate negate)
        {
            this.negate = negate;
        }

        @Override
        public boolean accept(boolean actual)
        {
            return !this.negate.accept(actual);
        }
    }

    private static final class FalsePredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(boolean value)
        {
            return false;
        }
    }

    private static final class TruePredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(boolean value)
        {
            return true;
        }
    }

    private static final class AlwaysTrueBooleanPredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(boolean value)
        {
            return true;
        }
    }

    private static final class AlwaysFalseBooleanPredicate implements BooleanPredicate
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(boolean value)
        {
            return false;
        }
    }
}
