/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.codegenerator;

import java.net.URL;
import java.util.Objects;

import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.misc.STMessage;

public final class LoggingErrorListener implements STErrorListener
{
    private final ErrorListener errorListener;
    private final URL url;

    public LoggingErrorListener(ErrorListener errorListener, URL url)
    {
        this.errorListener = Objects.requireNonNull(errorListener);
        this.url = Objects.requireNonNull(url);
    }

    private void logError(STMessage stMessage, String errorType)
    {
        String error = String.format("String template %s error while processing [%s]: %s",
                errorType,
                this.url.getPath(),
                stMessage.toString());
        this.errorListener.error(error);
        throw new RuntimeException(error);
    }

    @Override
    public void compileTimeError(STMessage stMessage)
    {
        this.logError(stMessage, "compile time");
    }

    @Override
    public void runTimeError(STMessage stMessage)
    {
        this.logError(stMessage, "run time");
    }

    @Override
    public void IOError(STMessage stMessage)
    {
        this.logError(stMessage, "IO");
    }

    @Override
    public void internalError(STMessage stMessage)
    {
        this.logError(stMessage, "internal");
    }
}
