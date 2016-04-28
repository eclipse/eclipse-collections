8.0.0
=====

New Functionality
-----------------

* Changed MutableBagIterable.addOccurrences(T item, int occurrences) to return the updated number of occurrences instead of void.
* Implemented Bags.mutable.ofAll(Iterable<? extends T> items) and Bags.mutable.withAll(Iterable<? extends T> items).
* Implemented Multimap.keySet() to return an unmodifiable SetIterable of keys.
* Implemented MutableMultimap.putAllPairs(Iterable<Pair<K, V>> keyValuePairs).
* Implemented LazyIterable.takeWhile(Predicate<? super T> predicate) and LazyIterable.dropWhile(Predicate<? super T> predicate).
* Pull up into() from LazyIterable to RichIterable.
* Made StackIterable implement OrderedIterable.

Optimizations
-------------

* Optimize MutableList.chunk() for RandomAccess lists to use the backing array instead of an iterator.

Bug fixes
---------

* Fixed Interval.take(int count) when count is 0. Old behavior was to return an Interval of size 1. This is a behavior breaking change.
* Changed AbstractSynchronizedRichIterable.groupByUniqueKey() to return MapIterable instead of MutableMap.
* Changed AbstractMutableMapIterable.groupByUniqueKey() to return MutableMapIterable instead of MutableMap.
* Changed MutableMapIterable.aggregateBy() to return MutableMap instead of MutableMapIterable.
* Fixed return types on MapIterable.collect(Function2) overrides.
* Fixed return type of UnifiedSetWithHashingStrategy.groupByEach() method to preserve hashing strategy.
* Fixed generics on Multimap.forEachKeyValue().
* Fixed generics on Multimap.forEachKeyMultiValues().
* Made primitive-object maps more bag-like. Change the filtering and transformation methods to return bags since they contain duplicates and no meaningful order.

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>8.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>8.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>8.0.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>8.0.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:8.0.0'
compile 'org.eclipse.collections:eclipse-collections:8.0.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:8.0.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:8.0.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="8.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="8.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="8.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="8.0.0"/>
```

