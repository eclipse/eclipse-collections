/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.jmh

import org.junit.Assert

import scala.collection.mutable.ArrayBuffer

object GroupByScalaTest
{
    private val SIZE = 1000000
    private val integers = new ArrayBuffer[Int](SIZE) ++ (1 to SIZE)

    def groupBy_2_keys_serial_eager_scala()
    {
        Assert.assertEquals(2, this.integers.groupBy((each: Int) => each % 2 == 0).size)
    }

    def groupBy_100_keys_serial_eager_scala()
    {
        Assert.assertEquals(100, this.integers.groupBy((each: Int) => each % 100).size)
    }

    def groupBy_10000_keys_serial_eager_scala()
    {
        Assert.assertEquals(10000, this.integers.groupBy((each: Int) => each % 10000).size)
    }

    def groupBy_2_keys_serial_lazy_scala()
    {
        Assert.assertEquals(2, this.integers.view.groupBy((each: Int) => each % 2 == 0).size)
    }

    def groupBy_100_keys_serial_lazy_scala()
    {
        Assert.assertEquals(100, this.integers.view.groupBy((each: Int) => each % 100).size)
    }

    def groupBy_10000_keys_serial_lazy_scala()
    {
        Assert.assertEquals(10000, this.integers.view.groupBy((each: Int) => each % 10000).size)
    }
}
