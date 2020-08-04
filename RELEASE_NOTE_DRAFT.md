10.3.0
====================

This is the 10.3.0 minor release. Even though this is a minor release, by no means the release was minor. This release is feature packed with numerous contributions from many contributors.
This release is to provide new features requested by the community, various optimizations, reduce tech debt, and includes OSGi integration improvements.

The Eclipse Collections team gives a huge thank you to everyone who participated in this release.

# New Functionality
-----------------
* Implemented RichIterable.containsBy().
* Implemented RichIterable.flatCollectPrimitive().
* Implemented ListIterable.forEachInBoth().
* Implemented MapIterable.getOrDefault() to allow easy inter-op.
* Implemented MapIterable.aggregateBy() which can aggregate on key and value.
* Implemented MutableMap.withMap().
* Implemented primitiveIterable.toArray() which takes an array as a parameter to store the elements of the iterable.
* Implemented primitiveLists.withInitialCapacity() and primitiveSets.withInitialCapacity().
* Implemented primitiveList.wrapCopy() to create a list by first copying the array passed in. 
* Implemented MutablePrimitiveList.shuffleThis().
* Implemented MutablePrimitiveList.swap().
* Implemented variant of MutablePrimitiveList.sortThis() which takes Comparator as input.
* Implemented variants of MutablePrimitiveList.sortThisBy() which takes primitiveToObjectFunction and Comparator as inputs.
* Made primitive*HashMap.keySet() serializable.
* Implemented singly-linked ImmutableStack.
* Implemented LongInterval.
* Implemented IntInterval.subList()
* Implemented Interval.fromToExclusive().
* Implemented Triples.
* Implemented Tuples.identicalTwin(), Tuples.identicalTriplet().
* Implemented missing methods in Multimap factory.
* Implemented Comparators.byFunctionNullsFirst(), Comparators.byFunctionNullsLast().
* Implemented Bags.ofOccurrences() and Bags.withOccurrences().
* Added Hindi Translation of Eclipse Collections website.

# Tech Debt Reduction
---------------------
* Replaced usage of org.eclipse.collections.impl.factory with org.eclipse.collections.api.factory wherever possible.
* Added default method for RichIterable.aggregateBy() that takes a target Map.
* Pulled up RichIterable.groupByUniqueKey() as default methods.
* Pulled up implementations of aggregateBy() as default methods.
* Pulled up implementations of with(), without(), withAll(), withoutAll() as default methods.
* Pulled up OrderedIterable.toStack() as a default method.
* Pulled up ListIterable.binarySearch() as default methods.
* Simplified implementation of RichIterable.containsBy().
* Added missing tests for MapIterable.getIfAbsent*().
* Added missing tests for MultimapsTest.
* Added missing tests for primitiveIterable.reduceIfEmpty().
* Optimized ImmutableListFactoryImpl.toImmutable() by not creating a redundant array copy
* Optimized Bag.aggregateBy() to use forEachWithOccurrences.
* Optimized primitiveList.toImmutable() by not creating a redundant array copy.
* Optimized collect methods for primitiveImmutableSingletonBag, primitiveImmutableSingletonSet, and primitiveImmutableSingletonList.
* Optimized BooleanArrayList.removeIf().
* Optimized IntInterval.size() and Interval.size() by caching size.
* Optimized IntInterval.sum(), IntInterval.mean(), and IntInterval.average() by using direct formulas.
* Removed duplicate code for implementations of aggregateBy().
* Removed duplicate methods in AbstractMutableBagIterable and AbstractImmutableBagIterable that have been moved up as default methods.
* Removed duplicate overrides for forEach().
* Added Javadocs for primitivePrimitiveMaps, primitiveObjectMaps, objectPrimitiveMaps, primitiveValuesMaps and their hierarchy.
* Improved documentation of Function2, Function3, and MutableCollection.injectIntoWith.
* Enabled code generation of BooleanArrayStack from the common primitive stack template.
* Added missing @Override annotations for tap().
* Added and fixed Checkstyle checks for comma-separated lists that are partially wrapped.
* Fixed Javadoc errors and warnings.
* Fixed whitespace and line-wrapping violations.
* Fixed inspections violations.
* Fixed gradle dependency settings in website.

# OSGi Integration Improvements
-------------------------------
* Added OSGi metadata.
* Fixed OSGi metadata to have sun.misc be an optional import package.
* Fixed project versions published to p2 repository.

# Build Changes
-----------------
* Migrated builds to GitHub Actions.
* Removed Travis builds.
* Added initial integration with Pitest for mutation testing.
* Upgraded Maven to 3.6.3.
* Upgraded dependencies used by tests.
* Upgraded CheckStyle to 8.34
* Upgraded Tycho plugin to 1.7.0
* Upgraded EBR plugin to 1.2.0.
* Upgraded versions of various Maven plugins.

# Note
-------
_We have taken all the measures to ensure all features are captured in the release notes. 
However, release notes compilation is manual, so it is possible that a commit might be missed. 
For a comprehensive list of commits please go through the commit log._

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>10.3.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>10.3.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>10.3.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>10.3.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'org.eclipse.collections:eclipse-collections-api:10.3.0'
implementation 'org.eclipse.collections:eclipse-collections:10.3.0'
testImplementation 'org.eclipse.collections:eclipse-collections-testutils:10.3.0'
implementation 'org.eclipse.collections:eclipse-collections-forkjoin:10.3.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="10.3.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="10.3.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="10.3.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="10.3.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/10.3.0/repository
