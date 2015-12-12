/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.string.immutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class CodePointListSerializationTest
{
    public static final String EMPTY_CODE_LIST = "rO0ABXNyADtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0cmluZy5pbW11dGFibGUuQ29k\n"
            + "ZVBvaW50TGlzdAAAAAAAAAACAgABTAAKY29kZVBvaW50c3QAPUxvcmcvZWNsaXBzZS9jb2xsZWN0\n"
            + "aW9ucy9hcGkvbGlzdC9wcmltaXRpdmUvSW1tdXRhYmxlSW50TGlzdDt4cHNyAEtvcmcuZWNsaXBz\n"
            + "ZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLnByaW1pdGl2ZS5JbW11dGFibGVJbnRF\n"
            + "bXB0eUxpc3QAAAAAAAAAAQIAAHhw";
    public static final String HELLO_WORLD_STRING = "rO0ABXNyADZjb20uZ3MuY29sbGVjdGlvbnMuaW1wbC5zdHJpbmcuaW1tdXRhYmxlLkNvZGVQb2lun"
            + "dExpc3QAAAAAAAAAAgIAAUwACmNvZGVQb2ludHN0ADhMY29tL2dzL2NvbGxlY3Rpb25zL2FwaS9sn"
            + "aXN0L3ByaW1pdGl2ZS9JbW11dGFibGVJbnRMaXN0O3hwc3IARmNvbS5ncy5jb2xsZWN0aW9ucy5pn"
            + "bXBsLmxpc3QuaW1tdXRhYmxlLnByaW1pdGl2ZS5JbW11dGFibGVJbnRBcnJheUxpc3QAAAAAAAAAn"
            + "AQIAAVsABWl0ZW1zdAACW0l4cHVyAAJbSU26YCZ26rKlAgAAeHAAAAAMAAAASAAAAGUAAABsAAAAn"
            + "bAAAAG8AAAAgAAAAVwAAAG8AAAByAAAAbAAAAGQAAAAh";

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                2L,
                EMPTY_CODE_LIST,
                CodePointList.from(""));
    }

    @Test
    public void serializedFormNotEmpty()
    {
        Verify.assertSerializedForm(
                2L,
                "rO0ABXNyADtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnN0cmluZy5pbW11dGFibGUuQ29k\n"
                        + "ZVBvaW50TGlzdAAAAAAAAAACAgABTAAKY29kZVBvaW50c3QAPUxvcmcvZWNsaXBzZS9jb2xsZWN0\n"
                        + "aW9ucy9hcGkvbGlzdC9wcmltaXRpdmUvSW1tdXRhYmxlSW50TGlzdDt4cHNyAEtvcmcuZWNsaXBz\n"
                        + "ZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuaW1tdXRhYmxlLnByaW1pdGl2ZS5JbW11dGFibGVJbnRB\n"
                        + "cnJheUxpc3QAAAAAAAAAAQIAAVsABWl0ZW1zdAACW0l4cHVyAAJbSU26YCZ26rKlAgAAeHAAAAAM\n"
                        + "AAAASAAAAGUAAABsAAAAbAAAAG8AAAAgAAAAVwAAAG8AAAByAAAAbAAAAGQAAAAh",
                CodePointList.from("Hello World!"));
    }
}
