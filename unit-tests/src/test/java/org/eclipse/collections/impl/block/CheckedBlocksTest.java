/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block;

import java.io.IOException;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.function.checked.CheckedFunction;
import org.eclipse.collections.impl.block.function.checked.CheckedFunction0;
import org.eclipse.collections.impl.block.function.checked.CheckedFunction2;
import org.eclipse.collections.impl.block.predicate.checked.CheckedPredicate;
import org.eclipse.collections.impl.block.predicate.checked.CheckedPredicate2;
import org.eclipse.collections.impl.block.procedure.checked.CheckedObjectIntProcedure;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure;
import org.eclipse.collections.impl.block.procedure.checked.CheckedProcedure2;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.test.Verify;
import org.eclipse.collections.impl.utility.ListIterate;
import org.eclipse.collections.impl.utility.MapIterate;
import org.junit.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.eclipse.collections.impl.factory.Iterables.mList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CheckedBlocksTest
{
    @Test
    public void checkedFunction2CheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            Function2<String, String, String> block =
                    new CheckedFunction2<String, String, String>()
                    {
                        @Override
                        public String safeValue(String argument1, String argument2) throws IOException
                        {
                            throw new IOException("fail");
                        }
                    };
            block.value("1", "2");
        });
    }

    @Test
    public void checkedFunction2RuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            Function2<String, String, String> block =
                    new CheckedFunction2<String, String, String>()
                    {
                        @Override
                        public String safeValue(String argument1, String argument2)
                        {
                            throw new LocalException();
                        }
                    };
            block.value("1", "2");
        });
    }

    @Test
    public void checkedCodeBlockCheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            Function0<String> function = new CheckedFunction0<String>()
            {
                @Override
                public String safeValue() throws IOException
                {
                    throw new IOException("fail");
                }
            };
            function.value();
        });
    }

    @Test
    public void checkedCodeBlockRuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            Function0<String> function = new CheckedFunction0<String>()
            {
                @Override
                public String safeValue()
                {
                    throw new LocalException();
                }
            };
            function.value();
        });
    }

    @Test
    public void checkedProcedureCheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            Procedure<String> block = new CheckedProcedure<String>()
            {
                @Override
                public void safeValue(String object) throws IOException
                {
                    throw new IOException("fail");
                }
            };
            block.value("1");
        });
    }

    @Test
    public void checkedProcedureRuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            Procedure<String> block = new CheckedProcedure<String>()
            {
                @Override
                public void safeValue(String object)
                {
                    throw new LocalException();
                }
            };
            block.value("1");
        });
    }

    @Test
    public void checkedObjectIntProcedureCheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            ObjectIntProcedure<String> block = new CheckedObjectIntProcedure<String>()
            {
                @Override
                public void safeValue(String object, int index) throws IOException
                {
                    throw new IOException("fail");
                }
            };
            block.value("1", 1);
        });
    }

    @Test
    public void checkedObjectIntProcedureRuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            ObjectIntProcedure<String> block = new CheckedObjectIntProcedure<String>()
            {
                @Override
                public void safeValue(String object, int index)
                {
                    throw new LocalException();
                }
            };
            block.value("1", 1);
        });
    }

    @Test
    public void checkedFunctionCheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            Function<String, String> block =
                    new CheckedFunction<String, String>()
                    {
                        @Override
                        public String safeValueOf(String object) throws IOException
                        {
                            throw new IOException("fail");
                        }
                    };
            block.valueOf("1");
        });
    }

    @Test
    public void checkedFunctionRuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            Function<String, String> block =
                    new CheckedFunction<String, String>()
                    {
                        @Override
                        public String safeValueOf(String object)
                        {
                            throw new LocalException();
                        }
                    };
            block.valueOf("1");
        });
    }

    @Test
    public void checkedPredicateCheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            Predicate<String> block = new CheckedPredicate<String>()
            {
                @Override
                public boolean safeAccept(String object) throws IOException
                {
                    throw new IOException("fail");
                }
            };
            block.accept("1");
        });
    }

    @Test
    public void checkedPredicateRuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            Predicate<String> block = new CheckedPredicate<String>()
            {
                @Override
                public boolean safeAccept(String object)
                {
                    throw new LocalException();
                }
            };
            block.accept("1");
        });
    }

    @Test
    public void checkedPredicate2CheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            Predicate2<String, String> block =
                    new CheckedPredicate2<String, String>()
                    {
                        @Override
                        public boolean safeAccept(String object, String param) throws IOException
                        {
                            throw new IOException("fail");
                        }
                    };
            block.accept("1", "2");
        });
    }

    @Test
    public void checkedPredicate2RuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            Predicate2<String, String> block =
                    new CheckedPredicate2<String, String>()
                    {
                        @Override
                        public boolean safeAccept(String object, String param)
                        {
                            throw new LocalException();
                        }
                    };
            block.accept("1", "2");
        });
    }

    @Test
    public void checkedProcedure2CheckedException()
    {
        Verify.assertThrowsWithCause(RuntimeException.class, IOException.class, () ->
        {
            Procedure2<String, String> block = new CheckedProcedure2<String, String>()
            {
                @Override
                public void safeValue(String object, String parameter) throws IOException
                {
                    throw new IOException("fail");
                }
            };
            block.value("1", "2");
        });
    }

    @Test
    public void checkedProcedure2RuntimeException()
    {
        assertThrows(LocalException.class, () ->
        {
            Procedure2<String, String> block = new CheckedProcedure2<String, String>()
            {
                @Override
                public void safeValue(String object, String parameter)
                {
                    throw new LocalException();
                }
            };
            block.value("1", "2");
        });
    }

    private static final class LocalException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;

        private LocalException()
        {
            super("fail");
        }
    }

    @Test
    public void codeBlockFailure()
    {
        Function0<Object> function = new CheckedFunction0<Object>()
        {
            @Override
            public Object safeValue() throws InterruptedException
            {
                throw new InterruptedException();
            }
        };
        MutableMap<String, Object> values = UnifiedMap.newMap();
        assertThrows(RuntimeException.class, () -> MapIterate.getIfAbsentPut(values, "test", function));
    }

    @Test
    public void codeBlockRuntimeException()
    {
        Function0<Object> function = new CheckedFunction0<Object>()
        {
            @Override
            public Object safeValue()
            {
                throw new RuntimeException();
            }
        };
        MutableMap<String, Object> values = UnifiedMap.newMap();
        assertThrows(RuntimeException.class, () -> MapIterate.getIfAbsentPut(values, "test", function));
    }

    @Test
    public void codeBlockSuccess()
    {
        Function0<Object> function = new CheckedFunction0<Object>()
        {
            @Override
            public Object safeValue()
            {
                return null;
            }
        };
        MutableMap<String, Object> values = UnifiedMap.newMap();
        MapIterate.getIfAbsentPut(values, "test", function);
    }

    @Test
    public void procedureFailure()
    {
        Procedure<Object> block = new CheckedProcedure<Object>()
        {
            @Override
            public void safeValue(Object object) throws InterruptedException
            {
                throw new InterruptedException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").forEach(block));
    }

    @Test
    public void procedureRuntimeException()
    {
        Procedure<Object> block = new CheckedProcedure<Object>()
        {
            @Override
            public void safeValue(Object object)
            {
                throw new RuntimeException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").forEach(block));
    }

    @Test
    public void procedureSuccess()
    {
        Procedure<Object> block = new CheckedProcedure<Object>()
        {
            @Override
            public void safeValue(Object object)
            {
            }
        };
        iList("test").forEach(block);
    }

    @Test
    public void objectIntProcedureFailure()
    {
        ObjectIntProcedure<Object> block = new CheckedObjectIntProcedure<Object>()
        {
            @Override
            public void safeValue(Object object, int index) throws InterruptedException
            {
                throw new InterruptedException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").forEachWithIndex(block));
    }

    @Test
    public void objectIntProcedureRuntimeException()
    {
        ObjectIntProcedure<Object> block = new CheckedObjectIntProcedure<Object>()
        {
            @Override
            public void safeValue(Object object, int index)
            {
                throw new RuntimeException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").forEachWithIndex(block));
    }

    @Test
    public void objectIntProcedureSuccess()
    {
        ObjectIntProcedure<Object> block = new CheckedObjectIntProcedure<Object>()
        {
            @Override
            public void safeValue(Object object, int index)
            {
            }
        };
        iList("test").forEachWithIndex(block);
    }

    @Test
    public void functionFailure()
    {
        Function<Object, Object> block = new CheckedFunction<Object, Object>()
        {
            @Override
            public Object safeValueOf(Object object) throws InterruptedException
            {
                throw new InterruptedException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").collect(block));
    }

    @Test
    public void functionRuntimeException()
    {
        Function<Object, Object> block = new CheckedFunction<Object, Object>()
        {
            @Override
            public Object safeValueOf(Object object)
            {
                throw new RuntimeException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").collect(block));
    }

    @Test
    public void functionSuccess()
    {
        Function<Object, Object> block = new CheckedFunction<Object, Object>()
        {
            @Override
            public Object safeValueOf(Object object)
            {
                return null;
            }
        };
        iList("test").collect(block);
    }

    @Test
    public void predicateFailure()
    {
        Predicate<Object> block = new CheckedPredicate<Object>()
        {
            @Override
            public boolean safeAccept(Object object) throws InterruptedException
            {
                throw new InterruptedException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").select(block));
    }

    @Test
    public void predicateRuntimeException()
    {
        Predicate<Object> block = new CheckedPredicate<Object>()
        {
            @Override
            public boolean safeAccept(Object object)
            {
                throw new RuntimeException();
            }
        };
        assertThrows(RuntimeException.class, () -> iList("test").select(block));
    }

    @Test
    public void predicateSuccess()
    {
        Predicate<String> alwaysTrueBlock = new CheckedPredicate<String>()
        {
            @Override
            public boolean safeAccept(String s)
            {
                return true;
            }
        };
        ImmutableList<String> list = iList("test");
        assertEquals(list, list.select(alwaysTrueBlock));

        Predicate<String> alwaysFalseBlock = new CheckedPredicate<String>()
        {
            @Override
            public boolean safeAccept(String s)
            {
                return false;
            }
        };
        Verify.assertEmpty(list.select(alwaysFalseBlock));
    }

    @Test
    public void procedure2Failure()
    {
        Procedure2<Object, Object> block = new CheckedProcedure2<Object, Object>()
        {
            @Override
            public void safeValue(Object argument1, Object argument2) throws InterruptedException
            {
                throw new InterruptedException();
            }
        };
        assertThrows(RuntimeException.class, () -> ListIterate.forEachInBoth(mList("test"), mList("test"), block));
    }

    @Test
    public void procedure2RuntimeException()
    {
        Procedure2<Object, Object> block = new CheckedProcedure2<Object, Object>()
        {
            @Override
            public void safeValue(Object argument1, Object argument2)
            {
                throw new RuntimeException();
            }
        };
        assertThrows(RuntimeException.class, () -> ListIterate.forEachInBoth(mList("test"), mList("test"), block));
    }

    @Test
    public void procedure2Success()
    {
        Procedure2<Object, Object> block = new CheckedProcedure2<Object, Object>()
        {
            @Override
            public void safeValue(Object argument1, Object argument2)
            {
                // nop
            }
        };
        ListIterate.forEachInBoth(mList("test"), mList("test"), block);
    }

    @Test
    public void predicate2Failure()
    {
        Predicate2<Object, Object> block = new CheckedPredicate2<Object, Object>()
        {
            @Override
            public boolean safeAccept(Object object, Object param) throws InterruptedException
            {
                throw new InterruptedException();
            }
        };
        assertThrows(RuntimeException.class, () -> mList("test").selectWith(block, null));
    }

    @Test
    public void predicate2RuntimeException()
    {
        Predicate2<Object, Object> block = new CheckedPredicate2<Object, Object>()
        {
            @Override
            public boolean safeAccept(Object object, Object param)
            {
                throw new RuntimeException();
            }
        };
        assertThrows(RuntimeException.class, () -> mList("test").selectWith(block, null));
    }

    @Test
    public void predicate2Success()
    {
        Predicate2<String, Object> alwaysTrueBlock = new CheckedPredicate2<String, Object>()
        {
            @Override
            public boolean safeAccept(String s, Object param)
            {
                return true;
            }
        };
        MutableList<String> list = mList("test");
        assertEquals(list, list.selectWith(alwaysTrueBlock, null));

        Predicate2<String, Object> alwaysFalseBlock = new CheckedPredicate2<String, Object>()
        {
            @Override
            public boolean safeAccept(String s, Object param)
            {
                return false;
            }
        };
        Verify.assertEmpty(list.selectWith(alwaysFalseBlock, null));
    }

    @Test
    public void checkedFunction2SafeValue() throws Exception
    {
        CheckedFunction2<Integer, Integer, Integer> checkedFunction2 = new CheckedFunction2<Integer, Integer, Integer>()
        {
            @Override
            public Integer safeValue(Integer argument1, Integer argument2)
            {
                return argument1 + argument2;
            }
        };
        assertEquals(Integer.valueOf(5), checkedFunction2.safeValue(2, 3));
    }
}
