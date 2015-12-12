/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.parallel;

import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class AbstractPredicateBasedCombinerSerializationTest
{
    private static final AbstractPredicateBasedCombiner<Object, Procedure<Object>> ABSTRACT_PREDICATE_BASED_COMBINER = new AbstractPredicateBasedCombiner<Object, Procedure<Object>>(false, null, 0, null)
    {
        private static final long serialVersionUID = 1L;

        public void combineOne(Procedure<Object> thingToCombine)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLkFic3RyYWN0UHJl\n"
                        + "ZGljYXRlQmFzZWRDb21iaW5lclNlcmlhbGl6YXRpb25UZXN0JDEAAAAAAAAAAQIAAHhyAERvcmcu\n"
                        + "ZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLkFic3RyYWN0UHJlZGljYXRlQmFzZWRD\n"
                        + "b21iaW5lcgAAAAAAAAABAgABTAAGcmVzdWx0dAAWTGphdmEvdXRpbC9Db2xsZWN0aW9uO3hyAD9v\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnBhcmFsbGVsLkFic3RyYWN0UHJvY2VkdXJlQ29t\n"
                        + "YmluZXIAAAAAAAAAAQIAAVoADXVzZUNvbWJpbmVPbmV4cABzcgAyb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5saXN0Lm11dGFibGUuRmFzdExpc3QAAAAAAAAAAQwAAHhwdwQAAAAAeA==",
                ABSTRACT_PREDICATE_BASED_COMBINER);
    }
}
