/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.Counter;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.factory.StringPredicates;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;

/**
 * JUnit test for {@link UnmodifiableMutableCollection}.
 */
public class UnmodifiableMutableCollectionTest
{
    private static final String METALLICA = "Metallica";

    private MutableCollection<String> mutableCollection;
    private MutableCollection<String> unmodifiableCollection;

    @Before
    public void setUp()
    {
        this.mutableCollection = FastList.<String>newList().with(METALLICA, "Bon Jovi", "Europe", "Scorpions");
        this.unmodifiableCollection = new UnmodifiableMutableCollection<>(this.mutableCollection);
    }

    @Test
    public void delegatingMethods()
    {
        Assert.assertEquals(this.mutableCollection.notEmpty(), this.unmodifiableCollection.notEmpty());
        Assert.assertEquals(this.mutableCollection.isEmpty(), this.unmodifiableCollection.isEmpty());
        Assert.assertEquals(this.mutableCollection.size(), this.unmodifiableCollection.size());
        Assert.assertEquals(this.mutableCollection.getFirst(), this.unmodifiableCollection.getFirst());
        Assert.assertEquals(this.mutableCollection.getLast(), this.unmodifiableCollection.getLast());
        Assert.assertEquals(this.mutableCollection.count(ignored6 -> true),
                this.unmodifiableCollection.count(ignored5 -> true));
        Verify.assertSize(4, this.unmodifiableCollection.select(ignored4 -> true));
        Verify.assertSize(4, this.unmodifiableCollection.select(ignored3 -> true, FastList.newList()));
        Verify.assertSize(1, this.unmodifiableCollection.selectWith(Object::equals, METALLICA));
        Verify.assertSize(
                1,
                this.unmodifiableCollection.selectWith(
                        Object::equals,
                        METALLICA,
                        FastList.newList()));
        Verify.assertSize(2, this.unmodifiableCollection.reject(StringPredicates.contains("p")));
        Verify.assertSize(
                2,
                this.unmodifiableCollection.reject(StringPredicates.contains("p"), FastList.newList()));
        Verify.assertSize(3, this.unmodifiableCollection.rejectWith(Object::equals, METALLICA));
        Verify.assertSize(
                3,
                this.unmodifiableCollection.rejectWith(
                        Object::equals,
                        METALLICA,
                        FastList.newList()));
        Verify.assertSize(4, this.unmodifiableCollection.collect(Functions.getStringPassThru()));
        Verify.assertSize(
                4,
                this.unmodifiableCollection.collect(Functions.getStringPassThru(),
                        FastList.newList()));

        Function<String, Collection<String>> flattenFunction = object -> FastList.newListWith(object, object);
        Verify.assertSize(8, this.unmodifiableCollection.flatCollect(flattenFunction));
        Verify.assertSize(8, this.unmodifiableCollection.flatCollect(flattenFunction, FastList.newList()));

        Verify.assertSize(4, this.unmodifiableCollection.collectIf(ignored2 -> true, Functions.getStringPassThru()));
        Verify.assertSize(
                4,
                this.unmodifiableCollection.collectIf(ignored1 -> true,
                        Functions.getStringPassThru(),
                        FastList.newList()));
        Assert.assertEquals(METALLICA, this.unmodifiableCollection.detect(StringPredicates.contains("allic")));
        Assert.assertEquals("Not found", this.unmodifiableCollection.detectIfNone(StringPredicates.contains("donna"),
                new PassThruFunction0<>("Not found")));
        Assert.assertEquals(METALLICA, this.unmodifiableCollection.detectWith(Object::equals, METALLICA));
        Assert.assertEquals("Not found", this.unmodifiableCollection.detectWithIfNone(Object::equals, "Madonna",
                new PassThruFunction0<>("Not found")));
        Assert.assertEquals(4, this.unmodifiableCollection.count(ignored -> true));
        Assert.assertEquals(1, this.unmodifiableCollection.countWith(Object::equals, METALLICA));
        Assert.assertTrue(this.unmodifiableCollection.anySatisfy(StringPredicates.contains("allic")));
        Assert.assertTrue(this.unmodifiableCollection.anySatisfyWith(Object::equals, METALLICA));
        Assert.assertTrue(this.unmodifiableCollection.allSatisfy(Predicates.notNull()));
        Assert.assertTrue(this.unmodifiableCollection.allSatisfyWith((ignored1, ignored2) -> true, ""));
        Assert.assertEquals(this.mutableCollection, this.unmodifiableCollection.toList());
        Verify.assertListsEqual(Lists.mutable.of("Bon Jovi", "Europe", METALLICA, "Scorpions"),
                this.unmodifiableCollection.toSortedList());
        Verify.assertListsEqual(Lists.mutable.of("Scorpions", METALLICA, "Europe", "Bon Jovi"),
                this.unmodifiableCollection.toSortedList(Collections.reverseOrder()));
        Verify.assertSize(4, this.unmodifiableCollection.toSet());
        Verify.assertSize(4, this.unmodifiableCollection.toMap(Functions.getStringPassThru(), Functions.getStringPassThru()));
    }

    @Test
    public void delegatingCollectPrimitiveMethods()
    {
        MutableCollection<Integer> mutable = Interval.oneTo(4).toList();
        MutableCollection<Integer> unmodifiable = new UnmodifiableMutableCollection<>(mutable);

        MutableBooleanCollection expectedBooleans = mutable.collectBoolean(PrimitiveFunctions.integerIsPositive());
        Assert.assertEquals(expectedBooleans, unmodifiable.collectBoolean(PrimitiveFunctions.integerIsPositive()));
        Assert.assertEquals(BooleanArrayList.newListWith(true, true, true, true), expectedBooleans);

        MutableByteCollection expectedBytes = mutable.collectByte(PrimitiveFunctions.unboxIntegerToByte());
        Assert.assertEquals(expectedBytes, unmodifiable.collectByte(PrimitiveFunctions.unboxIntegerToByte()));
        Assert.assertEquals(ByteArrayList.newListWith((byte) 1, (byte) 2, (byte) 3, (byte) 4), expectedBytes);

        MutableCharCollection expectedChars = mutable.collectChar(PrimitiveFunctions.unboxIntegerToChar());
        Assert.assertEquals(expectedChars, unmodifiable.collectChar(PrimitiveFunctions.unboxIntegerToChar()));
        Assert.assertEquals(CharArrayList.newListWith((char) 1, (char) 2, (char) 3, (char) 4), expectedChars);

        MutableDoubleCollection expectedDoubles = mutable.collectDouble(PrimitiveFunctions.unboxIntegerToDouble());
        Assert.assertEquals(expectedDoubles, unmodifiable.collectDouble(PrimitiveFunctions.unboxIntegerToDouble()));
        Assert.assertEquals(DoubleArrayList.newListWith(1.0d, 2.0d, 3.0d, 4.0d), expectedDoubles);

        MutableFloatCollection expectedFloats = mutable.collectFloat(PrimitiveFunctions.unboxIntegerToFloat());
        Assert.assertEquals(expectedFloats, unmodifiable.collectFloat(PrimitiveFunctions.unboxIntegerToFloat()));
        Assert.assertEquals(FloatArrayList.newListWith(1.0f, 2.0f, 3.0f, 4.0f), expectedFloats);

        MutableIntCollection expectedInts = mutable.collectInt(PrimitiveFunctions.unboxIntegerToInt());
        Assert.assertEquals(expectedInts, unmodifiable.collectInt(PrimitiveFunctions.unboxIntegerToInt()));
        Assert.assertEquals(IntArrayList.newListWith(1, 2, 3, 4), expectedInts);

        MutableLongCollection expectedLongs = mutable.collectLong(PrimitiveFunctions.unboxIntegerToLong());
        Assert.assertEquals(expectedLongs, unmodifiable.collectLong(PrimitiveFunctions.unboxIntegerToLong()));
        Assert.assertEquals(LongArrayList.newListWith(1L, 2L, 3L, 4L), expectedLongs);

        MutableShortCollection expectedShorts = mutable.collectShort(PrimitiveFunctions.unboxIntegerToShort());
        Assert.assertEquals(expectedShorts, unmodifiable.collectShort(PrimitiveFunctions.unboxIntegerToShort()));
        Assert.assertEquals(ShortArrayList.newListWith((short) 1, (short) 2, (short) 3, (short) 4), expectedShorts);
    }

    @Test
    public void nullCollection()
    {
        Verify.assertThrows(NullPointerException.class, () -> new UnmodifiableMutableCollection<>(null));
    }

    @Test
    public void add()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.add("Madonna"));
    }

    @Test
    public void remove()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.remove(METALLICA));
    }

    @Test
    public void addAll()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.addAll(FastList.<String>newList().with("Madonna")));
    }

    @Test
    public void removeAll()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.removeAll(FastList.<String>newList().with(METALLICA)));
    }

    @Test
    public void retainAll()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.retainAll(FastList.<String>newList().with(METALLICA)));
    }

    @Test
    public void clear()
    {
        Verify.assertThrows(UnsupportedOperationException.class, this.unmodifiableCollection::clear);
    }

    @Test
    public void transparencyOfMutableChanges()
    {
        this.mutableCollection.remove(METALLICA);
        Verify.assertSize(this.mutableCollection.size(), this.unmodifiableCollection);
    }

    @Test
    public void collectWith()
    {
        Function2<String, String, String> function = (band, parameter) -> parameter + band.charAt(0);
        Assert.assertEquals(
                FastList.newListWith(">M", ">B", ">E", ">S"),
                this.unmodifiableCollection.collectWith(function, ">"));
        Assert.assertEquals(FastList.newListWith("*M", "*B", "*E", "*S"), this.unmodifiableCollection.collectWith(function, "*", FastList.newList()));
    }

    @Test
    public void injectInto()
    {
        Function2<String, String, String> function = (injectValue, band) -> injectValue + band.charAt(0);
        Assert.assertEquals(">MBES", this.unmodifiableCollection.injectInto(">", function));
    }

    @Test
    public void injectIntoWith()
    {
        Function3<String, String, String, String> function =
                (injectValue, band, parameter) -> injectValue + band.charAt(0) + parameter;
        Assert.assertEquals(">M*B*E*S*", this.unmodifiableCollection.injectIntoWith(">", function, "*"));
    }

    @Test
    public void removeIf()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.removeIf(Predicates.notNull()));
    }

    @Test
    public void removeIfWith()
    {
        Verify.assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.removeIfWith((ignored1, ignored2) -> true, METALLICA));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void with()
    {
        this.unmodifiableCollection.with(METALLICA);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withAll()
    {
        this.unmodifiableCollection.withAll(this.mutableCollection);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void without()
    {
        this.unmodifiableCollection.without(METALLICA);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withoutAll()
    {
        this.unmodifiableCollection.withoutAll(this.mutableCollection);
    }

    @Test
    public void iterator()
    {
        Counter counter = new Counter();
        for (Object each : this.unmodifiableCollection)
        {
            counter.increment();
        }
        Assert.assertEquals(4, counter.getCount());
    }

    @Test
    public void forEach()
    {
        Counter counter = new Counter();
        this.unmodifiableCollection.forEach(Procedures.cast(band -> counter.increment()));
        Assert.assertEquals(4, counter.getCount());
    }

    @Test
    public void forEachWith()
    {
        StringBuilder buf = new StringBuilder();
        this.unmodifiableCollection.forEachWith((band, param) -> buf.append(param).append('<').append(band).append('>'), "GreatBand");
        Assert.assertEquals("GreatBand<Metallica>GreatBand<Bon Jovi>GreatBand<Europe>GreatBand<Scorpions>", buf.toString());
    }

    @Test
    public void forEachWithIndex()
    {
        Counter counter = new Counter();
        this.unmodifiableCollection.forEachWithIndex((band, index) -> counter.add(index));
        Assert.assertEquals(6, counter.getCount());
    }

    @Test
    public void selectAndRejectWith()
    {
        Twin<MutableList<String>> twin =
                this.unmodifiableCollection.selectAndRejectWith(Object::equals, METALLICA);
        Verify.assertSize(1, twin.getOne());
        Verify.assertSize(3, twin.getTwo());
    }

    @Test
    public void groupBy()
    {
        RichIterable<Integer> list = this.newWith(1, 2, 3, 4, 5, 6, 7);
        Function<Integer, Boolean> isOddFunction = object -> IntegerPredicates.isOdd().accept(object);

        MutableMap<Boolean, RichIterable<Integer>> expected =
                UnifiedMap.newWithKeysValues(
                        Boolean.TRUE, FastList.newListWith(1, 3, 5, 7),
                        Boolean.FALSE, FastList.newListWith(2, 4, 6));

        Multimap<Boolean, Integer> multimap = list.groupBy(isOddFunction);
        Assert.assertEquals(expected, multimap.toMap());

        Multimap<Boolean, Integer> multimap2 = list.groupBy(isOddFunction, FastListMultimap.newMultimap());
        Assert.assertEquals(expected, multimap2.toMap());
    }

    private <T> UnmodifiableMutableCollection<T> newWith(T... elements)
    {
        return new UnmodifiableMutableCollection<>(FastList.newListWith(elements));
    }

    @Test
    public void toSortedSet()
    {
        this.unmodifiableCollection = this.newWith("2", "4", "1", "3");
        MutableSortedSet<String> set = this.unmodifiableCollection.toSortedSet();
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith("1", "2", "3", "4"), set);
    }

    @Test
    public void toSortedSet_with_comparator()
    {
        this.unmodifiableCollection = this.newWith("2", "4", "4", "2", "1", "4", "1", "3");
        MutableSortedSet<String> set = this.unmodifiableCollection.toSortedSet(Collections.reverseOrder());
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(Collections.reverseOrder(), "1", "2", "3", "4"), set);
    }

    @Test
    public void toSortedSetBy()
    {
        this.unmodifiableCollection = this.newWith("2", "4", "1", "3");
        MutableSortedSet<String> set = this.unmodifiableCollection.toSortedSetBy(Functions.getStringToInteger());
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith("1", "2", "3", "4"), set);
    }

    @Test
    public void selectInstancesOf()
    {
        MutableCollection<Number> numbers = UnmodifiableMutableCollection.of(FastList.newListWith(1, 2.0, 3, 4.0, 5));
        Assert.assertEquals(iList(1, 3, 5), numbers.selectInstancesOf(Integer.class));
        Assert.assertEquals(iList(1, 2.0, 3, 4.0, 5), numbers.selectInstancesOf(Number.class));
    }
}
