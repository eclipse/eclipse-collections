/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.codegenerator.tools;

import java.util.Locale;

import org.stringtemplate.v4.AttributeRenderer;
import org.stringtemplate.v4.NumberRenderer;
import org.stringtemplate.v4.StringRenderer;

public class IntegerOrStringRenderer implements AttributeRenderer
{
    private final AttributeRenderer numberRenderer = new NumberRenderer();
    private final AttributeRenderer stringRenderer = new StringRenderer();

    @Override
    public String toString(Object object, String formatString, Locale locale)
    {
        if (!(object instanceof String))
        {
            throw new RuntimeException("Only works on Strings");
        }
        try
        {
            Integer integer = Integer.valueOf((String) object);
            return this.numberRenderer.toString(integer, formatString, locale);
        }
        catch (NumberFormatException ignored)
        {
            return this.stringRenderer.toString(object, formatString, locale);
        }
    }
}
