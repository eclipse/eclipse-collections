/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.block.procedure;

import java.util.Collection;
import java.util.List;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.block.factory.Procedures2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Procedure2Test
{
    @Test
    public void accept()
    {
        Procedure2<String, Collection<String>> addToCollection = Procedures2.addToCollection();
        List<String> list = Lists.mutable.empty();
        addToCollection.accept("A", list);
        assertEquals(list, Lists.mutable.of("A"));
        addToCollection.accept("B", list);
        assertEquals(list, Lists.mutable.of("A", "B"));
    }
}
