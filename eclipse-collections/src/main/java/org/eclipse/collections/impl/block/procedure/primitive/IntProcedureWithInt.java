/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.primitive;

import java.io.Serializable;

/**
 * A IntProcedureWithInt is a two argument Closure which has no return argument and takes an int as a first and second
 * argument.  The second argument is usually the index of the current element of a collection.
 *
 * @deprecated since 1.2 use {@link IntIntProcedure}
 */
@Deprecated
public interface IntProcedureWithInt extends Serializable
{
    void value(int each, int index);
}
