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

import org.junit.Test
import org.slf4j.LoggerFactory

object ScalaAnagramTest
{
    val LOGGER = LoggerFactory.getLogger(classOf[ScalaAnagramTest])
}

class ScalaAnagramTest
{
    val SIZE_THRESHOLD = 10
    val WORDS = Stream("alerts", "alters", "artels", "estral", "laster", "ratels", "salter", "slater", "staler", "stelar", "talers", "least", "setal", "slate", "stale", "steal", "stela", "taels", "tales", "teals", "tesla")

    def log(string: String)
    {
        ScalaAnagramTest.LOGGER.info(string)
    }

    @Test
    def testAnagrams
    {
        WORDS.groupBy(_.sorted)
                .values
                .filter(_.size > SIZE_THRESHOLD)
                .toList
                .sortWith(_.size > _.size)
                .map(list => list.size + ": " + list)
                .foreach(log)
    }

    @Test
    def testAnagramsLonghand
    {
        WORDS.groupBy(word => word.sorted)
                .values
                .filter(list => list.size > SIZE_THRESHOLD)
                .toList
                .sortWith((list1, list2) => list1.size > list2.size)
                .map(list => list.size + ": " + list)
                .foreach(listString => log(listString))
    }
}
