/*
 * Copyright (c) 2019 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.collection;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;

/**
 * This class decorates a RichIterable to have non short circuit behavior in overridden methods
 *
 * @since 10.0.
 */
public class NonShortCircuitRichIterable<T> extends AbstractForwardingRichIterable<T>
{
    private RichIterable<T> delegate;

    public NonShortCircuitRichIterable(RichIterable<T> richIterable)
    {
        this.delegate = richIterable;
    }

    @Override
    protected RichIterable<T> getDelegate()
    {
        return this.delegate;
    }

    @Override
    public RichIterable<T> asNonShortCircuit()
    {
        return this;
    }

    @Override
    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.getDelegate().collectBoolean(each -> predicate.accept(each)).anySatisfy(x -> x);
    }

    @Override
    public <P> boolean anySatisfyWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate().collectBoolean(each -> predicate.accept(each, parameter)).anySatisfy(x -> x);
    }

    @Override
    public T detect(Predicate<? super T> predicate)
    {
        return this.getDelegate()
            .collect(each -> predicate.accept(each) ? each : null)
            .detect(each -> !Objects.isNull(each));
    }

    @Override
    public <P> T detectWith(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return this.getDelegate()
                .collect(each -> predicate.accept(each, parameter) ? each : null)
                .detect(each -> !Objects.isNull(each));
    }

    @Override
    public Optional<T> detectOptional(Predicate<? super T> predicate)
    {
        return Optional.ofNullable(this.detect(predicate));
    }

    @Override
    public <P> Optional<T> detectWithOptional(Predicate2<? super T, ? super P> predicate, P parameter)
    {
        return Optional.ofNullable(this.detectWith(predicate, parameter));
    }
}
