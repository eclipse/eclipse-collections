/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api;

import java.util.Optional;

import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;

public interface ShortCircuitIterable<T>
{
    T detect(Predicate<? super T> predicate);

    <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    Optional<T> detectOptional(Predicate<? super T> predicate);

    <P> Optional<T> detectWithOptional(Predicate2<? super T, ? super P> predicate, P parameter);

    T detectIfNone(Predicate<? super T> predicate, Function0<? extends T> function);

    <P> T detectWithIfNone(Predicate2<? super T, ? super P> predicate, P parameter, Function0<? extends T> function);

    boolean anySatisfy(Predicate<? super T> predicate);

    <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter);

    boolean allSatisfy(Predicate<? super T> predicate);

    <P> boolean allSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter);

    boolean noneSatisfy(Predicate<? super T> predicate);

    <P> boolean noneSatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter);
}
