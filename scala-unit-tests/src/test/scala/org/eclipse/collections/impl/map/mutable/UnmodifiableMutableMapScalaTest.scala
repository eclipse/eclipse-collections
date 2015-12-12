/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable

import org.eclipse.collections.impl.{InternalIterableTestTrait, UnmodifiableIterableTestTrait}

class UnmodifiableMutableMapScalaTest extends InternalIterableTestTrait with UnmodifiableIterableTestTrait
{
    val map = UnifiedMap.newWithKeysValues(
        Integer.valueOf(1), "1",
        Integer.valueOf(2), "2",
        Integer.valueOf(3), "3")
    val classUnderTest = new UnmodifiableMutableMap[Integer, String](map)
}
