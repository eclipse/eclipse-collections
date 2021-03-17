/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.tuple;

import java.io.Serializable;

public interface Triple<T1, T2, T3>
        extends Serializable, Comparable<Triple<T1, T2, T3>>
{
    T1 getOne();

    T2 getTwo();

    T3 getThree();

    Triple<T3, T2, T1> reverse();
}

