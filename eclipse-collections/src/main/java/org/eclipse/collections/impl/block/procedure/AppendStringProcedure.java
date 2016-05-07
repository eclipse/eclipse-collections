/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import java.io.IOException;

import org.eclipse.collections.api.block.procedure.Procedure;

public class AppendStringProcedure<T> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;
    private final Appendable appendable;
    private final String separator;
    private boolean first = true;

    public AppendStringProcedure(Appendable appendable, String separator)
    {
        this.appendable = appendable;
        this.separator = separator;
    }

    @Override
    public void value(T each)
    {
        try
        {
            if (this.first)
            {
                this.first = false;
            }
            else
            {
                this.appendable.append(this.separator);
            }
            this.appendable.append(String.valueOf(each));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
