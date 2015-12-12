/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.list;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationTestCase;

public class ImmutableListMultimapSerializationTest
        extends ImmutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return FastListMultimap.newMultimap();
    }

    @Override
    public String getSerializedForm()
    {
        return "rO0ABXNyAGxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmxpc3QuSW1tdXRh\n"
                + "YmxlTGlzdE11bHRpbWFwSW1wbCRJbW11dGFibGVMaXN0TXVsdGltYXBTZXJpYWxpemF0aW9uUHJv\n"
                + "eHkAAAAAAAAAAQwAAHhwdwQAAAACdAABQXcEAAAAA3EAfgACdAABQnEAfgADcQB+AAN3BAAAAAFx\n"
                + "AH4AAng=";
    }
}
