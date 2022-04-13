/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.SortedSets;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.Triplet;
import org.eclipse.collections.api.tuple.Twin;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.StringIterate;

/**
 * This class will compare the method signatures between two classes. The comparison
 * can be based on the following method information:
 * <p><ul>
 * <li> Method Name
 * <li> Method Name + Parameter Types
 * <li> Method Name + Return Type
 * <li> Method Name + Parameter Types + Return Type
 * </ul>
 * <p>
 * The operations that can be used to compare the method signatures of two classes are:
 * <p><ul>
 * <li> Intersection
 * <li> Difference
 * <li> Symmetric difference
 * <li> isProperSubsetOf
 * <li> isProperSupersetOf
 * </ul>
 */
public class ClassComparer
{
    private static final String SYMMETRIC_DIFFERENCE = "Symmetric Difference";
    private static final String INTERSECTION = "Intersection";
    private static final String DIFFERENCE = "Difference";
    private final boolean includeParameterTypesInMethods;
    private final boolean includeReturnTypes;
    private final boolean includePackageNames;
    private boolean includeObjectMethods = false;
    private final Appendable appendable;

    public ClassComparer()
    {
        this(false, false, false);
    }

    public ClassComparer(Appendable out)
    {
        this(out, false, false, false);
    }

    public ClassComparer(boolean includeParameterTypesInMethods, boolean includeReturnTypes, boolean includePackageNames)
    {
        this(System.out, includeParameterTypesInMethods, includeReturnTypes, includePackageNames);
    }

    public ClassComparer(
            Appendable out,
            boolean includeParameterTypesInMethods,
            boolean includeReturnTypes,
            boolean includePackageNames)
    {
        this.includeParameterTypesInMethods = includeParameterTypesInMethods;
        this.includeReturnTypes = includeReturnTypes;
        this.includePackageNames = includePackageNames;
        this.appendable = out;
    }

    public static boolean isProperSupersetOf(Class<?> supersetClass, Class<?> subsetClass)
    {
        return ClassComparer.isProperSubsetOf(subsetClass, supersetClass);
    }

    public static boolean isProperSubsetOf(Class<?> subsetClass, Class<?> supersetClass)
    {
        ClassComparer comparer = new ClassComparer(true, true, true);
        return comparer.getMethodNames(subsetClass).isProperSubsetOf(comparer.getMethodNames(supersetClass));
    }

    public Triplet<MutableSortedSet<String>> compare(Class<?> leftClass, Class<?> rightClass)
    {
        MutableSortedSet<String> intersection = this.intersect(leftClass, rightClass);
        MutableSortedSet<String> differenceLeft = this.difference(leftClass, rightClass);
        MutableSortedSet<String> differenceRight = this.difference(rightClass, leftClass);
        return Tuples.triplet(intersection, differenceLeft, differenceRight);
    }

    public MutableSortedSet<String> difference(Class<?> leftClass, Class<?> rightClass)
    {
        MutableSortedSet<String> leftMethods = this.getMethodNames(leftClass);
        MutableSortedSet<String> rightMethods = this.getMethodNames(rightClass);
        return leftMethods.difference(rightMethods);
    }

    public MutableSortedSet<String> printDifference(Class<?> leftClass, Class<?> rightClass)
    {
        MutableSortedSet<String> difference = this.difference(leftClass, rightClass);
        this.output(Tuples.twin(leftClass, rightClass), DIFFERENCE, difference);
        return difference;
    }

    public MutableSortedSet<String> symmetricDifference(Class<?> leftClass, Class<?> rightClass)
    {
        MutableSortedSet<String> leftMethods = this.getMethodNames(leftClass);
        MutableSortedSet<String> rightMethods = this.getMethodNames(rightClass);
        return leftMethods.symmetricDifference(rightMethods);
    }

    public MutableSortedSet<String> printSymmetricDifference(Class<?> leftClass, Class<?> rightClass)
    {
        MutableSortedSet<String> symmetricDifference = this.symmetricDifference(leftClass, rightClass);
        this.output(Tuples.twin(leftClass, rightClass), SYMMETRIC_DIFFERENCE, symmetricDifference);
        return symmetricDifference;
    }

    public MutableSortedSet<String> intersect(Class<?> leftClass, Class<?> rightClass)
    {
        MutableSortedSet<String> leftMethods = this.getMethodNames(leftClass);
        MutableSortedSet<String> rightMethods = this.getMethodNames(rightClass);
        return leftMethods.intersect(rightMethods);
    }

    public MutableSortedSet<String> printIntersection(Class<?> leftClass, Class<?> rightClass)
    {
        MutableSortedSet<String> intersect = this.intersect(leftClass, rightClass);
        this.output(Tuples.twin(leftClass, rightClass), INTERSECTION, intersect);
        return intersect;
    }

    public MutableSortedSet<String> printClass(Class<?> clazz)
    {
        MutableSortedSet<String> methodNames = this.getMethodNames(clazz);
        this.outputTitle("Class: " + (this.includePackageNames ? clazz.getName() : clazz.getSimpleName()));
        this.outputGroupByToString(methodNames);
        return methodNames;
    }

    public Triplet<MutableSortedSet<String>> compareAndPrint(Class<?> leftClass, Class<?> rightClass)
    {
        Triplet<MutableSortedSet<String>> compare = this.compare(leftClass, rightClass);
        Twin<Class<?>> classPair = Tuples.twin(leftClass, rightClass);
        this.output(classPair, INTERSECTION, compare.getOne());
        this.output(classPair, DIFFERENCE, compare.getTwo());
        this.output(Tuples.twin(rightClass, leftClass), DIFFERENCE, compare.getThree());
        return compare;
    }

    private void output(Twin<Class<?>> classPair, String operation, RichIterable<String> strings)
    {
        this.outputTitle(operation + " (" + this.classNames(classPair) + ")");
        this.outputGroupByToString(strings);
    }

    private String classNames(Twin<Class<?>> classPair)
    {
        Class<?> left = classPair.getOne();
        Class<?> right = classPair.getTwo();
        return (this.includePackageNames ? left.getName() : left.getSimpleName())
                + ", "
                + (this.includePackageNames ? right.getName() : right.getSimpleName());
    }

    public MutableSortedSet<String> getMethodNames(Class<?> classOne)
    {
        return ArrayIterate.collectIf(classOne.getMethods(), this::includeMethod, this::methodName, SortedSets.mutable.empty());
    }

    public void includeObjectMethods()
    {
        this.includeObjectMethods = true;
    }

    private boolean includeMethod(Method method)
    {
        return this.includeObjectMethods || !method.getDeclaringClass().equals(Object.class);
    }

    private String methodName(Method method)
    {
        String methodNamePlusParams = this.includeParameterTypesInMethods
                ? method.getName() + "(" + this.parameterNames(method) + ")"
                : method.getName();
        return methodNamePlusParams + (this.includeReturnTypes ? ":" + method.getReturnType().getSimpleName() : "");
    }

    private String parameterNames(Method method)
    {
        return ArrayIterate.collect(method.getParameters(), parm -> parm.getType().getSimpleName())
                .makeString();
    }

    private void outputTitle(String title)
    {
        try
        {
            this.appendable.append(title).append(System.lineSeparator());
            this.appendable.append(StringIterate.repeat('-', title.length())).append(System.lineSeparator());
        }
        catch (IOException e)
        {
            throw new RuntimeException("MethodComparer error in outputTitle", e);
        }
    }

    private void outputGroupByToString(RichIterable<String> methods)
    {
        String output = methods.groupBy(string -> string.charAt(0))
                .keyMultiValuePairsView()
                .toSortedListBy(Pair::getOne)
                .makeString(System.lineSeparator());

        try
        {
            this.appendable.append(output).append(System.lineSeparator());
            this.appendable.append(System.lineSeparator());
        }
        catch (IOException e)
        {
            throw new RuntimeException("MethodComparer error in outputGroupByToString", e);
        }
    }
}
