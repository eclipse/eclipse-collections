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

import org.eclipse.collections.impl.utility.StringIterate;

/**
 * The StringPredicates2 class is a factory that produces Predicate2 instances that work with Strings.
 */
public final class StringPredicates2
{
    private static final ContainsString CONTAINS_STRING = new ContainsString();
    private static final NotContainsString NOT_CONTAINS_STRING = new NotContainsString();
    private static final StartsWith STARTS_WITH = new StartsWith();
    private static final NotStartsWith NOT_STARTS_WITH = new NotStartsWith();
    private static final EndsWith ENDS_WITH = new EndsWith();
    private static final NotEndsWith NOT_ENDS_WITH = new NotEndsWith();
    private static final EqualsIgnoreCase EQUALS_IGNORE_CASE = new EqualsIgnoreCase();
    private static final NotEqualsIgnoreCase NOT_EQUALS_IGNORE_CASE = new NotEqualsIgnoreCase();
    private static final MatchesRegex MATCHES_REGEX = new MatchesRegex();

    private StringPredicates2()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    /**
     * Returns true if a String specified on the predicate is contained within a String passed to the the accept
     * method.
     */
    public static Predicates2<String, String> contains()
    {
        return CONTAINS_STRING;
    }

    /**
     * Returns true if a String specified on the predicate is contained within a String passed to the the accept
     * method.
     *
     * @since 5.0
     */
    public static Predicates2<String, String> notContains()
    {
        return NOT_CONTAINS_STRING;
    }

    /**
     * Returns true if a String passed to the the accept method starts with the string specified on the predicate.
     */
    public static Predicates2<String, String> startsWith()
    {
        return STARTS_WITH;
    }

    /**
     * Returns false if a String passed to the the accept method starts with the string specified on the predicate.
     *
     * @since 5.0
     */
    public static Predicates2<String, String> notStartsWith()
    {
        return NOT_STARTS_WITH;
    }

    /**
     * Returns true if a String passed to the the accept method ends with the string specified on the predicate.
     */
    public static Predicates2<String, String> endsWith()
    {
        return ENDS_WITH;
    }

    /**
     * Returns false if a String passed to the the accept method ends with the string specified on the predicate.
     *
     * @since 5.0
     */
    public static Predicates2<String, String> notEndsWith()
    {
        return NOT_ENDS_WITH;
    }

    public static Predicates2<String, String> equalsIgnoreCase()
    {
        return EQUALS_IGNORE_CASE;
    }

    /**
     * @since 5.0
     */
    public static Predicates2<String, String> notEqualsIgnoreCase()
    {
        return NOT_EQUALS_IGNORE_CASE;
    }

    public static Predicates2<String, String> matches()
    {
        return MATCHES_REGEX;
    }

    private static final class ContainsString extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return StringIterate.notEmpty(each) && each.contains(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.contains()";
        }
    }

    private static final class NotContainsString extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return StringIterate.isEmpty(each) || !each.contains(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.notContains()";
        }
    }

    private static final class StartsWith extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return each != null && each.startsWith(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.startsWith()";
        }
    }

    private static final class NotStartsWith extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return each == null || !each.startsWith(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.notStartsWith()";
        }
    }

    private static final class EndsWith extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return each != null && each.endsWith(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.endsWith()";
        }
    }

    private static final class NotEndsWith extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return each == null || !each.endsWith(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.notEndsWith()";
        }
    }

    private static final class EqualsIgnoreCase extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return each != null && each.equalsIgnoreCase(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.equalsIgnoreCase()";
        }
    }

    private static final class NotEqualsIgnoreCase extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return each == null || !each.equalsIgnoreCase(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.notEqualsIgnoreCase()";
        }
    }

    private static final class MatchesRegex extends Predicates2<String, String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String each, String parameter)
        {
            return each != null && each.matches(parameter);
        }

        @Override
        public String toString()
        {
            return "StringPredicates2.matches()";
        }
    }
}
