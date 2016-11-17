/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.test.junit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.internal.MethodSorter;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

public class Java8TestClass extends TestClass
{
    /**
     * Creates a {@code TestClass} wrapping {@code klass}. Each time this
     * constructor executes, the class is scanned for annotations, which can be
     * an expensive process (we hope in future JDK's it will not be.) Therefore,
     * try to share instances of {@code TestClass} where possible.
     */
    public Java8TestClass(Class<?> klass)
    {
        super(klass);
    }

    @Override
    protected void scanAnnotatedMembers(Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations, Map<Class<? extends Annotation>, List<FrameworkField>> fieldsForAnnotations)
    {
        super.scanAnnotatedMembers(methodsForAnnotations, fieldsForAnnotations);

        this.getInterfaceMethodsForAnnotations(methodsForAnnotations, this.getJavaClass());
    }

    private void getInterfaceMethodsForAnnotations(Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations, Class<?> clazz)
    {
        List<Class<?>> interfaces = Java8TestClass.getInterfaces(this.getJavaClass());
        for (Class<?> eachInterface : interfaces)
        {
            for (Method eachMethod : MethodSorter.getDeclaredMethods(eachInterface))
            {
                if (!Modifier.isAbstract(eachMethod.getModifiers()))
                {
                    TestClass.addToAnnotationLists(new FrameworkMethod(eachMethod), methodsForAnnotations);
                }
            }
        }
    }

    private static List<Class<?>> getInterfaces(Class<?> testClass)
    {
        LinkedList<Class<?>> queue = new LinkedList<>();
        queue.add(testClass);

        Set<Class<?>> visited = new HashSet<>();
        visited.add(testClass);

        List<Class<?>> results = new ArrayList<>();
        while (!queue.isEmpty())
        {
            Class<?> anInterface = queue.poll();
            results.add(anInterface);

            Class<?>[] parentInterfaces = anInterface.getInterfaces();
            for (Class<?> parentInterface : parentInterfaces)
            {
                if (!visited.contains(parentInterface))
                {
                    visited.add(parentInterface);
                    queue.add(parentInterface);
                }
            }
        }
        return results;
    }
}
