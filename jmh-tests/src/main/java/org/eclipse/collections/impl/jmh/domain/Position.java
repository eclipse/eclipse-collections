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

public final class Position
{
    private final Account account;
    private final Product product;
    private final int quantity;

    public Position(Account account, Product product, int quantity)
    {
        this.account = account;
        this.product = product;
        this.quantity = quantity;
    }

    public Account getAccount()
    {
        return this.account;
    }

    public Product getProduct()
    {
        return this.product;
    }

    public String getCategory()
    {
        return this.product.getCategory();
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public double getMarketValue()
    {
        return this.quantity * this.product.getPrice();
    }

    public BigDecimal getPreciseMarketValue()
    {
        return BigDecimal.valueOf(this.quantity).multiply(this.product.getPrecisePrice());
    }
}
