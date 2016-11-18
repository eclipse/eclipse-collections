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

import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;

/**
 * This class acts as a no op factory for a ObjectIntProcedure which gets passed in and returned out.
 */
public final class PassThruObjectIntProcedureFactory<BT extends ObjectIntProcedure<?>> implements ObjectIntProcedureFactory<BT>
{
    private final BT procedure;

    public PassThruObjectIntProcedureFactory(BT procedure)
    {
        this.procedure = procedure;
    }

    @Override
    public BT create()
    {
        return this.procedure;
    }
}
