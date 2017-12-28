9.1.0 (January 2018)
====================

This is the 9.1.0 minor release. Main theme of this release to converge the API of Primitive and Object Collections. 
This release is also used to prepare for Eclipse Photon contribution and enhance OSGi support based on user feedback.

New Functionality
-----------------

* Implemented collectWithIndex() on OrderedIterable. 
* Implemented collectWithIndex() on PrimitiveOrderedIterable. 
* Implemented collectWithIndex() on ListIterate. 
* Implemented collectWithIndex() on RandomAccessListIterate. 
* Implemented zip() on primitive lists.
* Implemented zip() on IntIterable.
* Implemented zipInt() on IntIterable.
* Implemented removeIf(PrimitivePredicate) for MutablePrimitiveCollections.
* Implemented MutablePrimitivePrimitiveMap.putPair(PrimitivePrimitivePair). 
* Implemented MutablePrimitiveObjectMap.putPair(PrimitiveObjectPair<V>). 
* Implemented MutableObjectPrimitiveMap.putPair(ObjectPrimitivePair<K>).
* Implemented MutableMapIterable.putPair(Pair<K, V> keyValue).
* Implemented trimToSize() on UnifiedMapWithHashingStrategy.
* Implemented countBy() on Collectors2.
* Implemented groupByEach() on Collectors2.
* Implemented groupByUniqueKey() on Collectors2.
* Implemented MultiReaderList factory.
* Added withInitialCapacity() and ofInitialCapacity() on MutableList factory.
* Added withInitialCapacity() and ofInitialCapacity() on MultiReaderList factory.
* Implemented a factory class for Strings.

Bug Fixes
-------------------

* Fixed concurrency issues in EntrySet.removeIf() for ConcurrentHashMap and ConcurrentHashMapUnsafe.

Tech Debt Reduction
-------------------

* Removed unnecessary probe() call in PrimitiveObjectHashMap.
* Added Javadoc to Pair, Twin, Tuples, PrimitiveTuples, ObjectPrimitivePair, PrimitivePrimitivePair and PrimitiveObjectPair.
* Upgraded Maven Enforcer and Maven Javadoc plugins.
* Enabled unused import checkstyle configuration and fixed offenders.
* Enabled Java 9 unit test build on Travis.

OSGi
---------------

* Added a feature for Eclipse Collections

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>9.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>9.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>9.1.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>9.1.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:9.1.0'
compile 'org.eclipse.collections:eclipse-collections:9.1.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:9.1.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:9.1.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="9.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="9.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="9.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="9.1.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/9.1.0/repository
