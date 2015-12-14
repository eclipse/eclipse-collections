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

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class StringPredicatesSerializationTest
{
    @Test
    public void empty()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRFbXB0eQAAAAAAAAABAgAAeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmlt\n"
                        + "cGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cA==",
                StringPredicates.empty());
    }

    @Test
    public void notEmpty()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyROb3RFbXB0eQAAAAAAAAABAgAAeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cA==",
                StringPredicates.notEmpty());
    }

    @Test
    public void containsCharacter()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRDb250YWluc0NoYXJhY3RlcgAAAAAAAAABAgABQwAJY2hhcmFjdGVyeHIANW9y\n"
                        + "Zy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAA\n"
                        + "AAECAAB4cAAg",
                StringPredicates.contains(' '));
    }

    @Test
    public void containsString()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRDb250YWluc1N0cmluZwAAAAAAAAABAgABTAALb3RoZXJTdHJpbmd0ABJMamF2\n"
                        + "YS9sYW5nL1N0cmluZzt4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0\n"
                        + "b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhwcA==",
                StringPredicates.contains(null));
    }

    @Test
    public void startsWith()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRTdGFydHNXaXRoAAAAAAAAAAECAAFMAAlzdWJzdHJpbmd0ABJMamF2YS9sYW5n\n"
                        + "L1N0cmluZzt4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlBy\n"
                        + "ZWRpY2F0ZXMAAAAAAAAAAQIAAHhwcA==",
                StringPredicates.startsWith(null));
    }

    @Test
    public void endsWith()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRFbmRzV2l0aAAAAAAAAAABAgABTAAJc3Vic3RyaW5ndAASTGphdmEvbGFuZy9T\n"
                        + "dHJpbmc7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVk\n"
                        + "aWNhdGVzAAAAAAAAAAECAAB4cHA=",
                StringPredicates.endsWith(null));
    }

    @Test
    public void equalsIgnoreCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAExvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRFcXVhbHNJZ25vcmVDYXNlAAAAAAAAAAECAAFMAAtvdGhlclN0cmluZ3QAEkxq\n"
                        + "YXZhL2xhbmcvU3RyaW5nO3hyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZh\n"
                        + "Y3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHBw",
                StringPredicates.equalsIgnoreCase(null));
    }

    @Test
    public void matches()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRNYXRjaGVzUmVnZXgAAAAAAAAAAQIAAUwABXJlZ2V4dAASTGphdmEvbGFuZy9T\n"
                        + "dHJpbmc7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVk\n"
                        + "aWNhdGVzAAAAAAAAAAECAAB4cHA=",
                StringPredicates.matches(null));
    }

    @Test
    public void lessThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRMZXNzVGhhbgAAAAAAAAABAgABTAAGc3RyaW5ndAASTGphdmEvbGFuZy9TdHJp\n"
                        + "bmc7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNh\n"
                        + "dGVzAAAAAAAAAAECAAB4cHA=",
                StringPredicates.lessThan(null));
    }

    @Test
    public void lessThanOrEqualTo()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRMZXNzVGhhbk9yRXF1YWxUbwAAAAAAAAABAgABTAAGc3RyaW5ndAASTGphdmEv\n"
                        + "bGFuZy9TdHJpbmc7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9y\n"
                        + "eS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHA=",
                StringPredicates.lessThanOrEqualTo(null));
    }

    @Test
    public void greaterThan()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRHcmVhdGVyVGhhbgAAAAAAAAABAgABTAAGc3RyaW5ndAASTGphdmEvbGFuZy9T\n"
                        + "dHJpbmc7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVk\n"
                        + "aWNhdGVzAAAAAAAAAAECAAB4cHA=",
                StringPredicates.greaterThan(null));
    }

    @Test
    public void greaterThanOrEqualTo()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRHcmVhdGVyVGhhbk9yRXF1YWxUbwAAAAAAAAABAgABTAAGc3RyaW5ndAASTGph\n"
                        + "dmEvbGFuZy9TdHJpbmc7eHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFj\n"
                        + "dG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cHA=",
                StringPredicates.greaterThanOrEqualTo(null));
    }

    @Test
    public void hasLetters()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNMZXR0ZXJzAAAAAAAAAAECAAB4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhw",
                StringPredicates.hasLetters());
    }

    @Test
    public void hasDigits()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNEaWdpdHMAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                StringPredicates.hasDigits());
    }

    @Test
    public void hasLettersOrDigits()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNMZXR0ZXJzT3JEaWdpdHMAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5j\n"
                        + "b2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                StringPredicates.hasLettersOrDigits());
    }

    @Test
    public void hasLettersAndDigits()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNMZXR0ZXJzQW5kRGlnaXRzAAAAAAAAAAECAAB4cgA1b3JnLmVjbGlwc2Uu\n"
                        + "Y29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhw",
                StringPredicates.hasLettersAndDigits());
    }

    @Test
    public void hasSpaces()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNTcGFjZXMAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                StringPredicates.hasSpaces());
    }

    @Test
    public void hasUpperCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNVcHBlcmNhc2UAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                StringPredicates.hasUpperCase());
    }

    @Test
    public void hasLowerCase()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNMb3dlcmNhc2UAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                StringPredicates.hasLowerCase());
    }

    @Test
    public void hasUndefined()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRIYXNVbmRlZmluZWQAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                StringPredicates.hasUndefined());
    }

    @Test
    public void isNumeric()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRJc051bWVyaWMAAAAAAAAAAQIAAHhyADVvcmcuZWNsaXBzZS5jb2xsZWN0aW9u\n"
                        + "cy5pbXBsLmJsb2NrLmZhY3RvcnkuUHJlZGljYXRlcwAAAAAAAAABAgAAeHA=",
                StringPredicates.isNumeric());
    }

    @Test
    public void isAlphanumeric()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEpvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRJc0FscGhhbnVtZXJpYwAAAAAAAAABAgAAeHIANW9yZy5lY2xpcHNlLmNvbGxl\n"
                        + "Y3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cA==",
                StringPredicates.isAlphanumeric());
    }

    @Test
    public void isBlank()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRJc0JsYW5rAAAAAAAAAAECAAB4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhw",
                StringPredicates.isBlank());
    }

    @Test
    public void notBlank()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAERvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyROb3RCbGFuawAAAAAAAAABAgAAeHIANW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwuYmxvY2suZmFjdG9yeS5QcmVkaWNhdGVzAAAAAAAAAAECAAB4cA==",
                StringPredicates.notBlank());
    }

    @Test
    public void isAlpha()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAENvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuU3RyaW5n\n"
                        + "UHJlZGljYXRlcyRJc0FscGhhAAAAAAAAAAECAAB4cgA1b3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5ibG9jay5mYWN0b3J5LlByZWRpY2F0ZXMAAAAAAAAAAQIAAHhw",
                StringPredicates.isAlpha());
    }
}
