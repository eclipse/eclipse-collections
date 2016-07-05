/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api;

/**
 * PrimitiveIterable includes API that is common to all primitive collections.
 *
 * @since 3.0
 */
public interface PrimitiveIterable
{
    /**
     * Returns the number of items in this iterable.
     *
     * @since 3.0
     */
    int size();

    /**
     * Returns true if this iterable has zero items.
     *
     * @since 3.0
     */
    default boolean isEmpty() {
	    return this.size() == 0;
	}

    /**
     * The English equivalent of !this.isEmpty()
     *
     * @since 3.0
     */
    default boolean notEmpty() {
	    return this.size() != 0;
	}

    /**
     * Returns a string representation of this PrimitiveIterable.  The string representation consists of a list of the
     * PrimitiveIterable's elements in the order they are returned by its iterator, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters <tt>", "</tt> (comma and space).  Elements
     * are converted to strings as by String#valueOf().
     *
     * @return a string representation of this PrimitiveIterable
     */
    @Override
    String toString();

    /**
     * Returns a string representation of this collection by delegating to {@link #makeString(String)} and defaulting
     * the separator parameter to the characters <tt>", "</tt> (comma and space).
     *
     * @return a string representation of this collection.
     * @since 3.0
     */
    default String makeString() {
	    return this.makeString(", ");
	}

    /**
     * Returns a string representation of this collection by delegating to {@link #makeString(String, String, String)}
     * and defaulting the start and end parameters to <tt>""</tt> (the empty String).
     *
     * @return a string representation of this collection.
     * @since 3.0
     */
    default String makeString(String separator) {
	    return this.makeString("", separator, "");
	}

    /**
     * Returns a string representation of this collection.  The string representation consists of a list of the
     * collection's elements in the order they are returned by its iterator, enclosed in the start and end strings.
     * Adjacent elements are separated by the separator string.  Elements are converted to strings using the appropriate
     * version of <tt>String.valueOf()</tt>.
     *
     * @return a string representation of this collection.
     * @since 3.0
     */
    default String makeString(String start, String separator, String end) {
	    Appendable stringBuilder = new StringBuilder();
	    this.appendString(stringBuilder, start, separator, end);
	    return stringBuilder.toString();
	}

    /**
     * Prints a string representation of this collection onto the given {@code Appendable}.  Prints the string returned
     * by {@link #makeString()}.
     *
     * @since 3.0
     */
    default void appendString(Appendable appendable) {
	    this.appendString(appendable, ", ");
	}

    /**
     * Prints a string representation of this collection onto the given {@code Appendable}.  Prints the string returned
     * by {@link #makeString(String)}.
     *
     * @since 3.0
     */
    default void appendString(Appendable appendable, String separator) {
	    this.appendString(appendable, "", separator, "");
	}

    /**
     * Prints a string representation of this collection onto the given {@code Appendable}.  Prints the string returned
     * by {@link #makeString(String, String, String)}.
     *
     * @since 3.0
     */
    void appendString(Appendable appendable, String start, String separator, String end);
}
