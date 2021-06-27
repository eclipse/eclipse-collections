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
import javax.validation.constraints.Min;
import javax.validation.valueextraction.Unwrapping;

import org.eclipse.collections.api.FloatIterable;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.junit.Test;

public class FloatIterableValueExtractorTest extends AbstractNumberIterableValueExtractorTest
{
    @Test
    public void extractValues()
    {
        Set<ConstraintViolation<Foo>> violations = validator.validate(new Foo(FloatArrayList.newListWith(1.0f, 2.0f, 3.0f, 11.0f)));
        assertViolations(violations, "float");
    }

    private static class Foo
    {
        @Min(value = 10, payload = Unwrapping.Unwrap.class)
        private final FloatIterable iterable;

        private Foo(FloatIterable iterable)
        {
            this.iterable = iterable;
        }
    }
}