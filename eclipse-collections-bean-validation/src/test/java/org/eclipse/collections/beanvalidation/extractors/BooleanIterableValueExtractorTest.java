/*
 * Copyright (c) 2018 Marko Bekhta.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.beanvalidation.extractors;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.AssertTrue;
import javax.validation.valueextraction.Unwrapping;

import org.eclipse.collections.api.BooleanIterable;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BooleanIterableValueExtractorTest extends AbstractValueExtractorTest
{
    @Test
    public void extractValues()
    {
        Set<ConstraintViolation<Foo>> violations = validator.validate(new Foo(BooleanArrayList.newListWith(true, false)));
        assertFalse(violations.isEmpty());
        assertEquals("Wrong number of violations", 1, violations.size());
        for (ConstraintViolation<Foo> violation : violations)
        {
            assertEquals("must be true", violation.getMessage());
            assertEquals("iterable.<boolean-iterable element>", violation.getPropertyPath().toString());
            assertTrue(violation.getConstraintDescriptor().getAnnotation() instanceof AssertTrue);
        }
    }

    private static class Foo
    {
        @AssertTrue(payload = Unwrapping.Unwrap.class)
        private final BooleanIterable iterable;

        private Foo(BooleanIterable iterable)
        {
            this.iterable = iterable;
        }
    }
}