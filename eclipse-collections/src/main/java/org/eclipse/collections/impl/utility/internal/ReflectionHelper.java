/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.utility.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.MapIterate;

/**
 * A utility/helper class for working with Classes and Reflection.
 */
public final class ReflectionHelper
{
    /**
     * @deprecated in 2.0. Will become private in a future version.
     */
    @SuppressWarnings("rawtypes")
    @Deprecated
    public static final Class[] EMPTY_CLASS_ARRAY = {};

    /**
     * Mapping of iterator wrapper classes to iterator types
     */
    private static final ImmutableMap<Class<?>, Class<?>> WRAPPER_TO_PRIMATIVES = UnifiedMap.<Class<?>, Class<?>>newMapWith(
            Tuples.twin(Short.class, short.class),
            Tuples.twin(Boolean.class, boolean.class),
            Tuples.twin(Byte.class, byte.class),
            Tuples.twin(Character.class, char.class),
            Tuples.twin(Integer.class, int.class),
            Tuples.twin(Float.class, float.class),
            Tuples.twin(Long.class, long.class),
            Tuples.twin(Double.class, double.class)).toImmutable();

    private static final ImmutableMap<Class<?>, Class<?>> PRIMATIVES_TO_WRAPPERS = MapIterate.reverseMapping(WRAPPER_TO_PRIMATIVES.castToMap()).toImmutable();

    private ReflectionHelper()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    // These are special methods that will not produce error messages if the getter method is not found

    public static <T> Constructor<T> getConstructor(Class<T> instantiable, Class<?>... constructorParameterTypes)
    {
        try
        {
            return instantiable.getConstructor(constructorParameterTypes);
        }
        catch (NoSuchMethodException ignored)
        {
            return ReflectionHelper.searchForConstructor(instantiable, constructorParameterTypes);
        }
    }

    private static <T> Constructor<T> searchForConstructor(Class<T> instantiable, Class<?>... constructorParameterTypes)
    {
        Constructor<?>[] candidates = instantiable.getConstructors();
        for (Constructor<?> candidate : candidates)
        {
            if (ReflectionHelper.parameterTypesMatch(candidate.getParameterTypes(), constructorParameterTypes))
            {
                return (Constructor<T>) candidate;
            }
        }
        return null;
    }

    public static boolean parameterTypesMatch(Class<?>[] candidateParamTypes, Class<?>... desiredParameterTypes)
    {
        boolean match = candidateParamTypes.length == desiredParameterTypes.length;
        for (int i = 0; i < candidateParamTypes.length && match; i++)
        {
            Class<?> candidateType = candidateParamTypes[i].isPrimitive() && !desiredParameterTypes[i].isPrimitive()
                    ? PRIMATIVES_TO_WRAPPERS.get(candidateParamTypes[i])
                    : candidateParamTypes[i];
            match = candidateType.isAssignableFrom(desiredParameterTypes[i]);
        }
        return match;
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... constructorArguments)
    {
        try
        {
            return constructor.newInstance(constructorArguments);
        }
        catch (InstantiationException | InvocationTargetException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method may return null if the call to create a newInstance() fails.
     */
    public static <T> T newInstance(Class<T> aClass)
    {
        try
        {
            return aClass.getConstructor().newInstance();
        }
        catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasDefaultConstructor(Class<?> aClass)
    {
        try
        {
            Constructor<?> constructor = aClass.getDeclaredConstructor(EMPTY_CLASS_ARRAY);
            return Modifier.isPublic(constructor.getModifiers());
        }
        catch (NoSuchMethodException ignored)
        {
            return false;
        }
    }
}
