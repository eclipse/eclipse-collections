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

import java.util.Collections

import org.eclipse.collections.api._
import org.eclipse.collections.api.block.function.Function
import org.eclipse.collections.impl.Prelude._
import org.eclipse.collections.impl.block.factory.Comparators
import org.eclipse.collections.impl.list.mutable.FastList
import org.eclipse.collections.impl.utility.internal.IteratorIterate
import org.junit.Test
import org.slf4j.LoggerFactory

object MutableAnagramTest
{
    val LOGGER = LoggerFactory.getLogger(classOf[MutableAnagramTest])
}

class MutableAnagramTest
{
    val SIZE_THRESHOLD = 10
    val WORDS = FastList.newListWith("alerts", "alters", "artels", "estral", "laster", "ratels", "salter", "slater", "staler", "stelar", "talers", "least", "setal", "slate", "stale", "steal", "stela", "taels", "tales", "teals", "tesla").iterator

    @Test
    def testAnagrams
    {
        val sizeOfIterable: Function[RichIterable[String], Integer] = (iterable: RichIterable[String]) => Integer.valueOf(iterable.size)
        IteratorIterate.groupBy(WORDS, (string: String) => string.sortWith(_ > _))
                .multiValuesView
                .select((iterable: RichIterable[String]) => iterable.size > SIZE_THRESHOLD)
                .toSortedList(Collections.reverseOrder(Comparators.byFunction[RichIterable[String], Integer](sizeOfIterable)))
                .asLazy
                .collect[String]((iterable: RichIterable[String]) => iterable.size + ": " + iterable)
                .forEach((string: String) => MutableAnagramTest.LOGGER.info(string))
    }
}
