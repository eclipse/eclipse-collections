/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.test.domain;

import java.io.Serializable;

import org.eclipse.collections.api.block.function.Function;

public final class Person implements Comparable<Person>, Serializable
{
    public static final Function<Person, String> TO_FIRST = person -> person.firstName;

    public static final Function<Person, String> TO_LAST = person -> person.lastName;

    public static final Function<Person, Integer> TO_AGE = person -> person.age;
    private static final long serialVersionUID = 1L;

    private final String firstName;
    private final String lastName;
    private final int age;

    public Person(String firstName, String lastName)
    {
        this(firstName, lastName, 100);
    }

    public Person(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

        Person person = (Person) o;

        if (this.age != person.age)
        {
            return false;
        }
        if (!this.firstName.equals(person.firstName))
        {
            return false;
        }
        if (!this.lastName.equals(person.lastName))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = this.firstName.hashCode();
        result = 31 * result + this.lastName.hashCode();
        result = 31 * result + this.age;
        return result;
    }

    @Override
    public int compareTo(Person other)
    {
        return this.lastName.compareTo(other.lastName);
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public int getAge()
    {
        return this.age;
    }

    @Override
    public String toString()
    {
        return "Person{first='" + this.firstName
                + "', last='" + this.lastName
                + "', age=" + this.age + '}';
    }
}
