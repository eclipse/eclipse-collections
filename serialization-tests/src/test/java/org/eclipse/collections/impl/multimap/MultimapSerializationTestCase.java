/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public abstract class MultimapSerializationTestCase
{
    protected abstract MutableMultimap<String, String> createEmpty();

    protected abstract String getSerializedForm();

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                this.getExpectedSerialVersionUID(),
                this.getSerializedForm(),
                this.getMultimapUnderTest());
    }

    protected long getExpectedSerialVersionUID()
    {
        return 1L;
    }

    protected abstract Multimap<String, String> getMultimapUnderTest();
}
