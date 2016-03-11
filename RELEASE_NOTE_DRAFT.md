8.0.0
=====

New Functionality
-----------------

* Changed MutableBagIterable.addOccurrences(T item, int occurrences) to return the updated number of occurrences instead of void.
* Implemented Multimap.keySet() to return an unmodifiable SetIterable of keys.
* Implemented MutableMultimap.putAllPairs(Iterable<Pair<K, V>> keyValuePairs).
* Pull up into() from LazyIterable to RichIterable

Optimizations
-------------

* A Placeholder

Bug fixes
---------

* A Placeholder

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

