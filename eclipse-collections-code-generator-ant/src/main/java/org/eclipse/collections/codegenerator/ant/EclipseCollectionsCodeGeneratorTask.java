/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.codegenerator.ant;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.LogLevel;
import org.eclipse.collections.codegenerator.EclipseCollectionsCodeGenerator;

public class EclipseCollectionsCodeGeneratorTask extends Task
{
    private static final String CLASSPATH_SEPARATOR = System.getProperty("path.separator");
    private String templateDirectory;

    @Override
    public void execute()
    {
        this.log("Scanning all template files from " + this.templateDirectory);

        boolean[] error = new boolean[1];
        EclipseCollectionsCodeGenerator.ErrorListener errorListener = new EclipseCollectionsCodeGenerator.ErrorListener()
        {
            public void error(String string)
            {
                EclipseCollectionsCodeGeneratorTask.this.log(string, LogLevel.ERR.getLevel());
                error[0] = true;
            }
        };
        EclipseCollectionsCodeGenerator gsCollectionsCodeGenerator =
                new EclipseCollectionsCodeGenerator(this.templateDirectory, this.getProject().getBaseDir(), this.getClassPathURLs(), errorListener);
        int numFilesWritten = gsCollectionsCodeGenerator.generateFiles();
        this.log("Generated " + numFilesWritten + " files.");
        if (error[0])
        {
            throw new BuildException("Error(s) during code generation.");
        }
    }

    public void setTemplateDirectory(String templateDirectory)
    {
        this.templateDirectory = templateDirectory;
    }

    private List<URL> getClassPathURLs()
    {
        List<URL> urls = new ArrayList<>();
        String[] classPathStrings = ((AntClassLoader) this.getClass().getClassLoader()).getClasspath().split(CLASSPATH_SEPARATOR);

        for (String classPathString : classPathStrings)
        {
            try
            {
                URL url = new File(classPathString).toURI().toURL();
                urls.add(url);
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }
        }
        return urls;
    }
}
