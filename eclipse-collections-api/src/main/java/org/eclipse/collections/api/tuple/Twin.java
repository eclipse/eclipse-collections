/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.tuple;

/**
 * A Twin is a Pair where both elements have the same type.
 *
 * An instance of this interface can be created by calling Tuples.twin(Object, Object).
 */
public interface Twin<T>
        extends Pair<T, T>
{
    @Override
    Twin<T> swap();
}
