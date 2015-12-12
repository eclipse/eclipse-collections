/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl

import java.util.Comparator

import org.eclipse.collections.api.block.function.primitive.{DoubleFunction, FloatFunction, IntFunction, LongFunction}
import org.eclipse.collections.api.block.function.{Function, Function0, Function2, Function3}
import org.eclipse.collections.api.block.predicate.{Predicate, Predicate2}
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure
import org.eclipse.collections.api.block.procedure.{Procedure, Procedure2}

object Prelude
{
    // A "singleton" object to hold the implicit conversion methods
    /*
    * These three methods each take a closure and return an anonymous instance
    * of the corresponding Eclipse Collections interface
    */
    implicit def closure2Procedure[T](closure: (T) => Unit): Procedure[T] =
        new Procedure[T]
        {
            def value(each: T) = closure(each)
        }

    implicit def closure2Procedure2[T1, T2](closure: (T1, T2) => Unit): Procedure2[T1, T2] =
        new Procedure2[T1, T2]
        {
            def value(t1: T1, t2: T2) = closure(t1, t2)
        }

    implicit def closure2Function[T, V](closure: (T) => V): Function[T, V] =
        new Function[T, V]
        {
            def valueOf(t: T) = closure(t)
        }

    implicit def closure2Function2[T1, T2, V](closure: (T1, T2) => V): Function2[T1, T2, V] =
        new Function2[T1, T2, V]
        {
            def value(t1: T1, t2: T2) = closure(t1, t2)
        }

    implicit def closure2Function3[T1, T2, T3, V](closure: (T1, T2, T3) => V): Function3[T1, T2, T3, V] =
        new Function3[T1, T2, T3, V]
        {
            def value(t1: T1, t2: T2, t3: T3) = closure(t1, t2, t3)
        }

    implicit def closure2Predicate[T](closure: (T) => Boolean): Predicate[T] =
        new Predicate[T]
        {
            def accept(each: T) = closure(each)
        }

    implicit def closure2Predicate2[T1, T2](closure: (T1, T2) => Boolean): Predicate2[T1, T2] =
        new Predicate2[T1, T2]
        {
            def accept(t1: T1, t2: T2) = closure(t1, t2)
        }

    implicit def closure2ObjectIntProcedure[T](closure: (T, Int) => Unit): ObjectIntProcedure[T] =
        new ObjectIntProcedure[T]
        {
            def value(each: T, index: Int) = closure(each, index)
        }

    implicit def closure2Runnable(closure: () => Unit): Runnable =
        new Runnable
        {
            def run() = closure()
        }

    implicit def closure2CodeBlock[T](closure: () => T): Function0[T] =
        new Function0[T]
        {
            def value = closure()
        }

    implicit def closure2Comparator[T](closure: (T, T) => Int): Comparator[T] =
        new Comparator[T]
        {
            def compare(o1: T, o2: T) = closure(o1, o2)
        }

    implicit def closure2IntFunction[T](closure: (T) => Int): IntFunction[T] =
        new IntFunction[T]
        {
            def intValueOf(each: T) = closure(each)
        }

    implicit def closure2LongFunction[T](closure: (T) => Long): LongFunction[T] =
        new LongFunction[T]
        {
            def longValueOf(each: T) = closure(each)
        }

    implicit def closure2DoubleFunction[T](closure: (T) => Double): DoubleFunction[T] =
        new DoubleFunction[T]
        {
            def doubleValueOf(each: T) = closure(each)
        }

    implicit def closure2FloatFunction[T](closure: (T) => Float): FloatFunction[T] =
        new FloatFunction[T]
        {
            def floatValueOf(each: T) = closure(each)
        }
}
