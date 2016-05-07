/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.set;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationTestCase;

public class ImmutableSetMultimapSerializationTest extends ImmutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return new UnifiedSetMultimap<>();
    }

    @Override
    protected String getSerializedForm()
    {
        return "rO0ABXNyAGlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLnNldC5JbW11dGFi\n"
                + "bGVTZXRNdWx0aW1hcEltcGwkSW1tdXRhYmxlU2V0TXVsdGltYXBTZXJpYWxpemF0aW9uUHJveHkA\n"
                + "AAAAAAAAAQwAAHhwdwQAAAACdAABQXcEAAAAAnEAfgACdAABQnEAfgADdwQAAAABcQB+AAJ4";
    }
}
