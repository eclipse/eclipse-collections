/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.partition.bag.sorted;

import org.eclipse.collections.api.bag.sorted.SortedBag;
import org.eclipse.collections.api.partition.bag.PartitionBag;
import org.eclipse.collections.api.partition.ordered.PartitionReversibleIterable;
import org.eclipse.collections.api.partition.ordered.PartitionSortedIterable;

/**
 * @since 4.2
 */
public interface PartitionSortedBag<T> extends PartitionBag<T>, PartitionSortedIterable<T>, PartitionReversibleIterable<T>
{
    @Override
    SortedBag<T> getSelected();

    @Override
    SortedBag<T> getRejected();
}
