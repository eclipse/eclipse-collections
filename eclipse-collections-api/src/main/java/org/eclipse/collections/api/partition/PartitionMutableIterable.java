/*
 * Copyright (c) 2016 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.api.partition;

import org.eclipse.collections.api.MutableIterable;

public interface PartitionMutableIterable<T> extends PartitionIterable<T>
{
    @Override
    MutableIterable<T> getSelected();

    @Override
    MutableIterable<T> getRejected();

    PartitionImmutableIterable<T> toImmutable();
}
