/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.primitive;

import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.junit.Test;

public class CollectIterableSerializationTest
{
    @Test
    public void intSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3RJbnRJdGVyYWJsZSRJbnRGdW5jdGlvblRvUHJvY2VkdXJlAAAAAAAAAAECAAFMAAhmdW5jdGlv\n"
                        + "bnQAQkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZl\n"
                        + "L0ludEZ1bmN0aW9uO3hwc3IAT29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFj\n"
                        + "dG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkVW5ib3hJbnRlZ2VyVG9JbnQAAAAAAAAAAQIAAHhw");
        CollectIntIterable<Integer> collectIntIterable = new CollectIntIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.unboxIntegerToInt());

        collectIntIterable.forEach(null);
    }

    @Test
    public void doubleSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAFtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3REb3VibGVJdGVyYWJsZSREb3VibGVGdW5jdGlvblRvUHJvY2VkdXJlAAAAAAAAAAECAAFMAAhm\n"
                        + "dW5jdGlvbnQARUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJp\n"
                        + "bWl0aXZlL0RvdWJsZUZ1bmN0aW9uO3hwc3IAUm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwu\n"
                        + "YmxvY2suZmFjdG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkVW5ib3hJbnRlZ2VyVG9Eb3VibGUAAAAA\n"
                        + "AAAAAQIAAHhw");
        CollectDoubleIterable<Integer> collectDoubleIterable = new CollectDoubleIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.unboxIntegerToDouble());

        collectDoubleIterable.forEach(null);
    }

    @Test
    public void floatSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3RGbG9hdEl0ZXJhYmxlJEZsb2F0RnVuY3Rpb25Ub1Byb2NlZHVyZQAAAAAAAAABAgABTAAIZnVu\n"
                        + "Y3Rpb250AERMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1p\n"
                        + "dGl2ZS9GbG9hdEZ1bmN0aW9uO3hwc3IAUW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkVW5ib3hJbnRlZ2VyVG9GbG9hdAAAAAAAAAAB\n"
                        + "AgAAeHA=");

        CollectFloatIterable<Integer> collectFloatIterable = new CollectFloatIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.unboxIntegerToFloat());

        collectFloatIterable.forEach(null);
    }

    @Test
    public void longSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3RMb25nSXRlcmFibGUkTG9uZ0Z1bmN0aW9uVG9Qcm9jZWR1cmUAAAAAAAAAAQIAAUwACGZ1bmN0\n"
                        + "aW9udABDTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRp\n"
                        + "dmUvTG9uZ0Z1bmN0aW9uO3hwc3IAUG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "ZmFjdG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkVW5ib3hJbnRlZ2VyVG9Mb25nAAAAAAAAAAECAAB4\n"
                        + "cA==");

        CollectLongIterable<Integer> collectLongIterable = new CollectLongIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.unboxIntegerToLong());

        collectLongIterable.forEach(null);
    }

    @Test
    public void shortSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3RTaG9ydEl0ZXJhYmxlJFNob3J0RnVuY3Rpb25Ub1Byb2NlZHVyZQAAAAAAAAABAgABTAAIZnVu\n"
                        + "Y3Rpb250AERMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1p\n"
                        + "dGl2ZS9TaG9ydEZ1bmN0aW9uO3hwc3IAUW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkVW5ib3hJbnRlZ2VyVG9TaG9ydAAAAAAAAAAB\n"
                        + "AgAAeHA=");

        CollectShortIterable<Integer> collectShortIterable = new CollectShortIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.unboxIntegerToShort());

        collectShortIterable.forEach(null);
    }

    @Test
    public void byteSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3RCeXRlSXRlcmFibGUkQnl0ZUZ1bmN0aW9uVG9Qcm9jZWR1cmUAAAAAAAAAAQIAAUwACGZ1bmN0\n"
                        + "aW9udABDTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRp\n"
                        + "dmUvQnl0ZUZ1bmN0aW9uO3hwc3IAUG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "ZmFjdG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkVW5ib3hJbnRlZ2VyVG9CeXRlAAAAAAAAAAECAAB4\n"
                        + "cA==");

        CollectByteIterable<Integer> collectByteIterable = new CollectByteIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.unboxIntegerToByte());

        collectByteIterable.forEach(null);
    }

    @Test
    public void charSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3RDaGFySXRlcmFibGUkQ2hhckZ1bmN0aW9uVG9Qcm9jZWR1cmUAAAAAAAAAAQIAAUwACGZ1bmN0\n"
                        + "aW9udABDTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9wcmltaXRp\n"
                        + "dmUvQ2hhckZ1bmN0aW9uO3hwc3IAUG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "ZmFjdG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkVW5ib3hJbnRlZ2VyVG9DaGFyAAAAAAAAAAECAAB4\n"
                        + "cA==");

        CollectCharIterable<Integer> collectCharIterable = new CollectCharIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.unboxIntegerToChar());

        collectCharIterable.forEach(null);
    }

    @Test
    public void booleanSerialization()
    {
        LazyIterableTestHelper<Integer> integerLazyIterableTestHelper = new LazyIterableTestHelper<>(
                "rO0ABXNyAF1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxhenkucHJpbWl0aXZlLkNvbGxl\n"
                        + "Y3RCb29sZWFuSXRlcmFibGUkQm9vbGVhbkZ1bmN0aW9uVG9Qcm9jZWR1cmUAAAAAAAAAAQIAAUwA\n"
                        + "CGZ1bmN0aW9udABGTG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9w\n"
                        + "cmltaXRpdmUvQm9vbGVhbkZ1bmN0aW9uO3hwc3IAT29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmlt\n"
                        + "cGwuYmxvY2suZmFjdG9yeS5QcmltaXRpdmVGdW5jdGlvbnMkSW50ZWdlcklzUG9zaXRpdmUAAAAA\n"
                        + "AAAAAQIAAHhw");

        CollectBooleanIterable<Integer> collectBooleanIterable = new CollectBooleanIterable<>(
                integerLazyIterableTestHelper,
                PrimitiveFunctions.integerIsPositive());

        collectBooleanIterable.forEach(null);
    }
}
