/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.MutableCollection;
import org.eclipse.collections.api.partition.PartitionMutableCollection;

public class PartitionPredicate2Procedure<T, P> implements Procedure<T>
{
    private static final long serialVersionUID = 1L;

    private final Predicate2<? super T, ? super P> predicate;
    private final P parameter;
    private final PartitionMutableCollection<T> partitionMutableCollection;

    public PartitionPredicate2Procedure(Predicate2<? super T, ? super P> predicate, P parameter, PartitionMutableCollection<T> partitionMutableCollection)
    {
        this.predicate = predicate;
        this.parameter = parameter;
        this.partitionMutableCollection = partitionMutableCollection;
    }

    @Override
    public void value(T each)
    {
        MutableCollection<T> bucket = this.predicate.accept(each, this.parameter)
                ? this.partitionMutableCollection.getSelected()
                : this.partitionMutableCollection.getRejected();
        bucket.add(each);
    }
}
