/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.codegenerator.maven;

import java.io.File;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(
        name = "generate-sources",
        defaultPhase = LifecyclePhase.GENERATE_SOURCES,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.COMPILE)
public class GenerateSourcesMojo extends AbstractGenerateMojo
{
    @Parameter(property = "templateDirectory", required = true)
    private String templateDirectory;

    @Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}/generated-sources/java", required = true)
    private File outputDirectory;

    @Override
    public String getTemplateDirectory()
    {
        return this.templateDirectory;
    }

    @Override
    public File getOutputDirectory()
    {
        return this.outputDirectory;
    }

    @Override
    protected String getFileExtension()
    {
        return ".java";
    }

    @Override
    protected void addGeneratedDirectoryToMaven()
    {
        String outputDirectoryPath = this.outputDirectory.getPath();
        this.mavenProject.addCompileSourceRoot(outputDirectoryPath);
    }
}
