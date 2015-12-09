/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.utility.Iterate;

public abstract class AbstractProcedureCombiner<BT>
        implements Combiner<BT>
{
    private static final long serialVersionUID = 1L;

    private boolean useCombineOne;

    protected AbstractProcedureCombiner(boolean useCombineOne)
    {
        this.useCombineOne = useCombineOne;
    }

    public void combineAll(Iterable<BT> thingsToCombine)
    {
        Iterate.forEach(thingsToCombine, new Procedure<BT>()
        {
            public void value(BT object)
            {
                AbstractProcedureCombiner.this.combineOne(object);
            }
        });
    }

    public boolean useCombineOne()
    {
        return this.useCombineOne;
    }

    public void setCombineOne(boolean newBoolean)
    {
        this.useCombineOne = newBoolean;
    }
}
