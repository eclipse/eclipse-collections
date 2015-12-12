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

public final class Account
{
    private final String name;

    public Account(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
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

        Account account = (Account) o;

        return this.name.equals(account.name);
    }

    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }
}
