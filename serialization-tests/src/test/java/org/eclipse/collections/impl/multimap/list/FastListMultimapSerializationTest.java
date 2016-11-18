/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.list;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.multimap.MutableMultimapSerializationTestCase;

public class FastListMultimapSerializationTest extends MutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return new FastListMultimap<>();
    }

    @Override
    protected String getSerializedForm()
    {
        return "rO0ABXNyADtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmxpc3QuRmFzdExp\n"
                + "c3RNdWx0aW1hcAAAAAAAAAABDAAAeHB3BAAAAAJ0AAFBdwQAAAADcQB+AAJ0AAFCcQB+AANxAH4A\n"
                + "A3cEAAAAAXEAfgACeA==";
    }
}
