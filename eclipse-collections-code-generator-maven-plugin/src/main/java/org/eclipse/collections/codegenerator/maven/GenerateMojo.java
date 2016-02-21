/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.codegenerator.maven;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.eclipse.collections.codegenerator.EclipseCollectionsCodeGenerator;

/**
 * @goal generate
 * @phase generate-sources
 * @requiresDependencyResolution compile
 */
public class GenerateMojo extends AbstractMojo
{
    /**
     * Skips code generation if true.
     *
     * @parameter property="skipCodeGen"
     */
    private boolean skipCodeGen;

    /**
     * @parameter default-value="${project.build.directory}/generated-sources"
     * @required
     */
    private String templateDirectory;

    /**
     * The Maven project to act upon.
     *
     * @parameter property="project"
     * @required
     */
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException
    {
        if (this.skipCodeGen)
        {
            this.getLog().info("Skipping code generation in " + this.project.getArtifactId());
        }
        else
        {
            this.getLog().info("Generating sources to " + this.project.getArtifactId());
        }

        List<URL> urls = Arrays.asList(((URLClassLoader) GenerateMojo.class.getClassLoader()).getURLs());

        final boolean[] error = new boolean[1];
        EclipseCollectionsCodeGenerator.ErrorListener errorListener = new EclipseCollectionsCodeGenerator.ErrorListener()
        {
            public void error(String string)
            {
                GenerateMojo.this.getLog().error(string);
                error[0] = true;
            }
        };
        EclipseCollectionsCodeGenerator gsCollectionsCodeGenerator =
                new EclipseCollectionsCodeGenerator(this.templateDirectory, this.project.getBasedir(), urls, errorListener);
        if (!this.skipCodeGen)
        {
            int numFilesWritten = gsCollectionsCodeGenerator.generateFiles();
            this.getLog().info("Generated " + numFilesWritten + " files");
        }
        if (error[0])
        {
            throw new MojoExecutionException("Error(s) during code generation.");
        }

        if (gsCollectionsCodeGenerator.isTest())
        {
            this.project.addTestCompileSourceRoot(EclipseCollectionsCodeGenerator.GENERATED_TEST_SOURCES_LOCATION);
        }
        else
        {
            this.project.addCompileSourceRoot(EclipseCollectionsCodeGenerator.GENERATED_SOURCES_LOCATION);
        }
    }
}
