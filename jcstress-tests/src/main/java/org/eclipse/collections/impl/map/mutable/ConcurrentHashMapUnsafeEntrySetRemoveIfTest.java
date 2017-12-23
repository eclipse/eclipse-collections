/*
 * Copyright (c) 2018 Ivan Sopov and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import java.util.Map;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.Z_Result;

@JCStressTest
@Outcome(id = "false", expect = Expect.ACCEPTABLE)
@Outcome(expect = Expect.FORBIDDEN)
@State
public class ConcurrentHashMapUnsafeEntrySetRemoveIfTest
{
    private final ConcurrentHashMapUnsafe<Integer, Boolean> map = new ConcurrentHashMapUnsafe<>();

    @Actor
    public void setFoo()
    {
        map.put(1, false);
    }

    @Actor
    public void removeIf()
    {
        map.put(1, true);
        map.entrySet().removeIf(Map.Entry::getValue);
    }

    @Arbiter
    public void after(Z_Result r)
    {
        r.r1 = map.containsKey(1) && map.get(1);
    }
}
