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
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Resource;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(
        name = "generate-services",
        defaultPhase = LifecyclePhase.GENERATE_RESOURCES,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.COMPILE)
public class GenerateServicesMojo extends AbstractGenerateMojo
{
    @Parameter(property = "templateDirectory", required = true, defaultValue = "services")
    private String templateDirectory;

    @Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}/generated-resources/META-INF/services", required = true)
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
        return "";
    }

    @Override
    protected void addGeneratedDirectoryToMaven()
    {
        List<String> excludes = new ArrayList<>();
        excludes.add("**/*.crc");

        Resource resource = new Resource();
        resource.setDirectory(this.outputDirectory.getAbsolutePath());
        resource.setTargetPath("META-INF/services");
        resource.setExcludes(excludes);
        this.mavenProject.addResource(resource);
    }
}
