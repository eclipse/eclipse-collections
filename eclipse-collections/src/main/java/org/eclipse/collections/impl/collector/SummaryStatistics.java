/*
 * Copyright (c) 2017 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.stream.Collector;

import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Maps;

/**
 * A Summarizer can be used to aggregate statistics for multiple primitive attributes.
 *
 * @since 8.1
 * @deprecated since 8.2 since it will not work with Java 9 natively. Will be removed in 9.0.0.
 */
@Deprecated
public class SummaryStatistics<T> implements Procedure<T>, Externalizable
{
    private static final long serialVersionUID = 1L;

    private ImmutableMap<Object, IntFunction<? super T>> intFunctionsMap = Maps.immutable.empty();
    private ImmutableMap<Object, LongFunction<? super T>> longFunctionsMap = Maps.immutable.empty();
    private ImmutableMap<Object, DoubleFunction<? super T>> doubleFunctionsMap = Maps.immutable.empty();
    private ImmutableMap<Object, SerializableIntSummaryStatistics> intStatisticsMap = Maps.immutable.empty();
    private ImmutableMap<Object, SerializableLongSummaryStatistics> longStatisticsMap = Maps.immutable.empty();
    private ImmutableMap<Object, SerializableDoubleSummaryStatistics> doubleStatisticsMap = Maps.immutable.empty();

    public SummaryStatistics()
    {
    }

    private SummaryStatistics(
            ImmutableMap<Object, IntFunction<? super T>> intFunctions,
            ImmutableMap<Object, LongFunction<? super T>> longFunctions,
            ImmutableMap<Object, DoubleFunction<? super T>> doubleFunctions)
    {
        this.intFunctionsMap = intFunctions;
        this.intStatisticsMap = intFunctions.collectValues((key, value) -> new SerializableIntSummaryStatistics());
        this.longFunctionsMap = longFunctions;
        this.longStatisticsMap = longFunctions.collectValues((key, value) -> new SerializableLongSummaryStatistics());
        this.doubleFunctionsMap = doubleFunctions;
        this.doubleStatisticsMap = doubleFunctions.collectValues((key, value) -> new SerializableDoubleSummaryStatistics());
    }

    public SummaryStatistics<T> addIntFunction(Object key, IntFunction<? super T> function)
    {
        this.intFunctionsMap = this.intFunctionsMap.newWithKeyValue(key, function);
        this.intStatisticsMap = this.intStatisticsMap.newWithKeyValue(key, new SerializableIntSummaryStatistics());
        return this;
    }

    public SummaryStatistics<T> addLongFunction(Object key, LongFunction<? super T> function)
    {
        this.longFunctionsMap = this.longFunctionsMap.newWithKeyValue(key, function);
        this.longStatisticsMap = this.longStatisticsMap.newWithKeyValue(key, new SerializableLongSummaryStatistics());
        return this;
    }

    public SummaryStatistics<T> addDoubleFunction(Object key, DoubleFunction<? super T> function)
    {
        this.doubleFunctionsMap = this.doubleFunctionsMap.newWithKeyValue(key, function);
        this.doubleStatisticsMap = this.doubleStatisticsMap.newWithKeyValue(key, new SerializableDoubleSummaryStatistics());
        return this;
    }

    @Override
    public void value(T each)
    {
        this.intStatisticsMap.forEachKeyValue((key, value) -> value.accept(this.intFunctionsMap.get(key).intValueOf(each)));
        this.longStatisticsMap.forEachKeyValue((key, value) -> value.accept(this.longFunctionsMap.get(key).longValueOf(each)));
        this.doubleStatisticsMap.forEachKeyValue((key, value) -> value.accept(this.doubleFunctionsMap.get(key).doubleValueOf(each)));
    }

    public DoubleSummaryStatistics getDoubleStats(Object name)
    {
        return this.doubleStatisticsMap.get(name);
    }

    public IntSummaryStatistics getIntStats(Object name)
    {
        return this.intStatisticsMap.get(name);
    }

    public LongSummaryStatistics getLongStats(Object name)
    {
        return this.longStatisticsMap.get(name);
    }

    public SummaryStatistics<T> merge(SummaryStatistics<T> summaryStatistics)
    {
        this.doubleStatisticsMap.forEachKeyValue((key, value) -> value.combine(summaryStatistics.getDoubleStats(key)));
        this.intStatisticsMap.forEachKeyValue((key, value) -> value.combine(summaryStatistics.getIntStats(key)));
        this.longStatisticsMap.forEachKeyValue((key, value) -> value.combine(summaryStatistics.getLongStats(key)));
        return this;
    }

    public Collector<T, ?, SummaryStatistics<T>> toCollector()
    {
        return Collector.of(
                () -> new SummaryStatistics<>(this.intFunctionsMap, this.longFunctionsMap, this.doubleFunctionsMap),
                SummaryStatistics::value,
                SummaryStatistics::merge,
                Collector.Characteristics.UNORDERED);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.intFunctionsMap);
        out.writeObject(this.longFunctionsMap);
        out.writeObject(this.doubleFunctionsMap);
        out.writeObject(this.intStatisticsMap);
        out.writeObject(this.longStatisticsMap);
        out.writeObject(this.doubleStatisticsMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.intFunctionsMap = (ImmutableMap<Object, IntFunction<? super T>>) in.readObject();
        this.longFunctionsMap = (ImmutableMap<Object, LongFunction<? super T>>) in.readObject();
        this.doubleFunctionsMap = (ImmutableMap<Object, DoubleFunction<? super T>>) in.readObject();
        this.intStatisticsMap = (ImmutableMap<Object, SerializableIntSummaryStatistics>) in.readObject();
        this.longStatisticsMap = (ImmutableMap<Object, SerializableLongSummaryStatistics>) in.readObject();
        this.doubleStatisticsMap = (ImmutableMap<Object, SerializableDoubleSummaryStatistics>) in.readObject();
    }
}
