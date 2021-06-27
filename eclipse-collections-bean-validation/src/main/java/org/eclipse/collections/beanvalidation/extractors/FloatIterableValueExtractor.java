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

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.ValueExtractor;

import org.eclipse.collections.api.FloatIterable;
import org.eclipse.collections.api.iterator.FloatIterator;

/**
 * {@link ValueExtractor} implementation for collections implementing {@link FloatIterable}.
 */
public class FloatIterableValueExtractor implements ValueExtractor<@ExtractedValue(type = Float.class) FloatIterable>
{
    @Override
    public void extractValues(FloatIterable originalValue, ValueReceiver receiver)
    {
        FloatIterator iterator = originalValue.floatIterator();
        while (iterator.hasNext())
        {
            receiver.value("<float-iterable element>", Float.valueOf(iterator.next()));
        }
    }
}
