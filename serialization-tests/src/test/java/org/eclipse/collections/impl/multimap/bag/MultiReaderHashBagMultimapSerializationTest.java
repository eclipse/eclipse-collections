/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.multimap.MutableMultimapSerializationTestCase;

public class MultiReaderHashBagMultimapSerializationTest
        extends MutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return MultiReaderHashBagMultimap.newMultimap();
    }

    @Override
    protected long getExpectedSerialVersionUID()
    {
        return 2L;
    }

    @Override
    protected String getSerializedForm()
    {
        return "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmJhZy5NdWx0aVJl\n"
                + "YWRlckhhc2hCYWdNdWx0aW1hcAAAAAAAAAACDAAAeHB3BAAAAAJ0AAFBdwQAAAACcQB+AAJ3BAAA\n"
                + "AAF0AAFCdwQAAAACcQB+AAN3BAAAAAFxAH4AAncEAAAAAXg=";
    }
}
