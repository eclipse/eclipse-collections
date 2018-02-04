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

import org.eclipse.collections.api.LongIterable;
import org.eclipse.collections.api.iterator.LongIterator;

/**
 * {@link ValueExtractor} implementation for collections implementing {@link LongIterable}.
 */
public class LongIterableValueExtractor implements ValueExtractor<@ExtractedValue(type = Long.class) LongIterable>
{
    @Override
    public void extractValues(LongIterable originalValue, ValueReceiver receiver)
    {
        LongIterator iterator = originalValue.longIterator();
        while (iterator.hasNext())
        {
            receiver.value("<long-iterable element>", Long.valueOf(iterator.next()));
        }
    }
}
