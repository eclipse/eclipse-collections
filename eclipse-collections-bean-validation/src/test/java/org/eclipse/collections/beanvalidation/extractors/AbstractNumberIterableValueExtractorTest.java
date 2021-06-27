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

import java.util.Collection;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Min;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractNumberIterableValueExtractorTest extends AbstractValueExtractorTest
{
    protected <T> void assertViolations(Collection<ConstraintViolation<T>> violations, String type)
    {
        assertFalse(violations.isEmpty());
        assertEquals("Wrong number of violations", 3, violations.size());
        for (ConstraintViolation<T> violation : violations)
        {
            assertEquals("must be greater than or equal to 10", violation.getMessage());
            assertEquals("iterable.<" + type + "-iterable element>", violation.getPropertyPath().toString());
            assertTrue(violation.getConstraintDescriptor().getAnnotation() instanceof Min);
        }
    }
}