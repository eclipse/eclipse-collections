/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.jmh.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PrimitiveIterator;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.eclipse.collections.api.set.Pool;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

public class Positions
{
    private static final int DEFAULT_SIZE = 3_000_000;
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final PrimitiveIterator.OfDouble DOUBLES = RANDOM.ints(1, 100).asDoubleStream().iterator();
    private static final PrimitiveIterator.OfInt INTS = RANDOM.ints(1, 100).iterator();
    private final Pool<Account> accountPool = UnifiedSet.newSet();
    private final Pool<Product> productPool = UnifiedSet.newSet();
    private final Pool<String> stringPool = UnifiedSet.newSet();

    private final FastList<Position> ecPositions;
    private final ArrayList<Position> jdkPositions;

    public Positions()
    {
        this(DEFAULT_SIZE);
    }

    public Positions(int size)
    {
        this.ecPositions = FastList.newWithNValues(size, this::createPosition);
        this.jdkPositions = new ArrayList<>(FastList.newWithNValues(size, this::createPosition));
    }

    public Positions shuffle()
    {
        this.ecPositions.shuffleThis();
        Collections.shuffle(this.jdkPositions);
        return this;
    }

    public Position createPosition()
    {
        String accountName = this.stringPool.put(RandomStringUtils.randomNumeric(5));
        String category = this.stringPool.put(RandomStringUtils.randomAlphabetic(1).toUpperCase());
        String productName = this.stringPool.put(RandomStringUtils.randomNumeric(3));
        Account account = this.accountPool.put(new Account(accountName));
        Product product = this.productPool.put(new Product(productName, category, DOUBLES.nextDouble()));
        return new Position(account, product, INTS.nextInt());
    }

    public FastList<Position> getEcPositions()
    {
        return this.ecPositions;
    }

    public ArrayList<Position> getJdkPositions()
    {
        return this.jdkPositions;
    }
}
