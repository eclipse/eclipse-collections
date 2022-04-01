/*
 * Copyright (c) 2022 Goldman Sachs and others.
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

public class ComparatorsSerializationTest
{
    @Test
    public void comparableComparator()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQ29tcGFyYWJsZUNvbXBhcmF0b3IAAAAAAAAAAQIAAHhw",
                Comparators.comparableComparator());
    }

    @Test
    public void originalNaturalOrder()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkTmF0dXJhbE9yZGVyQ29tcGFyYXRvcgAAAAAAAAABAgAAeHA=",
                Comparators.originalNaturalOrder());
    }

    @Test
    public void naturalOrder()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suZmFjdG9yeS5TZXJpYWxp\n"
                        + "emFibGVDb21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9yAAAAAAAAAAECAAB4cA==",
                Comparators.naturalOrder());
    }

    @Test
    public void originalReverseNaturalOrder()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkUmV2ZXJzZUNvbXBhcmF0b3IAAAAAAAAAAQIAAUwACmNvbXBhcmF0b3J0ABZMamF2YS91\n"
                        + "dGlsL0NvbXBhcmF0b3I7eHBzcgBNb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5m\n"
                        + "YWN0b3J5LkNvbXBhcmF0b3JzJE5hdHVyYWxPcmRlckNvbXBhcmF0b3IAAAAAAAAAAQIAAHhw",
                Comparators.originalReverseNaturalOrder());
    }

    @Test
    public void reverseNaturalOrder()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suZmFjdG9yeS5TZXJpYWxp\n"
                        + "emFibGVDb21wYXJhdG9ycyRSZXZlcnNlQ29tcGFyYXRvcgAAAAAAAAABAgABTAAKY29tcGFyYXRv\n"
                        + "cnQAOkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svU2VyaWFsaXphYmxlQ29tcGFy\n"
                        + "YXRvcjt4cHNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suZmFjdG9yeS5TZXJp\n"
                        + "YWxpemFibGVDb21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9yAAAAAAAAAAECAAB4cA==\n",
                Comparators.reverseNaturalOrder());
    }

    @Test
    public void reverse()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkUmV2ZXJzZUNvbXBhcmF0b3IAAAAAAAAAAQIAAUwACmNvbXBhcmF0b3J0ABZMamF2YS91\n"
                        + "dGlsL0NvbXBhcmF0b3I7eHBzcgBNb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5m\n"
                        + "YWN0b3J5LkNvbXBhcmF0b3JzJE5hdHVyYWxPcmRlckNvbXBhcmF0b3IAAAAAAAAAAQIAAHhw",
                Comparators.reverse(Comparators.originalNaturalOrder()));
    }

    @Test
    public void safeNullsLow()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkU2FmZU51bGxzTG93Q29tcGFyYXRvcgAAAAAAAAABAgABTAAVbm90TnVsbFNhZmVDb21w\n"
                        + "YXJhdG9ydAAWTGphdmEvdXRpbC9Db21wYXJhdG9yO3hwc3IATW9yZy5lY2xpcHNlLmNvbGxlY3Rp\n"
                        + "b25zLmltcGwuYmxvY2suZmFjdG9yeS5Db21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9y\n"
                        + "AAAAAAAAAAECAAB4cA==",
                Comparators.safeNullsLow(Comparators.originalNaturalOrder()));
    }

    @Test
    public void safeNullsHigh()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkU2FmZU51bGxzSGlnaENvbXBhcmF0b3IAAAAAAAAAAQIAAUwAFW5vdE51bGxTYWZlQ29t\n"
                        + "cGFyYXRvcnQAFkxqYXZhL3V0aWwvQ29tcGFyYXRvcjt4cHNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0\n"
                        + "aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFyYXRvcnMkTmF0dXJhbE9yZGVyQ29tcGFyYXRv\n"
                        + "cgAAAAAAAAABAgAAeHA=",
                Comparators.safeNullsHigh(Comparators.originalNaturalOrder()));
    }

    @Test
    public void chain()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQ2hhaW5lZENvbXBhcmF0b3IAAAAAAAAAAQIAAVsAC2NvbXBhcmF0b3JzdAAXW0xqYXZh\n"
                        + "L3V0aWwvQ29tcGFyYXRvcjt4cHVyABdbTGphdmEudXRpbC5Db21wYXJhdG9yO/ex2FW83SGgAgAA\n"
                        + "eHAAAAABc3IATW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5Db21w\n"
                        + "YXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9yAAAAAAAAAAECAAB4cA==",
                Comparators.chain(Comparators.originalNaturalOrder()));
    }

    @Test
    public void fromFunctions()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAD9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2suY29tcGFyYXRvci5GdW5j\n"
                        + "dGlvbkNvbXBhcmF0b3IAAAAAAAAAAQIAAkwACmNvbXBhcmF0b3J0ADpMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2Jsb2NrL1NlcmlhbGl6YWJsZUNvbXBhcmF0b3I7TAAIZnVuY3Rpb250ADVM\n"
                        + "b3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL0Z1bmN0aW9uO3hwc3IA\n"
                        + "WG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmFwaS5ibG9jay5mYWN0b3J5LlNlcmlhbGl6YWJsZUNv\n"
                        + "bXBhcmF0b3JzJE5hdHVyYWxPcmRlckNvbXBhcmF0b3IAAAAAAAAAAQIAAHhwc3IARW9yZy5lY2xp\n"
                        + "cHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suZmFjdG9yeS5GdW5jdGlvbnMkVG9TdHJpbmdGdW5j\n"
                        + "dGlvbgAAAAAAAAABAgAAeHA=",
                Comparators.fromFunctions(Functions.getToString()));
    }

    @Test
    public void originalByFunction()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmNvbXBhcmF0b3IuRnVu\n"
                        + "Y3Rpb25Db21wYXJhdG9yAAAAAAAAAAECAAJMAApjb21wYXJhdG9ydAAWTGphdmEvdXRpbC9Db21w\n"
                        + "YXJhdG9yO0wACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9m\n"
                        + "dW5jdGlvbi9GdW5jdGlvbjt4cHNyAE1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuQ29tcGFyYXRvcnMkTmF0dXJhbE9yZGVyQ29tcGFyYXRvcgAAAAAAAAABAgAAeHBz\n"
                        + "cgBFb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LkZ1bmN0aW9ucyRU\n"
                        + "b1N0cmluZ0Z1bmN0aW9uAAAAAAAAAAECAAB4cA==",
                Comparators.originalByFunction(Functions.getToString()));
    }

    @Test
    public void fromFunctions2()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQ2hhaW5lZENvbXBhcmF0b3IAAAAAAAAAAQIAAVsAC2NvbXBhcmF0b3JzdAAXW0xqYXZh\n"
                        + "L3V0aWwvQ29tcGFyYXRvcjt4cHVyABdbTGphdmEudXRpbC5Db21wYXJhdG9yO/ex2FW83SGgAgAA\n"
                        + "eHAAAAACc3IAP29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmFwaS5ibG9jay5jb21wYXJhdG9yLkZ1\n"
                        + "bmN0aW9uQ29tcGFyYXRvcgAAAAAAAAABAgACTAAKY29tcGFyYXRvcnQAOkxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svU2VyaWFsaXphYmxlQ29tcGFyYXRvcjtMAAhmdW5jdGlvbnQA\n"
                        + "NUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247eHBz\n"
                        + "cgBYb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuYXBpLmJsb2NrLmZhY3RvcnkuU2VyaWFsaXphYmxl\n"
                        + "Q29tcGFyYXRvcnMkTmF0dXJhbE9yZGVyQ29tcGFyYXRvcgAAAAAAAAABAgAAeHBzcgBFb3JnLmVj\n"
                        + "bGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LkZ1bmN0aW9ucyRUb1N0cmluZ0Z1\n"
                        + "bmN0aW9uAAAAAAAAAAECAAB4cHNxAH4ABXEAfgAKcQB+AAw=",
                Comparators.fromFunctions(Functions.getToString(), Functions.getToString()));
    }

    @Test
    public void chainTwoOriginalByFunctions()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQ2hhaW5lZENvbXBhcmF0b3IAAAAAAAAAAQIAAVsAC2NvbXBhcmF0b3JzdAAXW0xqYXZh\n"
                        + "L3V0aWwvQ29tcGFyYXRvcjt4cHVyABdbTGphdmEudXRpbC5Db21wYXJhdG9yO/ex2FW83SGgAgAA\n"
                        + "eHAAAAACc3IAQG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suY29tcGFyYXRvci5G\n"
                        + "dW5jdGlvbkNvbXBhcmF0b3IAAAAAAAAAAQIAAkwACmNvbXBhcmF0b3J0ABZMamF2YS91dGlsL0Nv\n"
                        + "bXBhcmF0b3I7TAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2Nr\n"
                        + "L2Z1bmN0aW9uL0Z1bmN0aW9uO3hwc3IATW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5Db21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9yAAAAAAAAAAECAAB4\n"
                        + "cHNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rpb25z\n"
                        + "JFRvU3RyaW5nRnVuY3Rpb24AAAAAAAAAAQIAAHhwc3EAfgAFcQB+AApxAH4ADA==",
                Comparators.chain(
                        Comparators.originalByFunction(Functions.getToString()),
                        Comparators.originalByFunction(Functions.getToString())));
    }

    @Test
    public void fromFunctions3()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQ2hhaW5lZENvbXBhcmF0b3IAAAAAAAAAAQIAAVsAC2NvbXBhcmF0b3JzdAAXW0xqYXZh\n"
                        + "L3V0aWwvQ29tcGFyYXRvcjt4cHVyABdbTGphdmEudXRpbC5Db21wYXJhdG9yO/ex2FW83SGgAgAA\n"
                        + "eHAAAAADc3IAP29yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmFwaS5ibG9jay5jb21wYXJhdG9yLkZ1\n"
                        + "bmN0aW9uQ29tcGFyYXRvcgAAAAAAAAABAgACTAAKY29tcGFyYXRvcnQAOkxvcmcvZWNsaXBzZS9j\n"
                        + "b2xsZWN0aW9ucy9hcGkvYmxvY2svU2VyaWFsaXphYmxlQ29tcGFyYXRvcjtMAAhmdW5jdGlvbnQA\n"
                        + "NUxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vRnVuY3Rpb247eHBz\n"
                        + "cgBYb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuYXBpLmJsb2NrLmZhY3RvcnkuU2VyaWFsaXphYmxl\n"
                        + "Q29tcGFyYXRvcnMkTmF0dXJhbE9yZGVyQ29tcGFyYXRvcgAAAAAAAAABAgAAeHBzcgBFb3JnLmVj\n"
                        + "bGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5mYWN0b3J5LkZ1bmN0aW9ucyRUb1N0cmluZ0Z1\n"
                        + "bmN0aW9uAAAAAAAAAAECAAB4cHNxAH4ABXEAfgAKcQB+AAxzcQB+AAVxAH4ACnEAfgAM",
                Comparators.fromFunctions(Functions.getToString(), Functions.getToString(), Functions.getToString()));
    }

    @Test
    public void chainThreeOriginalByFunctions()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQ2hhaW5lZENvbXBhcmF0b3IAAAAAAAAAAQIAAVsAC2NvbXBhcmF0b3JzdAAXW0xqYXZh\n"
                        + "L3V0aWwvQ29tcGFyYXRvcjt4cHVyABdbTGphdmEudXRpbC5Db21wYXJhdG9yO/ex2FW83SGgAgAA\n"
                        + "eHAAAAADc3IAQG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxvY2suY29tcGFyYXRvci5G\n"
                        + "dW5jdGlvbkNvbXBhcmF0b3IAAAAAAAAAAQIAAkwACmNvbXBhcmF0b3J0ABZMamF2YS91dGlsL0Nv\n"
                        + "bXBhcmF0b3I7TAAIZnVuY3Rpb250ADVMb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2Nr\n"
                        + "L2Z1bmN0aW9uL0Z1bmN0aW9uO3hwc3IATW9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuYmxv\n"
                        + "Y2suZmFjdG9yeS5Db21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9yAAAAAAAAAAECAAB4\n"
                        + "cHNyAEVvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuRnVuY3Rpb25z\n"
                        + "JFRvU3RyaW5nRnVuY3Rpb24AAAAAAAAAAQIAAHhwc3EAfgAFcQB+AApxAH4ADHNxAH4ABXEAfgAK\n"
                        + "cQB+AAw=",
                Comparators.chain(
                        Comparators.originalByFunction(Functions.getToString()),
                        Comparators.originalByFunction(Functions.getToString()),
                        Comparators.originalByFunction(Functions.getToString())));
    }

    @Test
    public void powerSet()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAElvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkUG93ZXJTZXRDb21wYXJhdG9yAAAAAAAAAAECAAB4cA==",
                Comparators.powerSet());
    }

    @Test
    public void ascendingCollectionSizeComparator()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQXNjZW5kaW5nQ29sbGVjdGlvblNpemVDb21wYXJhdG9yAAAAAAAAAAECAAB4cA==",
                Comparators.ascendingCollectionSizeComparator());
    }

    @Test
    public void descendingCollectionSizeComparator()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAFlvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkRGVzY2VuZGluZ0NvbGxlY3Rpb25TaXplQ29tcGFyYXRvcgAAAAAAAAABAgAAeHA=",
                Comparators.descendingCollectionSizeComparator());
    }

    @Test
    public void compareByFirst()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE5vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQnlGaXJzdE9mUGFpckNvbXBhcmF0b3IAAAAAAAAAAQIAAUwACmNvbXBhcmF0b3J0ABZM\n"
                        + "amF2YS91dGlsL0NvbXBhcmF0b3I7eHBw",
                Comparators.byFirstOfPair(null));
    }

    @Test
    public void compareBySecond()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAE9vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmZhY3RvcnkuQ29tcGFy\n"
                        + "YXRvcnMkQnlTZWNvbmRPZlBhaXJDb21wYXJhdG9yAAAAAAAAAAECAAFMAApjb21wYXJhdG9ydAAW\n"
                        + "TGphdmEvdXRpbC9Db21wYXJhdG9yO3hwcA==",
                Comparators.bySecondOfPair(null));
    }

    @Test
    public void compareByFunctionNullsLast()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmNvbXBhcmF0b3IuRnVu\n"
                        + "Y3Rpb25Db21wYXJhdG9yAAAAAAAAAAECAAJMAApjb21wYXJhdG9ydAAWTGphdmEvdXRpbC9Db21w\n"
                        + "YXJhdG9yO0wACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9m\n"
                        + "dW5jdGlvbi9GdW5jdGlvbjt4cHNyACRqYXZhLnV0aWwuQ29tcGFyYXRvcnMkTnVsbENvbXBhcmF0\n"
                        + "b3KW851NtwreSAIAAloACW51bGxGaXJzdEwABHJlYWxxAH4AAXhwAH5yACxqYXZhLnV0aWwuQ29t\n"
                        + "cGFyYXRvcnMkTmF0dXJhbE9yZGVyQ29tcGFyYXRvcgAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5F\n"
                        + "bnVtAAAAAAAAAAASAAB4cHQACElOU1RBTkNFcA==",
                Comparators.byFunctionNullsLast(null));
    }

    @Test
    public void compareByFunctionNullsFirst()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEBvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLmNvbXBhcmF0b3IuRnVu\n"
                        + "Y3Rpb25Db21wYXJhdG9yAAAAAAAAAAECAAJMAApjb21wYXJhdG9ydAAWTGphdmEvdXRpbC9Db21w\n"
                        + "YXJhdG9yO0wACGZ1bmN0aW9udAA1TG9yZy9lY2xpcHNlL2NvbGxlY3Rpb25zL2FwaS9ibG9jay9m\n"
                        + "dW5jdGlvbi9GdW5jdGlvbjt4cHNyACRqYXZhLnV0aWwuQ29tcGFyYXRvcnMkTnVsbENvbXBhcmF0\n"
                        + "b3KW851NtwreSAIAAloACW51bGxGaXJzdEwABHJlYWxxAH4AAXhwAX5yACxqYXZhLnV0aWwuQ29t\n"
                        + "cGFyYXRvcnMkTmF0dXJhbE9yZGVyQ29tcGFyYXRvcgAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5F\n"
                        + "bnVtAAAAAAAAAAASAAB4cHQACElOU1RBTkNFcA==",
                Comparators.byFunctionNullsFirst(null));
    }
}
