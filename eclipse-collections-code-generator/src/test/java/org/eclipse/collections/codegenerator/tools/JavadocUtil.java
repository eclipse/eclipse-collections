/*
 * Copyright (c) 2021 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.codegenerator.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import org.eclipse.collections.codegenerator.EclipseCollectionsCodeGenerator;
import org.eclipse.collections.codegenerator.model.Primitive;

/**
 * <p>This class copies the Javadocs from a generated class back to the template.
 * This is helpful for writing the Javadocs on a "real" class to benefit from IDE
 * support (auto complete of parameter names, for example) that you don't get
 * when directly editing the template.</p>
 *
 * <p>This class is in the test classpath to keep it out of public API, but it is
 * not a test.</p>
 *
 * <p>This assumes it is running in the same folder as the code generator and the
 * API folder with the templates is a sibling folder. You can specify any number
 * of pairs of arguments, for example:
 * <blockquote><code>
 * org.eclipse.collections.api.map.primitive.IntValuesMap
 * api/map/primitiveValuesMap
 * org.eclipse.collections.api.map.primitive.CharIntMap
 * api/map/primitivePrimitiveMap
 * </code></blockquote>
 * </p>
 *
 * @author <a href="dimeo@elderresearch.com">John Dimeo</a>
 * @since May 8, 2020
 */
public class JavadocUtil
{
    private static final Path TEMPLATE_ROOT = Paths.get("src", "main", "resources");
    private static final Path API_ROOT = Paths.get("..", "eclipse-collections-api");

    private String generatedClass;
    private String template;

    public JavadocUtil generatedClass(String gc)
    {
        this.generatedClass = gc;
        return this;
    }

    public JavadocUtil template(String t)
    {
        this.template = t;
        return this;
    }

    public void process() throws IOException
    {
        generatedClass = generatedClass.replace('.', File.separatorChar);
        if (!generatedClass.endsWith(".java"))
        {
            generatedClass += ".java";
        }
        if (!template.endsWith(".stg"))
        {
            template  += ".stg";
        }

        Path src = API_ROOT.resolve(EclipseCollectionsCodeGenerator.GENERATED_SOURCES_LOCATION.replace('/', File.separatorChar) + generatedClass);
        Path dest = TEMPLATE_ROOT.resolve(template.replace('/', File.separatorChar));

        if (!Files.isRegularFile(src) || !Files.isRegularFile(dest))
        {
            throw new IllegalArgumentException("Specified files are not readable");
        }

        List<String> srcLines = Files.readAllLines(src);
        List<String> destLines = Files.readAllLines(dest);
        Deque<String> javadocLines = new LinkedList<>();

        boolean inJavadoc = false;
        boolean matchNextLine = false;
        for (String srcLine : srcLines)
        {
            String srcLineNorm = normalizeGeneratedClassLine(srcLine);
            String srcLineTrimmed = srcLineNorm.trim();
            if (srcLineTrimmed.isEmpty())
            {
                continue;
            }

            // Annotations belong with the javadoc
            if (srcLineTrimmed.startsWith("@"))
            {
                javadocLines.add(srcLineNorm);
                continue;
            }

            if (matchNextLine)
            {
                findLineReplacingJavadoc(destLines, srcLineTrimmed, javadocLines);
                matchNextLine = false;
                continue;
            }

            if (srcLineTrimmed.startsWith("/**"))
            {
                inJavadoc = true;
                javadocLines.clear();
            }
            if (inJavadoc)
            {
                javadocLines.add(srcLineNorm);
                if (srcLineTrimmed.startsWith("*/") || srcLineTrimmed.endsWith("*/"))
                {
                    inJavadoc = false;
                    matchNextLine = true;
                }
            }
        }

        Files.write(dest, destLines);

        System.out.println("Copied Javadocs from " + generatedClass + " back to " + template);
    }

    private static void findLineReplacingJavadoc(List<String> lines, String matchLine, Deque<String> javadoc)
    {
        ListIterator<String> iter = lines.listIterator();
        while (iter.hasNext())
        {
            String line = normalizeTemplateLine(iter.next().trim());
            // Definitions aren't candidates for matches
            if (line.isEmpty() || line.contains("::="))
            {
                continue;
            }

            if (line.equals(matchLine))
            {
                // Remove existing javadoc
                iter.previous();
                while (iter.hasPrevious()
                    && (line = iter.previous().trim()).startsWith("@")
                    || line.startsWith("*/")
                    || line.startsWith("*")
                    || line.startsWith("/**"))
                {
                    iter.remove();
                }
                iter.next();
                javadoc.forEach(iter::add);
                return;
            }
        }
        System.err.println("Could not match line " + matchLine);
        return;
    }

    // Remove all types and type placeholders so lines will exactly match
    private static String normalizeTemplateLine(String s)
    {
        s = s.replace("<type1>", "<type>").replace("<type2>", "<type>");
        s = s.replace("<name1>", "<name>").replace("<name2>", "<name>");
        // boolean is a common return value for is...() methods - normalize
        // it to match normalized lines from the generated class. Also remove \
        return s.replace("boolean", "<type>").replaceAll(Pattern.quote("\\"), "");
    }

    // TODO: Multiple types/names not properly supported
    private static String normalizeGeneratedClassLine(String s)
    {
        for (Primitive p : Primitive.values())
        {
            s = s.replace(p.type, "<type>").replace(p.getName(), "<name>");
        }
        // ... but "interface" gets erroneously replaced
        return s.replace("<type>erface", "interface");
    }

    public static void main(String... args) throws IOException
    {
        if (args.length < 2 || args.length % 2 > 0)
        {
            throw new IllegalArgumentException("You must specify pairs of file paths: a generated class followed by its template");
        }

        for (int i = 0; i < args.length; i += 2)
        {
            new JavadocUtil().generatedClass(args[i]).template(args[i + 1]).process();
        }
    }
}
