/*
 * Copyright (c) 2022 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.checked;

import java.io.Serializable;

/**
 * A functional interface that can be represented by a Lambda that can throw a CheckedException.
 */
@FunctionalInterface
public interface ThrowingFunction2<T1, T2, R> extends Serializable
{
    @SuppressWarnings("ProhibitedExceptionDeclared")
    R safeValue(T1 argument1, T2 argument2) throws Exception;
}
