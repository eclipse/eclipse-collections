/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;

import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.ListIterate;
import org.eclipse.collections.impl.utility.MapIterate;

/**
 * A Summarizer can be used to aggregate statistics for multiple primitive attributes.
 */
public class SummaryStatistics<T> implements Procedure<T>
{
    private final MutableMap<String, Pair<DoubleFunction<? super T>, DoubleSummaryStatistics>> doubleMap = Maps.mutable.empty();
    private final MutableMap<String, Pair<IntFunction<? super T>, IntSummaryStatistics>> intMap = Maps.mutable.empty();
    private final MutableMap<String, Pair<LongFunction<? super T>, LongSummaryStatistics>> longMap = Maps.mutable.empty();

    public SummaryStatistics()
    {
    }

    public SummaryStatistics(
            Map<String, IntFunction<? super T>> intFunctions,
            Map<String, LongFunction<? super T>> longFunctions,
            Map<String, DoubleFunction<? super T>> doubleFunctions)
    {
        this();
        MapIterate.forEachKeyValue(intFunctions, this::addIntFunction);
        MapIterate.forEachKeyValue(longFunctions, this::addLongFunction);
        MapIterate.forEachKeyValue(doubleFunctions, this::addDoubleFunction);
    }

    public SummaryStatistics(
            List<IntFunction<? super T>> intFunctions,
            List<LongFunction<? super T>> longFunctions,
            List<DoubleFunction<? super T>> doubleFunctions)
    {
        this();
        ListIterate.forEachWithIndex(intFunctions, (each, index) -> this.addIntFunction(Integer.toString(index), each));
        ListIterate.forEachWithIndex(longFunctions, (each, index) -> this.addLongFunction(Integer.toString(index), each));
        ListIterate.forEachWithIndex(doubleFunctions, (each, index) -> this.addDoubleFunction(Integer.toString(index), each));
    }

    public SummaryStatistics<T> addIntFunction(String name, IntFunction<? super T> function)
    {
        this.intMap.put(name, Tuples.pair(function, new IntSummaryStatistics()));
        return this;
    }

    public SummaryStatistics<T> addLongFunction(String name, LongFunction<? super T> function)
    {
        this.longMap.put(name, Tuples.pair(function, new LongSummaryStatistics()));
        return this;
    }

    public SummaryStatistics<T> addDoubleFunction(String name, DoubleFunction<? super T> function)
    {
        this.doubleMap.put(name, Tuples.pair(function, new DoubleSummaryStatistics()));
        return this;
    }

    @Override
    public void value(T each)
    {
        this.doubleMap.each(pair -> pair.getTwo().accept(pair.getOne().doubleValueOf(each)));
        this.intMap.each(pair -> pair.getTwo().accept(pair.getOne().intValueOf(each)));
        this.longMap.each(pair -> pair.getTwo().accept(pair.getOne().longValueOf(each)));
    }

    public DoubleSummaryStatistics getDoubleStats(String name)
    {
        return this.doubleMap.get(name).getTwo();
    }

    public IntSummaryStatistics getIntStats(String name)
    {
        return this.intMap.get(name).getTwo();
    }

    public LongSummaryStatistics getLongStats(String name)
    {
        return this.longMap.get(name).getTwo();
    }

    public SummaryStatistics<T> merge(SummaryStatistics<T> summaryStatistics)
    {
        this.doubleMap.forEachKeyValue((k, v) -> v.getTwo().combine(summaryStatistics.getDoubleStats(k)));
        this.intMap.forEachKeyValue((k, v) -> v.getTwo().combine(summaryStatistics.getIntStats(k)));
        this.longMap.forEachKeyValue((k, v) -> v.getTwo().combine(summaryStatistics.getLongStats(k)));
        return this;
    }
}
