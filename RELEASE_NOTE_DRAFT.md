9.2.0
====================

This is the 9.2.0 minor release.

New Functionality
-----------------

* Implemented flatCollectWith() on RichIterable.
* Implemented toSortedMapBy() on RichIterable.
* Implemented Bag#selectDuplicates().
* Implemented Bag#selectUnique().
* Implemented OrderedMapAdapter, the first implementation of MutableOrderedMap.
* Implemented chunk() on Primitive Iterables.
* Implemented newEmpty() on MutablePrimitiveCollection.
* Implemented PrimitiveBag#selectDuplicates().
* Implemented PrimitiveBag#selectUnique().
* Implemented toStringOfItemToCount() on Primitive Bags.
* Added MultiReader factories to Sets factory.
* Added MultiReader factories to Bags factory.
* Implemented summarizeDouble(), summarizeFloat(), summarizeLong(), summarizeInt on Procedures2.
* Implemented aggregateBy() on Collectors2.
* Implemented countByEach() on Collectors2.
* Implemented assertPostSerializedEqualsHashCodeAndToString() on Verify.
* Added jcstress-based tests for Concurrent Collections.

Optimizations
-------------------

* Optimized zip() by pre-sizing target.
* Optimized UnifiedMap#detectOptional(org.eclipse.collections.api.block.predicate.Predicate2) to not use an iterator.
* Optimized groupByUniqueKey() by pre-sizing the target maps. 

Bug Fixes
-------------------

* Fixed PrimitiveHashSet#iterator()#remove() to not rehash.
* Fixed PrimitiveHashMap#addToValue() to return correct value after rehash and grow.
* Fixed ConcurrentHashMap#iterator() with size close to zero.
* Fixed ConcurrentHashMapUnsafe#iterator() with size close to zero.
* Fixed equals() and hashCode() in IntInterval to handle edge case for negative from, to, step.
* Fixed invalid cast in UnifiedSet#trimToSize().
* Fixed toImmutable(), asUnmodifiable() implementations in synchronized primitive collections and maps.

Tech Debt Reduction
-------------------

* Made Primitive Collections non-final.
* Marked index methods non-final in UnifiedMap, UnifiedMapWithHashingStrategy, UnifiedSet and UnifiedSetWithHashingStrategy.
* Marked HashingStrategy protected in UnifiedMapWithHashingStrategy and UnifiedSetWithHashingStrategy.
* Updated UnifiedMap, UnifiedMapWithHashingStrategy, UnifiedSet and UnifiedSetWithHashingStrategy to use newEmpty().
* Fixed static code analysis inspection violations.
* Fixed Javadoc and whitespace formatting.
* Removed unused imports from Primitive classes.
* Removed redundant imports from Primitive classes.
* Enabled CheckStyle analysis for generated code.
* Removed unnecessary override in test for selectByOccurrences().
* Removed Java-8 check from tests.
* Added UnifiedMapWithHashingStrategyNoIteratorTest.

Library Upgrades
-------------------

* Upgraded JMH to 1.19
* Upgraded CheckStyle to 8.8.
* Upgraded JaCoCo to 0.8.0.

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>9.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>9.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>9.2.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>9.2.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:9.2.0'
compile 'org.eclipse.collections:eclipse-collections:9.2.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:9.2.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:9.2.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="9.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="9.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="9.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="9.2.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/9.2.0/repository
