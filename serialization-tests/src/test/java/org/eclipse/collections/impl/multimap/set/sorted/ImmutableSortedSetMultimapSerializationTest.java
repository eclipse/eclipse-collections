/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set.sorted;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationTestCase;

public class ImmutableSortedSetMultimapSerializationTest extends ImmutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return new TreeSortedSetMultimap<>();
    }

    @Override
    protected String getSerializedForm()
    {
        return "rO0ABXNyAHxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLnNldC5zb3J0ZWQu\n"
                + "SW1tdXRhYmxlU29ydGVkU2V0TXVsdGltYXBJbXBsJEltbXV0YWJsZVNvcnRlZFNldE11bHRpbWFw\n"
                + "U2VyaWFsaXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHB3BAAAAAJ0AAFBdwQAAAACcQB+AAJ0AAFC\n"
                + "cQB+AAN3BAAAAAFxAH4AAng=";
    }
}
