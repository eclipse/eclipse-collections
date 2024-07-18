/*
 * Copyright (c) 2024 Goldman Sachs and others.
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
import java.util.Iterator;

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
import org.eclipse.collections.api.factory.Lists;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link UnmodifiableMutableCollection}.
 */
public class UnmodifiableMutableCollectionTest
{
    private static final String METALLICA = "Metallica";

    private MutableCollection<String> mutableCollection;
    private MutableCollection<String> unmodifiableCollection;

    @BeforeEach
    public void setUp()
    {
        this.mutableCollection = FastList.<String>newList().with(METALLICA, "Bon Jovi", "Europe", "Scorpions");
        this.unmodifiableCollection = new UnmodifiableMutableCollection<>(this.mutableCollection);
    }

    @Test
    public void delegatingMethods()
    {
        assertEquals(this.mutableCollection.notEmpty(), this.unmodifiableCollection.notEmpty());
        assertEquals(this.mutableCollection.isEmpty(), this.unmodifiableCollection.isEmpty());
        assertEquals(this.mutableCollection.size(), this.unmodifiableCollection.size());
        assertEquals(this.mutableCollection.getFirst(), this.unmodifiableCollection.getFirst());
        assertEquals(this.mutableCollection.getLast(), this.unmodifiableCollection.getLast());
        assertEquals(
                this.mutableCollection.count(ignored6 -> true),
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
                this.unmodifiableCollection.collect(
                        Functions.getStringPassThru(),
                        FastList.newList()));

        Function<String, Collection<String>> flattenFunction = object -> FastList.newListWith(object, object);
        Verify.assertSize(8, this.unmodifiableCollection.flatCollect(flattenFunction));
        Verify.assertSize(8, this.unmodifiableCollection.flatCollect(flattenFunction, FastList.newList()));

        Verify.assertSize(4, this.unmodifiableCollection.collectIf(ignored2 -> true, Functions.getStringPassThru()));
        Verify.assertSize(
                4,
                this.unmodifiableCollection.collectIf(
                        ignored1 -> true,
                        Functions.getStringPassThru(),
                        FastList.newList()));
        assertEquals(METALLICA, this.unmodifiableCollection.detect(StringPredicates.contains("allic")));
        assertEquals(
                "Not found",
                this.unmodifiableCollection.detectIfNone(
                        StringPredicates.contains("donna"),
                        new PassThruFunction0<>("Not found")));
        assertEquals(METALLICA, this.unmodifiableCollection.detectWith(Object::equals, METALLICA));
        assertEquals("Not found", this.unmodifiableCollection.detectWithIfNone(Object::equals, "Madonna",
                new PassThruFunction0<>("Not found")));
        assertEquals(4, this.unmodifiableCollection.count(ignored -> true));
        assertEquals(1, this.unmodifiableCollection.countWith(Object::equals, METALLICA));
        assertTrue(this.unmodifiableCollection.anySatisfy(StringPredicates.contains("allic")));
        assertTrue(this.unmodifiableCollection.anySatisfyWith(Object::equals, METALLICA));
        assertTrue(this.unmodifiableCollection.allSatisfy(Predicates.notNull()));
        assertTrue(this.unmodifiableCollection.allSatisfyWith((ignored1, ignored2) -> true, ""));
        assertEquals(this.mutableCollection, this.unmodifiableCollection.toList());
        Verify.assertListsEqual(
                Lists.mutable.of("Bon Jovi", "Europe", METALLICA, "Scorpions"),
                this.unmodifiableCollection.toSortedList());
        Verify.assertListsEqual(
                Lists.mutable.of("Scorpions", METALLICA, "Europe", "Bon Jovi"),
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
        assertEquals(expectedBooleans, unmodifiable.collectBoolean(PrimitiveFunctions.integerIsPositive()));
        assertEquals(BooleanArrayList.newListWith(true, true, true, true), expectedBooleans);

        MutableByteCollection expectedBytes = mutable.collectByte(PrimitiveFunctions.unboxIntegerToByte());
        assertEquals(expectedBytes, unmodifiable.collectByte(PrimitiveFunctions.unboxIntegerToByte()));
        assertEquals(ByteArrayList.newListWith((byte) 1, (byte) 2, (byte) 3, (byte) 4), expectedBytes);

        MutableCharCollection expectedChars = mutable.collectChar(PrimitiveFunctions.unboxIntegerToChar());
        assertEquals(expectedChars, unmodifiable.collectChar(PrimitiveFunctions.unboxIntegerToChar()));
        assertEquals(CharArrayList.newListWith((char) 1, (char) 2, (char) 3, (char) 4), expectedChars);

        MutableDoubleCollection expectedDoubles = mutable.collectDouble(PrimitiveFunctions.unboxIntegerToDouble());
        assertEquals(expectedDoubles, unmodifiable.collectDouble(PrimitiveFunctions.unboxIntegerToDouble()));
        assertEquals(DoubleArrayList.newListWith(1.0d, 2.0d, 3.0d, 4.0d), expectedDoubles);

        MutableFloatCollection expectedFloats = mutable.collectFloat(PrimitiveFunctions.unboxIntegerToFloat());
        assertEquals(expectedFloats, unmodifiable.collectFloat(PrimitiveFunctions.unboxIntegerToFloat()));
        assertEquals(FloatArrayList.newListWith(1.0f, 2.0f, 3.0f, 4.0f), expectedFloats);

        MutableIntCollection expectedInts = mutable.collectInt(PrimitiveFunctions.unboxIntegerToInt());
        assertEquals(expectedInts, unmodifiable.collectInt(PrimitiveFunctions.unboxIntegerToInt()));
        assertEquals(IntArrayList.newListWith(1, 2, 3, 4), expectedInts);

        MutableLongCollection expectedLongs = mutable.collectLong(PrimitiveFunctions.unboxIntegerToLong());
        assertEquals(expectedLongs, unmodifiable.collectLong(PrimitiveFunctions.unboxIntegerToLong()));
        assertEquals(LongArrayList.newListWith(1L, 2L, 3L, 4L), expectedLongs);

        MutableShortCollection expectedShorts = mutable.collectShort(PrimitiveFunctions.unboxIntegerToShort());
        assertEquals(expectedShorts, unmodifiable.collectShort(PrimitiveFunctions.unboxIntegerToShort()));
        assertEquals(ShortArrayList.newListWith((short) 1, (short) 2, (short) 3, (short) 4), expectedShorts);
    }

    @Test
    public void nullCollection()
    {
        assertThrows(NullPointerException.class, () -> new UnmodifiableMutableCollection<>(null));
    }

    @Test
    public void add()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.add("Madonna"));
    }

    @Test
    public void remove()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.remove(METALLICA));
    }

    @Test
    public void addAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.addAll(FastList.<String>newList().with("Madonna")));
    }

    @Test
    public void removeAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.removeAll(FastList.<String>newList().with(METALLICA)));
    }

    @Test
    public void retainAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.retainAll(FastList.<String>newList().with(METALLICA)));
    }

    @Test
    public void clear()
    {
        assertThrows(UnsupportedOperationException.class, this.unmodifiableCollection::clear);
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
        assertEquals(
                FastList.newListWith(">M", ">B", ">E", ">S"),
                this.unmodifiableCollection.collectWith(function, ">"));
        assertEquals(FastList.newListWith("*M", "*B", "*E", "*S"), this.unmodifiableCollection.collectWith(function, "*", FastList.newList()));
    }

    @Test
    public void injectInto()
    {
        Function2<String, String, String> function = (injectValue, band) -> injectValue + band.charAt(0);
        assertEquals(">MBES", this.unmodifiableCollection.injectInto(">", function));
    }

    @Test
    public void injectIntoWith()
    {
        Function3<String, String, String, String> function =
                (injectValue, band, parameter) -> injectValue + band.charAt(0) + parameter;
        assertEquals(">M*B*E*S*", this.unmodifiableCollection.injectIntoWith(">", function, "*"));
    }

    @Test
    public void removeIf()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.removeIf(Predicates.notNull()));
    }

    @Test
    public void removeIfWith()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.removeIfWith((ignored1, ignored2) -> true, METALLICA));
    }

    @Test
    public void with()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.with(METALLICA));
    }

    @Test
    public void withAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.withAll(this.mutableCollection));
    }

    @Test
    public void without()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.without(METALLICA));
    }

    @Test
    public void withoutAll()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.unmodifiableCollection.withoutAll(this.mutableCollection));
    }

    @Test
    public void iterator()
    {
        Counter counter = new Counter();
        for (Iterator<String> iterator = this.unmodifiableCollection.iterator(); iterator.hasNext(); )
        {
            iterator.next();
            counter.increment();
        }
        assertEquals(4, counter.getCount());
    }

    @Test
    public void forEach()
    {
        Counter counter = new Counter();
        this.unmodifiableCollection.forEach(Procedures.cast(band -> counter.increment()));
        assertEquals(4, counter.getCount());
    }

    @Test
    public void forEachWith()
    {
        StringBuilder buf = new StringBuilder();
        this.unmodifiableCollection.forEachWith((band, param) -> buf.append(param).append('<').append(band).append('>'), "GreatBand");
        assertEquals("GreatBand<Metallica>GreatBand<Bon Jovi>GreatBand<Europe>GreatBand<Scorpions>", buf.toString());
    }

    @Test
    public void forEachWithIndex()
    {
        Counter counter = new Counter();
        this.unmodifiableCollection.forEachWithIndex((band, index) -> counter.add(index));
        assertEquals(6, counter.getCount());
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
        assertEquals(expected, multimap.toMap());

        Multimap<Boolean, Integer> multimap2 = list.groupBy(isOddFunction, FastListMultimap.newMultimap());
        assertEquals(expected, multimap2.toMap());
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
        assertEquals(iList(1, 3, 5), numbers.selectInstancesOf(Integer.class));
        assertEquals(iList(1, 2.0, 3, 4.0, 5), numbers.selectInstancesOf(Number.class));
    }
}
