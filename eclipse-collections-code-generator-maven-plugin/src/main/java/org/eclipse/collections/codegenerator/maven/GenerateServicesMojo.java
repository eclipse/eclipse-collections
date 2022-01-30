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
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.eclipse.collections.codegenerator.EclipseCollectionsCodeGenerator;
import org.eclipse.collections.codegenerator.ErrorListener;

@Mojo(
        name = "generate-services",
        defaultPhase = LifecyclePhase.GENERATE_RESOURCES,
        threadSafe = true,
        requiresDependencyResolution = ResolutionScope.COMPILE)
public class GenerateServicesMojo extends AbstractMojo
{
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject mavenProject;

    @Parameter(property = "skipCodeGen", defaultValue = "false")
    private boolean skipCodeGen;

    @Parameter(property = "templateDirectory", required = true, defaultValue = "services")
    private String templateDirectory;

    @Parameter(property = "outputDirectory", defaultValue = "${project.build.directory}/generated-resources/META-INF/services", required = true)
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException
    {
        if (this.skipCodeGen)
        {
            this.getLog().info("Skipping code generation in " + this.mavenProject.getArtifactId());
        }
        else
        {
            this.getLog().info("Generating sources in " + this.mavenProject.getArtifactId() + " to " + outputDirectory);
        }

        List<URL> urls = Arrays.asList(((URLClassLoader) GenerateServicesMojo.class.getClassLoader()).getURLs());

        boolean[] error = new boolean[1];
        ErrorListener errorListener = string -> {
            this.getLog().error(string);
            error[0] = true;
        };
        EclipseCollectionsCodeGenerator codeGenerator = new EclipseCollectionsCodeGenerator(
                this.templateDirectory,
                urls,
                errorListener,
                this.outputDirectory,
                "");
        if (!this.skipCodeGen)
        {
            int numFilesWritten = codeGenerator.generateFiles();
            this.getLog().info("Generated " + numFilesWritten + " files");
        }
        if (error[0])
        {
            throw new MojoExecutionException("Error(s) during code generation.");
        }

        List<String> excludes = new ArrayList<>();
        excludes.add("**/*.crc");

        Resource resource = new Resource();
        resource.setDirectory(this.outputDirectory.getAbsolutePath());
        resource.setTargetPath("META-INF/services");
        resource.setExcludes(excludes);
        this.mavenProject.addResource(resource);
    }
}
