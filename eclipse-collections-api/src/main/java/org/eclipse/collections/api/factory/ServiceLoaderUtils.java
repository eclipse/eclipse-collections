/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public final class ServiceLoaderUtils
{
    private ServiceLoaderUtils()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T> T loadServiceClass(Class<T> serviceClass)
    {
        List<T> factories = new ArrayList<>();
        for (T factory : ServiceLoader.load(serviceClass))
        {
            factories.add(factory);
        }
        if (factories.isEmpty())
        {
            String message = "Could not find any implementations of " + serviceClass.getSimpleName() + ". Check that eclipse-collections.jar is on the classpath and that its META-INF/services directory is intact.";
            return ServiceLoaderUtils.createProxyInstance(serviceClass, message);
        }
        if (factories.size() > 1)
        {
            String message = String.format(
                    "Found multiple implementations of %s on the classpath. Check that there is only one copy of eclipse-collections.jar on the classpath. Found implementations: %s.",
                    serviceClass.getSimpleName(),
                    factories.stream()
                            .map(T::getClass)
                            .map(Class::getSimpleName)
                            .collect(Collectors.joining(", ")));
            return ServiceLoaderUtils.createProxyInstance(serviceClass, message);
        }
        return factories.get(0);
    }

    private static <T> T createProxyInstance(Class<T> serviceClass, String message)
    {
        InvocationHandler handler = new ThrowingInvocationHandler(message);
        Object proxyInstance = Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                handler);
        return serviceClass.cast(proxyInstance);
    }
}
