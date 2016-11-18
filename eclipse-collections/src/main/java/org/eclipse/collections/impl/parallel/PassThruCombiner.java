/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

/**
 * A PassThruCombiner doesn't do anything.  It can be used for operations that require no combination, as in a fork
 * with no join step.
 */
public final class PassThruCombiner<T>
        implements Combiner<T>
{
    private static final long serialVersionUID = 1L;

    @Override
    public void combineAll(Iterable<T> thingsToCombine)
    {
    }

    @Override
    public void combineOne(T thingToCombine)
    {
    }

    @Override
    public boolean useCombineOne()
    {
        return true;
    }
}
