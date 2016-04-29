/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag.strategy;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.multimap.MutableMultimapSerializationTestCase;

public class HashBagMultimapWithHashingStrategySerializationTest
        extends MutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return new HashBagMultimapWithHashingStrategy<>(HashingStrategies.defaultStrategy());
    }

    @Override
    protected String getSerializedForm()
    {
        return "rO0ABXNyAFVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmJhZy5zdHJhdGVn\n"
                + "eS5IYXNoQmFnTXVsdGltYXBXaXRoSGFzaGluZ1N0cmF0ZWd5AAAAAAAAAAEMAAB4cHNyAExvcmcu\n"
                + "ZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuSGFzaGluZ1N0cmF0ZWdpZXMk\n"
                + "RGVmYXVsdFN0cmF0ZWd5AAAAAAAAAAECAAB4cHcEAAAAAnQAAUF3BAAAAAJxAH4ABHcEAAAAAXQA\n"
                + "AUJ3BAAAAAJxAH4ABXcEAAAAAXEAfgAEdwQAAAABeA==";
    }
}
