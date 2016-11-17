/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

public interface CollisionsTestCase
{
    Integer COLLISION_1 = 0;
    Integer COLLISION_2 = 17;
    Integer COLLISION_3 = 34;
    Integer COLLISION_4 = 51;
    Integer COLLISION_5 = 68;
    Integer COLLISION_6 = 85;
    Integer COLLISION_7 = 102;
    Integer COLLISION_8 = 119;
    Integer COLLISION_9 = 136;
    Integer COLLISION_10 = 152;

    ImmutableList<Integer> COLLISIONS = Lists.immutable.with(
            COLLISION_1, COLLISION_2, COLLISION_3,
            COLLISION_4, COLLISION_5, COLLISION_6,
            COLLISION_7, COLLISION_8, COLLISION_9);
}
