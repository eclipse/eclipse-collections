/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.factory;

import java.util.Collections;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class PredicatesSerializationTest
{
    @Test
    public void throwing()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRUaHJvd2luZ1ByZWRpY2F0ZUFkYXB0ZXIAAAAAAAAAAQIAAUwAEXRocm93aW5nUHJlZGlj\n"
                        + "YXRldABITG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2ltcGwvYmxvY2svcHJlZGljYXRlL2NoZWNr\n"
                        + "ZWQvVGhyb3dpbmdQcmVkaWNhdGU7eHIARW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2sucHJlZGljYXRlLmNoZWNrZWQuQ2hlY2tlZFByZWRpY2F0ZQAAAAAAAAABAgAAeHBw",
                Predicates.throwing(null));
    }

    @Test
    public void alwaysTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBbHdheXNUcnVlAAAAAAAAAAECAAB4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhw",
                Predicates.alwaysTrue());
    }

    @Test
    public void alwaysFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBbHdheXNGYWxzZQAAAAAAAAABAgAAeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmlt\n"
                        + "cGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cA==",
                Predicates.alwaysFalse());
    }

    @Test
    public void adapt()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRQcmVkaWNhdGVBZGFwdGVyAAAAAAAAAAECAAFMAAlwcmVkaWNhdGV0ADdMb3JnL2VjbGlw\n"
                        + "c2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGU7eHIANW9yZy5lY2xp\n"
                        + "cHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4\n"
                        + "cHA=",
                Predicates.adapt(null));
    }

    @Test
    public void attributePredicate()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBdHRyaWJ1dGVQcmVkaWNhdGUAAAAAAAAAAQIAAkwACGZ1bmN0aW9udAA1TG9yZy9lY2xp\n"
                        + "cHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9mdW5jdGlvbi9GdW5jdGlvbjtMAAlwcmVkaWNhdGV0\n"
                        + "ADdMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGU7\n"
                        + "eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVz\n"
                        + "AAAAAAAAAAECAAB4cHBw",
                Predicates.attributePredicate(null, null));
    }

    @Test
    public void ifTrue()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBdHRyaWJ1dGVUcnVlAAAAAAAAAAECAAB4cgBIb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMkQXR0cmlidXRlUHJlZGljYXRlAAAAAAAAAAEC\n"
                        + "AAJMAAhmdW5jdGlvbnQANUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rp\n"
                        + "b24vRnVuY3Rpb247TAAJcHJlZGljYXRldAA3TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9i\n"
                        + "bG9jay9wcmVkaWNhdGUvUHJlZGljYXRlO3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBs\n"
                        + "LmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHBwc3IAQG9yZy5lY2xpcHNlLmNv\n"
                        + "bGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJFRydWVFcXVhbHMAAAAAAAAA\n"
                        + "AQIAAHhw",
                Predicates.ifTrue(null));
    }

    @Test
    public void ifFalse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBdHRyaWJ1dGVGYWxzZQAAAAAAAAABAgAAeHIASG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJEF0dHJpYnV0ZVByZWRpY2F0ZQAAAAAAAAAB\n"
                        + "AgACTAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0\n"
                        + "aW9uL0Z1bmN0aW9uO0wACXByZWRpY2F0ZXQAN0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkv\n"
                        + "YmxvY2svcHJlZGljYXRlL1ByZWRpY2F0ZTt4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhwcHNyAEFvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcyRGYWxzZUVxdWFscwAAAAAA\n"
                        + "AAABAgAAeHA=",
                Predicates.ifFalse(null));
    }

    @Test
    public void anySatisfy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBbnlTYXRpc2Z5AAAAAAAAAAECAAFMAAlwcmVkaWNhdGV0ADdMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGU7eHIANW9yZy5lY2xpcHNlLmNv\n"
                        + "bGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHA=",
                Predicates.anySatisfy(null));
    }

    @Test
    public void allSatisfy()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBbGxTYXRpc2Z5AAAAAAAAAAECAAFMAAlwcmVkaWNhdGV0ADdMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGU7eHIANW9yZy5lY2xpcHNlLmNv\n"
                        + "bGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHA=",
                Predicates.allSatisfy(null));
    }

    @Test
    public void assignableFrom()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBc3NpZ25hYmxlRnJvbVByZWRpY2F0ZQAAAAAAAAABAgABTAAFY2xhenp0ABFMamF2YS9s\n"
                        + "YW5nL0NsYXNzO3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rvcnku\n"
                        + "UHJlZGljYXRlcwAAAAAAAAABAgAAeHB2cgAQamF2YS5sYW5nLk9iamVjdAAAAAAAAAAAAAAAeHA=\n",
                Predicates.assignableFrom(Object.class));
    }

    @Test
    public void instanceOf()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRJbnN0YW5jZU9mUHJlZGljYXRlAAAAAAAAAAECAAFMAAVjbGF6enQAEUxqYXZhL2xhbmcv\n"
                        + "Q2xhc3M7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVk\n"
                        + "aWNhdGVzAAAAAAAAAAECAAB4cHZyABBqYXZhLmxhbmcuT2JqZWN0AAAAAAAAAAAAAAB4cA==",
                Predicates.instanceOf(Object.class));
    }

    @Test
    public void notInstanceOf()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3RJbnN0YW5jZU9mUHJlZGljYXRlAAAAAAAAAAECAAFMAAVjbGF6enQAEUxqYXZhL2xh\n"
                        + "bmcvQ2xhc3M7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5Q\n"
                        + "cmVkaWNhdGVzAAAAAAAAAAECAAB4cHZyABBqYXZhLmxhbmcuT2JqZWN0AAAAAAAAAAAAAAB4cA==\n",
                Predicates.notInstanceOf(Object.class));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRMZXNzVGhhblByZWRpY2F0ZQAAAAAAAAABAgAAeHIASG9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJENvbXBhcmVUb1ByZWRpY2F0ZQAAAAAA\n"
                        + "AAABAgABTAAJY29tcGFyZVRvdAAWTGphdmEvbGFuZy9Db21wYXJhYmxlO3hyADVvcmcuZWNsaXBz\n"
                        + "ZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHBw\n",
                Predicates.lessThan((String) null));
    }

    @Test
    public void lessThanOrEqualTo()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRMZXNzVGhhbk9yRXF1YWxQcmVkaWNhdGUAAAAAAAAAAQIAAHhyAEhvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcyRDb21wYXJlVG9QcmVkaWNh\n"
                        + "dGUAAAAAAAAAAQIAAUwACWNvbXBhcmVUb3QAFkxqYXZhL2xhbmcvQ29tcGFyYWJsZTt4cgA1b3Jn\n"
                        + "LmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAA\n"
                        + "AQIAAHhwcA==",
                Predicates.lessThanOrEqualTo((String) null));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRHcmVhdGVyVGhhblByZWRpY2F0ZQAAAAAAAAABAgAAeHIASG9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJENvbXBhcmVUb1ByZWRpY2F0ZQAA\n"
                        + "AAAAAAABAgABTAAJY29tcGFyZVRvdAAWTGphdmEvbGFuZy9Db21wYXJhYmxlO3hyADVvcmcuZWNs\n"
                        + "aXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAA\n"
                        + "eHBw",
                Predicates.greaterThan((String) null));
    }

    @Test
    public void greaterThanOrEqualTo()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRHcmVhdGVyVGhhbk9yRXF1YWxQcmVkaWNhdGUAAAAAAAAAAQIAAHhyAEhvcmcuZWNsaXBz\n"
                        + "ZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcyRDb21wYXJlVG9QcmVk\n"
                        + "aWNhdGUAAAAAAAAAAQIAAUwACWNvbXBhcmVUb3QAFkxqYXZhL2xhbmcvQ29tcGFyYWJsZTt4cgA1\n"
                        + "b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAA\n"
                        + "AAAAAQIAAHhwcA==",
                Predicates.greaterThanOrEqualTo((String) null));
    }

    @Test
    public void equal()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRFcXVhbFByZWRpY2F0ZQAAAAAAAAABAgABTAANY29tcGFyZU9iamVjdHQAEkxqYXZhL2xh\n"
                        + "bmcvT2JqZWN0O3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rvcnku\n"
                        + "UHJlZGljYXRlcwAAAAAAAAABAgAAeHBzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkA\n"
                        + "BXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAA==",
                Predicates.equal(0));
    }

    @Test
    public void notEqual()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3RFcXVhbFByZWRpY2F0ZQAAAAAAAAABAgABTAANY29tcGFyZU9iamVjdHQAEkxqYXZh\n"
                        + "L2xhbmcvT2JqZWN0O3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3Rv\n"
                        + "cnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHBzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIA\n"
                        + "AUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAA==",
                Predicates.notEqual(0));
    }

    @Test
    public void betweenExclusive()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRCZXR3ZWVuRXhjbHVzaXZlAAAAAAAAAAECAAB4cgBEb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMkUmFuZ2VQcmVkaWNhdGUAAAAAAAAAAQIA\n"
                        + "AUwAC2NvbXBhcmVGcm9tdAAWTGphdmEvbGFuZy9Db21wYXJhYmxlO3hyAEhvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcyRDb21wYXJlVG9QcmVkaWNh\n"
                        + "dGUAAAAAAAAAAQIAAUwACWNvbXBhcmVUb3EAfgACeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHQAAHEAfgAG",
                Predicates.betweenExclusive("", ""));
    }

    @Test
    public void betweenInclusive()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRCZXR3ZWVuSW5jbHVzaXZlAAAAAAAAAAECAAB4cgBEb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMkUmFuZ2VQcmVkaWNhdGUAAAAAAAAAAQIA\n"
                        + "AUwAC2NvbXBhcmVGcm9tdAAWTGphdmEvbGFuZy9Db21wYXJhYmxlO3hyAEhvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcyRDb21wYXJlVG9QcmVkaWNh\n"
                        + "dGUAAAAAAAAAAQIAAUwACWNvbXBhcmVUb3EAfgACeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHQAAHEAfgAG",
                Predicates.betweenInclusive("", ""));
    }

    @Test
    public void betweenInclusiveFrom()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRCZXR3ZWVuSW5jbHVzaXZlRnJvbQAAAAAAAAABAgAAeHIARG9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJFJhbmdlUHJlZGljYXRlAAAAAAAA\n"
                        + "AAECAAFMAAtjb21wYXJlRnJvbXQAFkxqYXZhL2xhbmcvQ29tcGFyYWJsZTt4cgBIb3JnLmVjbGlw\n"
                        + "c2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMkQ29tcGFyZVRvUHJl\n"
                        + "ZGljYXRlAAAAAAAAAAECAAFMAAljb21wYXJlVG9xAH4AAnhyADVvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHB0AABxAH4ABg==\n",
                Predicates.betweenInclusiveFrom("", ""));
    }

    @Test
    public void betweenInclusiveTo()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRCZXR3ZWVuSW5jbHVzaXZlVG8AAAAAAAAAAQIAAHhyAERvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcyRSYW5nZVByZWRpY2F0ZQAAAAAAAAAB\n"
                        + "AgABTAALY29tcGFyZUZyb210ABZMamF2YS9sYW5nL0NvbXBhcmFibGU7eHIASG9yZy5lY2xpcHNl\n"
                        + "LmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJENvbXBhcmVUb1ByZWRp\n"
                        + "Y2F0ZQAAAAAAAAABAgABTAAJY29tcGFyZVRvcQB+AAJ4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhwdAAAcQB+AAY=",
                Predicates.betweenInclusiveTo("", ""));
    }

    @Test
    public void and()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBbmRQcmVkaWNhdGUAAAAAAAAAAQIAAkwABGxlZnR0ADdMb3JnL2VjbGlwc2UvY29sbGVj\n"
                        + "dGlvbnMvYXBpL2Jsb2NrL3ByZWRpY2F0ZS9QcmVkaWNhdGU7TAAFcmlnaHRxAH4AAXhyADVvcmcu\n"
                        + "ZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAAB\n"
                        + "AgAAeHBwcA==",
                Predicates.and(null, null));
    }

    @Test
    public void andCollection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRBbmRJdGVyYWJsZVByZWRpY2F0ZQAAAAAAAAABAgAAeHIAT29yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJEFic3RyYWN0SXRlcmFibGVQcmVk\n"
                        + "aWNhdGUAAAAAAAAAAQIAAUwACnByZWRpY2F0ZXN0ABRMamF2YS9sYW5nL0l0ZXJhYmxlO3hyADVv\n"
                        + "cmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAA\n"
                        + "AAABAgAAeHBzcgAaamF2YS51dGlsLkFycmF5cyRBcnJheUxpc3TZpDy+zYgG0gIAAVsAAWF0ABNb\n"
                        + "TGphdmEvbGFuZy9PYmplY3Q7eHB1cgA4W0xvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxv\n"
                        + "Y2sucHJlZGljYXRlLlByZWRpY2F0ZTsDKlJ9D2WzJgIAAHhwAAAAA3BwcA==",
                Predicates.and(null, null, null));
    }

    @Test
    public void or()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRPclByZWRpY2F0ZQAAAAAAAAABAgACTAAEbGVmdHQAN0xvcmcvZWNsaXBzZS9jb2xsZWN0\n"
                        + "aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL1ByZWRpY2F0ZTtMAAVyaWdodHEAfgABeHIANW9yZy5l\n"
                        + "Y2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAEC\n"
                        + "AAB4cHBw",
                Predicates.or(null, null));
    }

    @Test
    public void orCollection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRPckl0ZXJhYmxlUHJlZGljYXRlAAAAAAAAAAECAAB4cgBPb3JnLmVjbGlwc2UuY29sbGVj\n"
                        + "dGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMkQWJzdHJhY3RJdGVyYWJsZVByZWRp\n"
                        + "Y2F0ZQAAAAAAAAABAgABTAAKcHJlZGljYXRlc3QAFExqYXZhL2xhbmcvSXRlcmFibGU7eHIANW9y\n"
                        + "Zy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAA\n"
                        + "AAECAAB4cHNyABpqYXZhLnV0aWwuQXJyYXlzJEFycmF5TGlzdNmkPL7NiAbSAgABWwABYXQAE1tM\n"
                        + "amF2YS9sYW5nL09iamVjdDt4cHVyADhbTG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmFwaS5ibG9j\n"
                        + "ay5wcmVkaWNhdGUuUHJlZGljYXRlOwMqUn0PZbMmAgAAeHAAAAADcHBw",
                Predicates.or(null, null, null));
    }

    @Test
    public void neither()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROZWl0aGVyUHJlZGljYXRlAAAAAAAAAAECAAJMAARsZWZ0dAA3TG9yZy9lY2xpcHNlL2Nv\n"
                        + "bGxlY3Rpb25zL2FwaS9ibG9jay9wcmVkaWNhdGUvUHJlZGljYXRlO0wABXJpZ2h0cQB+AAF4cgA1\n"
                        + "b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAA\n"
                        + "AAAAAQIAAHhwcHA=",
                Predicates.neither(null, null));
    }

    @Test
    public void noneOf()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb25lT2ZJdGVyYWJsZVByZWRpY2F0ZQAAAAAAAAABAgAAeHIAT29yZy5lY2xpcHNlLmNv\n"
                        + "bGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzJEFic3RyYWN0SXRlcmFibGVQ\n"
                        + "cmVkaWNhdGUAAAAAAAAAAQIAAUwACnByZWRpY2F0ZXN0ABRMamF2YS9sYW5nL0l0ZXJhYmxlO3hy\n"
                        + "ADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAA\n"
                        + "AAAAAAABAgAAeHBzcgAaamF2YS51dGlsLkFycmF5cyRBcnJheUxpc3TZpDy+zYgG0gIAAVsAAWF0\n"
                        + "ABNbTGphdmEvbGFuZy9PYmplY3Q7eHB1cgA4W0xvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGku\n"
                        + "YmxvY2sucHJlZGljYXRlLlByZWRpY2F0ZTsDKlJ9D2WzJgIAAHhwAAAAA3BwcA==",
                Predicates.noneOf(null, null, null));
    }

    @Test
    public void not()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEJvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3RQcmVkaWNhdGUAAAAAAAAAAQIAAUwACXByZWRpY2F0ZXQAN0xvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL1ByZWRpY2F0ZTt4cgA1b3JnLmVjbGlwc2Uu\n"
                        + "Y29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhwcA==\n",
                Predicates.not(null));
    }

    @Test
    public void in_SetIterable()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRJblNldEl0ZXJhYmxlUHJlZGljYXRlAAAAAAAAAAECAAFMAAtzZXRJdGVyYWJsZXQALUxv\n"
                        + "cmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvc2V0L1NldEl0ZXJhYmxlO3hyADVvcmcuZWNsaXBz\n"
                        + "ZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHBz\n"
                        + "cgBJb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5zZXQuaW1tdXRhYmxlLkltbXV0YWJsZVNl\n"
                        + "dFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAB4",
                Predicates.in(Sets.immutable.with()));
    }

    @Test
    public void notIn_SetIterable()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3RJblNldEl0ZXJhYmxlUHJlZGljYXRlAAAAAAAAAAECAAFMAAtzZXRJdGVyYWJsZXQA\n"
                        + "LUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvc2V0L1NldEl0ZXJhYmxlO3hyADVvcmcuZWNs\n"
                        + "aXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAA\n"
                        + "eHBzcgBJb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5zZXQuaW1tdXRhYmxlLkltbXV0YWJs\n"
                        + "ZVNldFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAAAAB4",
                Predicates.notIn(Sets.immutable.with()));
    }

    @Test
    public void in_Set()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRJblNldFByZWRpY2F0ZQAAAAAAAAABAgABTAADc2V0dAAPTGphdmEvdXRpbC9TZXQ7eHIA\n"
                        + "NW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAA\n"
                        + "AAAAAAECAAB4cHNyACBqYXZhLnV0aWwuQ29sbGVjdGlvbnMkQ2hlY2tlZFNldEEkm6J62f+rAgAA\n"
                        + "eHIAJ2phdmEudXRpbC5Db2xsZWN0aW9ucyRDaGVja2VkQ29sbGVjdGlvbhXpbf0Y5sxvAgADTAAB\n"
                        + "Y3QAFkxqYXZhL3V0aWwvQ29sbGVjdGlvbjtMAAR0eXBldAARTGphdmEvbGFuZy9DbGFzcztbABZ6\n"
                        + "ZXJvTGVuZ3RoRWxlbWVudEFycmF5dAATW0xqYXZhL2xhbmcvT2JqZWN0O3hwc3IAM29yZy5lY2xp\n"
                        + "cHNlLmNvbGxlY3Rpb25zLmltcGwuc2V0Lm11dGFibGUuVW5pZmllZFNldAAAAAAAAAABDAAAeHB3\n"
                        + "CAAAAAA/QAAAeHZyABBqYXZhLmxhbmcuT2JqZWN0AAAAAAAAAAAAAAB4cHA=",
                Predicates.in(Collections.checkedSet(Sets.mutable.with(), Object.class)));
    }

    @Test
    public void notIn_Set()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3RJblNldFByZWRpY2F0ZQAAAAAAAAABAgABTAADc2V0dAAPTGphdmEvdXRpbC9TZXQ7\n"
                        + "eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVz\n"
                        + "AAAAAAAAAAECAAB4cHNyACBqYXZhLnV0aWwuQ29sbGVjdGlvbnMkQ2hlY2tlZFNldEEkm6J62f+r\n"
                        + "AgAAeHIAJ2phdmEudXRpbC5Db2xsZWN0aW9ucyRDaGVja2VkQ29sbGVjdGlvbhXpbf0Y5sxvAgAD\n"
                        + "TAABY3QAFkxqYXZhL3V0aWwvQ29sbGVjdGlvbjtMAAR0eXBldAARTGphdmEvbGFuZy9DbGFzcztb\n"
                        + "ABZ6ZXJvTGVuZ3RoRWxlbWVudEFycmF5dAATW0xqYXZhL2xhbmcvT2JqZWN0O3hwc3IAM29yZy5l\n"
                        + "Y2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuc2V0Lm11dGFibGUuVW5pZmllZFNldAAAAAAAAAABDAAA\n"
                        + "eHB3CAAAAAA/QAAAeHZyABBqYXZhLmxhbmcuT2JqZWN0AAAAAAAAAAAAAAB4cHA=",
                Predicates.notIn(Collections.checkedSet(Sets.mutable.with(), Object.class)));
    }

    @Test
    public void in_small_Collection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRJbkNvbGxlY3Rpb25QcmVkaWNhdGUAAAAAAAAAAQIAAUwACmNvbGxlY3Rpb250ABZMamF2\n"
                        + "YS91dGlsL0NvbGxlY3Rpb247eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2su\n"
                        + "ZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                Predicates.in(Lists.mutable.with()));
    }

    @Test
    public void notIn_small_Collection()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3RJbkNvbGxlY3Rpb25QcmVkaWNhdGUAAAAAAAAAAQIAAUwACmNvbGxlY3Rpb250ABZM\n"
                        + "amF2YS91dGlsL0NvbGxlY3Rpb247eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHNyADJvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmxpc3QubXV0YWJsZS5GYXN0TGlzdAAAAAAAAAABDAAAeHB3BAAAAAB4",
                Predicates.notIn(Lists.mutable.with()));
    }

    @Test
    public void isNull()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADxvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRJc051bGwAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJs\n"
                        + "b2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                Predicates.isNull());
    }

    @Test
    public void notNull()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3ROdWxsAAAAAAAAAAECAAB4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhw",
                Predicates.notNull());
    }

    @Test
    public void sameAs()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRJZGVudGl0eVByZWRpY2F0ZQAAAAAAAAABAgABTAAEdHdpbnQAEkxqYXZhL2xhbmcvT2Jq\n"
                        + "ZWN0O3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcwAAAAAAAAABAgAAeHBw",
                Predicates.sameAs(null));
    }

    @Test
    public void notSameAs()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyROb3RJZGVudGl0eVByZWRpY2F0ZQAAAAAAAAABAgABTAAEdHdpbnQAEkxqYXZhL2xhbmcv\n"
                        + "T2JqZWN0O3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJl\n"
                        + "ZGljYXRlcwAAAAAAAAABAgAAeHBw",
                Predicates.notSameAs(null));
    }

    @Test
    public void synchronizedEach()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRTeW5jaHJvbml6ZWRQcmVkaWNhdGUAAAAAAAAAAQIAAUwACXByZWRpY2F0ZXQAN0xvcmcv\n"
                        + "ZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svcHJlZGljYXRlL1ByZWRpY2F0ZTt4cHA=",
                Predicates.synchronizedEach(null));
    }

    @Test
    public void subClass()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRTdWJjbGFzc1ByZWRpY2F0ZQAAAAAAAAABAgABTAAGYUNsYXNzdAARTGphdmEvbGFuZy9D\n"
                        + "bGFzczt4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRp\n"
                        + "Y2F0ZXMAAAAAAAAAAQIAAHhwdnIAEGphdmEubGFuZy5PYmplY3QAAAAAAAAAAAAAAHhw",
                Predicates.subClass(Object.class));
    }

    @Test
    public void superClass()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRTdXBlcmNsYXNzUHJlZGljYXRlAAAAAAAAAAECAAFMAAZhQ2xhc3N0ABFMamF2YS9sYW5n\n"
                        + "L0NsYXNzO3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJl\n"
                        + "ZGljYXRlcwAAAAAAAAABAgAAeHB2cgAQamF2YS5sYW5nLk9iamVjdAAAAAAAAAAAAAAAeHA=",
                Predicates.superClass(Object.class));
    }

    @Test
    public void bind()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGlj\n"
                        + "YXRlcyRCaW5kUHJlZGljYXRlMgAAAAAAAAABAgACTAAJcGFyYW1ldGVydAASTGphdmEvbGFuZy9P\n"
                        + "YmplY3Q7TAAJcHJlZGljYXRldAA4TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9w\n"
                        + "cmVkaWNhdGUvUHJlZGljYXRlMjt4cHBzcgA8b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5i\n"
                        + "bG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyJEVxdWFsAAAAAAAAAAECAAB4cgA2b3JnLmVjbGlwc2Uu\n"
                        + "Y29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMyAAAAAAAAAAECAAB4cA==\n",
                Predicates.bind(Predicates2.equal(), null));
    }
}

