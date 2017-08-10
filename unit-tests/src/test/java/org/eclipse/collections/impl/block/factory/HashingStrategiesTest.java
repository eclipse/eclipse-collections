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

import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.test.domain.Person;
import org.junit.Assert;
import org.junit.Test;

public class HashingStrategiesTest
{
    @Test
    public void defaultStrategy()
    {
        HashingStrategy<String> stringHashingStrategy = HashingStrategies.defaultStrategy();
        Assert.assertEquals("TEST".hashCode(), stringHashingStrategy.computeHashCode("TEST"));
        Assert.assertEquals("1TeSt1".hashCode(), stringHashingStrategy.computeHashCode("1TeSt1"));
        Assert.assertTrue(stringHashingStrategy.equals("lowercase", "lowercase"));
        Assert.assertFalse(stringHashingStrategy.equals("lowercase", "LOWERCASE"));
        Assert.assertFalse(stringHashingStrategy.equals("12321", "abcba"));
    }

    @Test
    public void nullSafeStrategy()
    {
        HashingStrategy<Integer> integerHashingStrategy =
                HashingStrategies.nullSafeHashingStrategy(HashingStrategies.defaultStrategy());

        Assert.assertEquals(0, integerHashingStrategy.computeHashCode(null));
        Assert.assertEquals(5, integerHashingStrategy.computeHashCode(5));

        Assert.assertTrue(integerHashingStrategy.equals(null, null));
        Assert.assertFalse(integerHashingStrategy.equals(null, 1));
        Assert.assertFalse(integerHashingStrategy.equals(1, null));
        Assert.assertTrue(integerHashingStrategy.equals(1, 1));
    }

    @Test
    public void nullSafeFromFunction()
    {
        Person john = new Person("John", "Smith");
        Person jane = new Person("Jane", "Smith");
        Person nullLast = new Person("Jane", null);
        Person nullFirst = new Person(null, "Smith");

        HashingStrategy<Person> lastHashingStrategy = HashingStrategies.nullSafeFromFunction(Person.TO_LAST);
        HashingStrategy<Person> firstHashingStrategy = HashingStrategies.nullSafeFromFunction(Person.TO_FIRST);

        Assert.assertEquals("John".hashCode(), firstHashingStrategy.computeHashCode(john));
        Assert.assertEquals(0, firstHashingStrategy.computeHashCode(nullFirst));
        Assert.assertEquals("Jane".hashCode(), firstHashingStrategy.computeHashCode(nullLast));
        Assert.assertEquals(firstHashingStrategy.computeHashCode(jane), firstHashingStrategy.computeHashCode(nullLast));
        Assert.assertNotEquals(john.hashCode(), firstHashingStrategy.computeHashCode(john));
        Assert.assertFalse(firstHashingStrategy.equals(john, jane));

        Assert.assertEquals("Smith".hashCode(), lastHashingStrategy.computeHashCode(john));
        Assert.assertEquals(0, lastHashingStrategy.computeHashCode(nullLast));
        Assert.assertEquals("Smith".hashCode(), lastHashingStrategy.computeHashCode(nullFirst));
        Assert.assertEquals(lastHashingStrategy.computeHashCode(john), lastHashingStrategy.computeHashCode(nullFirst));
        Assert.assertNotEquals(john.hashCode(), lastHashingStrategy.computeHashCode(john));
        Assert.assertTrue(lastHashingStrategy.equals(john, jane));

        Assert.assertNotEquals(lastHashingStrategy.computeHashCode(john), firstHashingStrategy.computeHashCode(john));
        Assert.assertNotEquals(lastHashingStrategy.computeHashCode(john), firstHashingStrategy.computeHashCode(jane));
        Assert.assertEquals(lastHashingStrategy.computeHashCode(john), lastHashingStrategy.computeHashCode(jane));
    }

    @Test
    public void fromFunction()
    {
        Person john = new Person("John", "Smith");
        Person jane = new Person("Jane", "Smith");
        HashingStrategy<Person> lastHashingStrategy = HashingStrategies.fromFunction(Person.TO_LAST);
        HashingStrategy<Person> firstHashingStrategy = HashingStrategies.fromFunction(Person.TO_FIRST);

        Assert.assertEquals("John".hashCode(), firstHashingStrategy.computeHashCode(john));
        Assert.assertNotEquals(john.hashCode(), firstHashingStrategy.computeHashCode(john));
        Assert.assertFalse(firstHashingStrategy.equals(john, jane));

        Assert.assertEquals("Smith".hashCode(), lastHashingStrategy.computeHashCode(john));
        Assert.assertNotEquals(john.hashCode(), lastHashingStrategy.computeHashCode(john));
        Assert.assertTrue(lastHashingStrategy.equals(john, jane));

        Assert.assertNotEquals(lastHashingStrategy.computeHashCode(john), firstHashingStrategy.computeHashCode(john));
        Assert.assertNotEquals(lastHashingStrategy.computeHashCode(john), firstHashingStrategy.computeHashCode(jane));
        Assert.assertEquals(lastHashingStrategy.computeHashCode(john), lastHashingStrategy.computeHashCode(jane));
    }

    @Test
    public void identityHashingStrategy()
    {
        Person john1 = new Person("John", "Smith");
        Person john2 = new Person("John", "Smith");
        Verify.assertEqualsAndHashCode(john1, john2);

        HashingStrategy<Object> identityHashingStrategy = HashingStrategies.identityStrategy();
        Assert.assertNotEquals(identityHashingStrategy.computeHashCode(john1), identityHashingStrategy.computeHashCode(john2));
        Assert.assertTrue(identityHashingStrategy.equals(john1, john1));
        Assert.assertFalse(identityHashingStrategy.equals(john1, john2));
    }

    @Test
    public void chainedHashingStrategy()
    {
        Person john1 = new Person("John", "Smith");
        Person john2 = new Person("John", "Smith");
        Person john3 = new Person("John", "Doe");

        HashingStrategy<Person> chainedHashingStrategy = HashingStrategies.chain(
                HashingStrategies.fromFunction(Person.TO_FIRST),
                HashingStrategies.fromFunction(Person.TO_LAST));
        Assert.assertTrue(chainedHashingStrategy.equals(john1, john2));

        HashingStrategy<Person> chainedHashingStrategy2 = HashingStrategies.chain(
                HashingStrategies.fromFunction(Person.TO_FIRST));
        Assert.assertEquals("John".hashCode(), chainedHashingStrategy2.computeHashCode(john1));
        Assert.assertTrue(chainedHashingStrategy2.equals(john1, john3));
    }

    @Test
    public void fromFunctionsTwoArgs()
    {
        Person john1 = new Person("John", "Smith");
        Person john2 = new Person("John", "Smith", 10);
        Person john3 = new Person("John", "Doe");

        HashingStrategy<Person> chainedHashingStrategy = HashingStrategies.fromFunctions(Person.TO_FIRST, Person.TO_LAST);
        Assert.assertTrue(chainedHashingStrategy.equals(john1, john2));
        Assert.assertFalse(chainedHashingStrategy.equals(john1, john3));
    }

    @Test
    public void fromFunctionsThreeArgs()
    {
        Person john1 = new Person("John", "Smith");
        Person john2 = new Person("John", "Smith");
        Person john3 = new Person("John", "Doe");
        Person john4 = new Person("John", "Smith", 10);

        HashingStrategy<Person> chainedHashingStrategy = HashingStrategies.fromFunctions(Person.TO_FIRST, Person.TO_LAST, Person.TO_AGE);
        Assert.assertEquals(john1.hashCode(), chainedHashingStrategy.computeHashCode(john1));
        Assert.assertTrue(chainedHashingStrategy.equals(john1, john2));
        Assert.assertFalse(chainedHashingStrategy.equals(john1, john3));
        Assert.assertFalse(chainedHashingStrategy.equals(john1, john4));
    }

    @Test
    public void fromBooleanFunction()
    {
        HashingStrategy<Integer> isEvenHashingStrategy = HashingStrategies.fromBooleanFunction((BooleanFunction<Integer>) anObject -> anObject.intValue() % 2 == 0);

        Assert.assertEquals(Boolean.TRUE.hashCode(), isEvenHashingStrategy.computeHashCode(Integer.valueOf(2)));
        Assert.assertEquals(Boolean.FALSE.hashCode(), isEvenHashingStrategy.computeHashCode(Integer.valueOf(1)));
        Assert.assertTrue(isEvenHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(4)));
        Assert.assertFalse(isEvenHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));
    }

    @Test
    public void fromByteFunction()
    {
        HashingStrategy<Integer> byteFunctionHashingStrategy = HashingStrategies.fromByteFunction((ByteFunction<Integer>) Integer::byteValue);

        Assert.assertEquals(100, byteFunctionHashingStrategy.computeHashCode(Integer.valueOf(100)));
        Assert.assertTrue(byteFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(2)));
        Assert.assertFalse(byteFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));
    }

    @Test
    public void fromCharFunction()
    {
        HashingStrategy<Integer> charFunctionHashingStrategy = HashingStrategies.fromCharFunction((CharFunction<Integer>) anObject -> (char) anObject.intValue());

        Assert.assertEquals(100, charFunctionHashingStrategy.computeHashCode(Integer.valueOf(100)));
        Assert.assertTrue(charFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(2)));
        Assert.assertFalse(charFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));
    }

    @Test
    public void fromDoubleFunction()
    {
        HashingStrategy<Integer> doubleFunctionHashingStrategy = HashingStrategies.fromDoubleFunction((DoubleFunction<Integer>) Integer::doubleValue);

        Assert.assertEquals(Double.valueOf(100).hashCode(), doubleFunctionHashingStrategy.computeHashCode(Integer.valueOf(100)));
        Assert.assertTrue(doubleFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(2)));
        Assert.assertFalse(doubleFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));

        HashingStrategy<Double> doublePassThruFunction = HashingStrategies.fromDoubleFunction(Double::doubleValue);
        Assert.assertEquals(Double.valueOf(Double.NaN).hashCode(), doublePassThruFunction.computeHashCode(Double.NaN));
        Assert.assertNotEquals(Double.valueOf(Double.POSITIVE_INFINITY).hashCode(), doublePassThruFunction.computeHashCode(Double.NaN));
        Assert.assertEquals(Double.valueOf(Double.POSITIVE_INFINITY).hashCode(), doublePassThruFunction.computeHashCode(Double.POSITIVE_INFINITY));
        Assert.assertTrue(doublePassThruFunction.equals(Double.NaN, Double.NaN));
        Assert.assertFalse(doublePassThruFunction.equals(Double.NaN, Double.POSITIVE_INFINITY));
    }

    @Test
    public void fromFloatFunction()
    {
        HashingStrategy<Integer> floatFunctionHashingStrategy = HashingStrategies.fromFloatFunction((FloatFunction<Integer>) Integer::floatValue);

        Assert.assertEquals(Float.valueOf(100).hashCode(), floatFunctionHashingStrategy.computeHashCode(Integer.valueOf(100)));
        Assert.assertTrue(floatFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(2)));
        Assert.assertFalse(floatFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));

        HashingStrategy<Float> floatPassThruFunction = HashingStrategies.fromFloatFunction(Float::floatValue);
        Assert.assertEquals(Float.valueOf(Float.NaN).hashCode(), floatPassThruFunction.computeHashCode(Float.NaN));
        Assert.assertNotEquals(Float.valueOf(Float.POSITIVE_INFINITY).hashCode(), floatPassThruFunction.computeHashCode(Float.NaN));
        Assert.assertEquals(Float.valueOf(Float.POSITIVE_INFINITY).hashCode(), floatPassThruFunction.computeHashCode(Float.POSITIVE_INFINITY));
        Assert.assertTrue(floatPassThruFunction.equals(Float.NaN, Float.NaN));
        Assert.assertFalse(floatPassThruFunction.equals(Float.NaN, Float.POSITIVE_INFINITY));
    }

    @Test
    public void fromIntFunction()
    {
        HashingStrategy<Integer> intFunctionHashingStrategy = HashingStrategies.fromIntFunction((IntFunction<Integer>) Integer::intValue);

        Assert.assertEquals(100, intFunctionHashingStrategy.computeHashCode(Integer.valueOf(100)));
        Assert.assertTrue(intFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(2)));
        Assert.assertFalse(intFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));
    }

    @Test
    public void fromLongFunction()
    {
        HashingStrategy<Integer> longFunctionHashingStrategy = HashingStrategies.fromLongFunction((LongFunction<Integer>) Integer::longValue);

        Assert.assertEquals(Long.valueOf(100).hashCode(), longFunctionHashingStrategy.computeHashCode(Integer.valueOf(100)));
        Assert.assertTrue(longFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(2)));
        Assert.assertFalse(longFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));
    }

    @Test
    public void fromShortFunction()
    {
        HashingStrategy<Integer> shortFunctionHashingStrategy = HashingStrategies.fromShortFunction((ShortFunction<Integer>) Integer::shortValue);

        Assert.assertEquals(100, shortFunctionHashingStrategy.computeHashCode(Integer.valueOf(100)));
        Assert.assertTrue(shortFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(2)));
        Assert.assertFalse(shortFunctionHashingStrategy.equals(Integer.valueOf(2), Integer.valueOf(1)));
    }

    @Test
    public void classIsNonInstantiable()
    {
        Verify.assertClassNonInstantiable(HashingStrategies.class);
    }
}
