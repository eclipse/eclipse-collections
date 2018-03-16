/*
 * Copyright (c) 2018 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collector;

import org.eclipse.collections.impl.test.Verify;
import org.junit.Test;

public class SummaryStatisticsSeralizationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rvci5TdW1tYXJ5U3Rh\n"
                        + "dGlzdGljcwAAAAAAAAABDAAAeHBzcgBJb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5tYXAu\n"
                        + "aW1tdXRhYmxlLkltbXV0YWJsZU1hcFNlcmlhbGl6YXRpb25Qcm94eQAAAAAAAAABDAAAeHB3BAAA\n"
                        + "AAF0AAExc3IAIWphdmEubGFuZy5pbnZva2UuU2VyaWFsaXplZExhbWJkYW9h0JQsKTaFAgAKSQAO\n"
                        + "aW1wbE1ldGhvZEtpbmRbAAxjYXB0dXJlZEFyZ3N0ABNbTGphdmEvbGFuZy9PYmplY3Q7TAAOY2Fw\n"
                        + "dHVyaW5nQ2xhc3N0ABFMamF2YS9sYW5nL0NsYXNzO0wAGGZ1bmN0aW9uYWxJbnRlcmZhY2VDbGFz\n"
                        + "c3QAEkxqYXZhL2xhbmcvU3RyaW5nO0wAHWZ1bmN0aW9uYWxJbnRlcmZhY2VNZXRob2ROYW1lcQB+\n"
                        + "AAhMACJmdW5jdGlvbmFsSW50ZXJmYWNlTWV0aG9kU2lnbmF0dXJlcQB+AAhMAAlpbXBsQ2xhc3Nx\n"
                        + "AH4ACEwADmltcGxNZXRob2ROYW1lcQB+AAhMABNpbXBsTWV0aG9kU2lnbmF0dXJlcQB+AAhMABZp\n"
                        + "bnN0YW50aWF0ZWRNZXRob2RUeXBlcQB+AAh4cAAAAAZ1cgATW0xqYXZhLmxhbmcuT2JqZWN0O5DO\n"
                        + "WJ8QcylsAgAAeHAAAAAAdnIASG9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuY29sbGVjdG9y\n"
                        + "LlN1bW1hcnlTdGF0aXN0aWNzU2VyYWxpemF0aW9uVGVzdAAAAAAAAAAAAAAAeHB0AEBvcmcvZWNs\n"
                        + "aXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0ludEZ1bmN0aW9u\n"
                        + "dAAKaW50VmFsdWVPZnQAFShMamF2YS9sYW5nL09iamVjdDspSXQASG9yZy9lY2xpcHNlL2NvbGxl\n"
                        + "Y3Rpb25zL2ltcGwvY29sbGVjdG9yL1N1bW1hcnlTdGF0aXN0aWNzU2VyYWxpemF0aW9uVGVzdHQA\n"
                        + "IGxhbWJkYSRzZXJpYWxpemVkRm9ybSQ0ZTMwYzhiMiQxcQB+ABBxAH4AEHhzcQB+AAJ3BAAAAAFx\n"
                        + "AH4ABHNxAH4ABQAAAAZ1cQB+AAoAAAAAcQB+AA10AEFvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9h\n"
                        + "cGkvYmxvY2svZnVuY3Rpb24vcHJpbWl0aXZlL0xvbmdGdW5jdGlvbnQAC2xvbmdWYWx1ZU9mdAAV\n"
                        + "KExqYXZhL2xhbmcvT2JqZWN0OylKcQB+ABF0ACBsYW1iZGEkc2VyaWFsaXplZEZvcm0kMTM1MGMy\n"
                        + "YjMkMXEAfgAYcQB+ABh4c3EAfgACdwQAAAABcQB+AARzcQB+AAUAAAAGdXEAfgAKAAAAAHEAfgAN\n"
                        + "dABDb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL2Jsb2NrL2Z1bmN0aW9uL3ByaW1pdGl2ZS9E\n"
                        + "b3VibGVGdW5jdGlvbnQADWRvdWJsZVZhbHVlT2Z0ABUoTGphdmEvbGFuZy9PYmplY3Q7KURxAH4A\n"
                        + "EXQAIGxhbWJkYSRzZXJpYWxpemVkRm9ybSRjMzhlYjczZSQxcQB+AB9xAH4AH3hzcQB+AAJ3BAAA\n"
                        + "AAFxAH4ABHNyAEdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmNvbGxlY3Rvci5TZXJpYWxp\n"
                        + "emFibGVJbnRTdW1tYXJ5U3RhdGlzdGljcwAAAAAAAAABDAAAeHB3GAAAAAAAAAAAf////4AAAAAA\n"
                        + "AAAAAAAAAHh4c3EAfgACdwQAAAABcQB+AARzcgBIb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1w\n"
                        + "bC5jb2xsZWN0b3IuU2VyaWFsaXphYmxlTG9uZ1N1bW1hcnlTdGF0aXN0aWNzAAAAAAAAAAEMAAB4\n"
                        + "cHcgAAAAAAAAAAB//////////4AAAAAAAAAAAAAAAAAAAAB4eHNxAH4AAncEAAAAAXEAfgAEc3IA\n"
                        + "Sm9yZy5lY2xpcHNlLmNvbGxlY3Rpb25zLmltcGwuY29sbGVjdG9yLlNlcmlhbGl6YWJsZURvdWJs\n"
                        + "ZVN1bW1hcnlTdGF0aXN0aWNzAAAAAAAAAAEMAAB4cHcwAAAAAAAAAAB/8AAAAAAAAP/wAAAAAAAA\n"
                        + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeHh4",
                new SummaryStatistics<>()
                        .addIntFunction("1", each -> 1)
                        .addLongFunction("1", each -> 1L)
                        .addDoubleFunction("1", each -> 1.0d));
    }
}
