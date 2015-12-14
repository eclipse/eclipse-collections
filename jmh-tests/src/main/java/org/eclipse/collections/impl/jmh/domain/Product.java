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

import java.math.BigDecimal;

public final class Product
{
    private final String name;
    private final String category;
    private final double price;

    public Product(String name, String category, double price)
    {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public BigDecimal getPrecisePrice()
    {
        return BigDecimal.valueOf(this.getPrice());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }

        Product account = (Product) o;

        return this.name.equals(account.name);
    }

    public String getCategory()
    {
        return this.category;
    }

    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

    @Override
    public String toString()
    {
        return "Product{"
                + "name='" + this.name + '\''
                + ", category='" + this.category + '\''
                + ", price=" + this.price
                + '}';
    }
}
