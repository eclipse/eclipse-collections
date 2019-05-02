/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.list;

import org.eclipse.collections.api.block.procedure.Procedure;

/**
 * A MultiReaderList provides thread-safe iteration for a list through methods {@code withReadLockAndDelegate()} and {@code withWriteLockAndDelegate()}.
 */
public interface MultiReaderList<T>
        extends MutableList<T>
{
    void withReadLockAndDelegate(Procedure<MutableList<T>> procedure);

    void withWriteLockAndDelegate(Procedure<MutableList<T>> procedure);
}
