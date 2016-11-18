/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable

import org.eclipse.collections.api.set.MutableSet
import org.eclipse.collections.impl.MultiReaderThreadSafetyTestTrait
import org.eclipse.collections.impl.Prelude._

trait MultiReaderUnifiedSetTestTrait extends MultiReaderThreadSafetyTestTrait
{
    val classUnderTest: MultiReaderUnifiedSet[Int]

    def createReadLockHolderThread(gate: Gate): Thread =
        spawn
        {
            this.classUnderTest.withReadLockAndDelegate((_: MutableSet[_]) => sleep(gate))
        }

    def createWriteLockHolderThread(gate: Gate): Thread =
        spawn
        {
            this.classUnderTest.withWriteLockAndDelegate((_: MutableSet[_]) => sleep(gate))
        }
}
