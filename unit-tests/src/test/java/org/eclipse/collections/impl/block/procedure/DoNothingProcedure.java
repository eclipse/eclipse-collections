/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.block.procedure.Procedure;

public class DoNothingProcedure implements Procedure<Object>
{
    public static final DoNothingProcedure DO_NOTHING = new DoNothingProcedure();

    private static final long serialVersionUID = 1L;

    @Override
    public void value(Object each)
    {
        // Do nothing.
    }
}
