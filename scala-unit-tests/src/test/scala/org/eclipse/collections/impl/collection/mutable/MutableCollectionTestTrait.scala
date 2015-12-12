/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection.mutable

import org.eclipse.collections.api.collection.MutableCollection
import org.eclipse.collections.impl.InternalIterableTestTrait
import org.eclipse.collections.impl.test.Verify
import org.junit.Test

// TODO: change to extend RichIterableTestTrait
trait MutableCollectionTestTrait extends InternalIterableTestTrait
{
    val classUnderTest: MutableCollection[String]

    @Test
    def add
    {
        this.classUnderTest.add("4")
        Verify.assertSize(4, this.classUnderTest)
        Verify.assertContainsAll(this.classUnderTest, "1", "2", "3", "4")
    }
}
