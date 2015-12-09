/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.collection;

import java.util.Collection;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;

/**
 * A FixedSizeCollection is a collection that may be mutated, but cannot grow or shrink in size.  It is up to
 * the underlying implementation to decide which mutations are allowable.
 */
public interface FixedSizeCollection<T>
        extends MutableCollection<T>
{
    /**
     * This method allows fixed size collections the ability to add elements to their existing elements. A new instance
     * of {@link MutableCollection} is returned containing the elements of the original collection with the new element
     * {@link #add(Object) added}.  Implementations will return a new FixedSizeCollection where possible.  In order to
     * use this method properly with mutable and fixed size collections the following approach must be taken:
     * <p>
     * <pre>
     * MutableCollection<String> list;
     * list = list.with("1");
     * list = list.with("2");
     * return list;
     * </pre>
     *
     * @see #add(Object)
     */
    MutableCollection<T> with(T element);

    /**
     * This method allows fixed size collections the ability to remove elements from their existing elements. A new
     * instance of {@link MutableCollection} is returned containing the elements of the original collection with the
     * element {@link #remove(Object) removed}.  Implementations will return a new FixedSizeCollection where possible.
     * In order to use this method properly with mutable and fixed size collections the following approach must be
     * taken:
     * <p>
     * <pre>
     * MutableCollection<String> list;
     * list = list.without("1");
     * list = list.without("2");
     * return list;
     * </pre>
     *
     * @see #remove(Object)
     */
    MutableCollection<T> without(T element);

    /**
     * This method allows fixed size collections the ability to add multiple elements to their existing elements. A new
     * instance of {@link MutableCollection} is returned containing the elements of the original collection with all of
     * the new elements {@link #addAll(Collection) added}.  Implementations will return a new FixedSizeCollection where
     * possible.  In order to use this method properly with mutable and fixed size collections the following approach
     * must be taken:
     * <p>
     * <pre>
     * MutableCollection<String> list;
     * list = list.withAll(FastList.newListWith("1", "2"));
     * return list;
     * </pre>
     *
     * @see #addAll(Collection)
     */
    MutableCollection<T> withAll(Iterable<? extends T> elements);

    /**
     * This method allows fixed size collections the ability to remove multiple elements from their existing elements.
     * A new instance of {@link MutableCollection} is returned containing the elements of the original collection with
     * the given elements {@link #removeAll(Collection) removed}.  Implementations will return a new FixedSizeCollection
     * where possible.  In order to use this method properly with mutable and fixed size collections the following
     * approach must be taken:
     * <p>
     * <pre>
     * MutableCollection<String> list;
     * list = list.withoutAll(FastList.newListWith("1", "2"));
     * return list;
     * </pre>
     *
     * @see #removeAll(Collection)
     */
    MutableCollection<T> withoutAll(Iterable<? extends T> elements);

    /**
     * @throws UnsupportedOperationException
     */
    boolean add(T t);

    /**
     * @throws UnsupportedOperationException
     */
    boolean addAllIterable(Iterable<? extends T> iterable);

    /**
     * @throws UnsupportedOperationException
     */
    boolean addAll(Collection<? extends T> collection);

    /**
     * @throws UnsupportedOperationException
     */
    boolean remove(Object o);

    /**
     * @throws UnsupportedOperationException
     */
    boolean removeAll(Collection<?> collection);

    /**
     * @throws UnsupportedOperationException
     */
    boolean removeAllIterable(Iterable<?> iterable);

    /**
     * @throws UnsupportedOperationException
     */
    boolean removeIf(Predicate<? super T> predicate);

    /**
     * @throws UnsupportedOperationException
     */
    <P> boolean removeIfWith(Predicate2<? super T, ? super P> predicate, P parameter);

    /**
     * @throws UnsupportedOperationException
     */
    boolean retainAll(Collection<?> collection);

    /**
     * @throws UnsupportedOperationException
     */
    boolean retainAllIterable(Iterable<?> iterable);

    /**
     * @throws UnsupportedOperationException
     */
    void clear();
}
