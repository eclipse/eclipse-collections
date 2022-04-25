/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;

/**
 * ObjectIntProcedureFactory is used by parallel iterators as a factory for stateful ObjectIntProcedure instances.
 */
@FunctionalInterface
public interface ObjectIntProcedureFactory<T extends ObjectIntProcedure<?>>
{
    T create();
}
