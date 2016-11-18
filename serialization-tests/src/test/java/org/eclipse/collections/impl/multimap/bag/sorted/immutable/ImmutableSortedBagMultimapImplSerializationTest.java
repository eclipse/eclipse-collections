/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap.bag.sorted.immutable;

import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.multimap.ImmutableMultimapSerializationTestCase;
import org.eclipse.collections.impl.multimap.bag.sorted.mutable.TreeBagMultimap;

public class ImmutableSortedBagMultimapImplSerializationTest extends ImmutableMultimapSerializationTestCase
{
    @Override
    protected MutableMultimap<String, String> createEmpty()
    {
        return new TreeBagMultimap<>();
    }

    @Override
    protected String getSerializedForm()
    {
        return "rO0ABXNyAIZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm11bHRpbWFwLmJhZy5zb3J0ZWQu\n"
                + "aW1tdXRhYmxlLkltbXV0YWJsZVNvcnRlZEJhZ011bHRpbWFwSW1wbCRJbW11dGFibGVTb3J0ZWRC\n"
                + "YWdNdWx0aW1hcFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHBwdwQAAAACdAABQXcEAAAA\n"
                + "A3EAfgACdAABQnEAfgADcQB+AAN3BAAAAAFxAH4AAng=";
    }
}

