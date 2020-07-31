10.3.0
====================

This is the 10.3.0 minor release.
This release is to provide new features requested by the community, bug fix for edge case issues, and reduce tech debt.

# New Functionality
-----------------
* Added Hindi Translation of Eclipse Collections website.
* Added `flatCollect` support primitive collections.
* Added `withMap()` to MutableMap.
* Added `containsBy` to RichIterable.
* Added `forEachInBoth` to `ListIterable` with javadoc.
* Added new APIs `ofOccurrences` and `withOccurrences`, to bag mutable and immutable factories.
* Added `wrapCopy()` to primitive lists to mirror functionality in FastList.
* Added singly-linked implementation of immutable stacks.
* Added `withInitialCapacity()` to primitive List and Set factories.
* Added `toArray()` method to primitive iterables which takes an array as a parameter to store the elements of the iterable.
* Added default `aggregateBy` method to RichIterable that takes a target Map.
* Added `shuffleThis()` operation to primitive lists.
* Added sorting of primitive lists by indirect comparison.
* Added `fromToExclusive` in `Interval`.
* Implemented `swap()` method on mutable primitive lists.
* Implemented `MapIterable.getOrDefault()` to allow easy inter-op.
* Implemented `subList()` on `IntInterval`.
* Introduced `pitest` mutation testing.
* Implemented `Triples`.
* Implemented `LongInterval`.
* Implemented `aggregateBy` for `MapIterable` with a variant to aggregate on key and value.
* Implemented Null Safe Comparators by Function.

# Bug Fixes
-----------------
* Fixed size edge case issues in Interval and IntInterval.
* Fixed Javadoc warnings, code generation errors.
* Fixed inspections, line-wrapping and whitespace violations.
* Fixed symmetry issues with factory methods in Multimaps factory.
* Added CheckStyle checks for comma-separated lists that are partially wrapped.

# Tech Debt Reduction
---------------------
* Optimized `removeIf()` implementation on BooleanArrayList.
* Increased test coverage for `reduceIfEmpty` on primitive iterables, `MapIterable.getIfAbsent*()`, `MultimapsTest`.
* Used `org.eclipse.collections.api.factory` instead of `org.eclipse.collections.impl.factory` wherever possible.
* Made `primitive*HashMap.keySet()` serializable.
* Made `sun.misc` an optional Import-Package in OSGi metadata.
* Moved primitiveSort.stg to impl/utility.
* Optimized collect methods for primitive Immutable Singleton Bags, Sets and Lists.
* Optimized implementations of `aggregateBy` in Bags to use `forEachWithOccurrences`.
* Pulled up `ListIterable.binarySearch()`, `OrderedIterable.toStack()`, `RichIterable.groupByUniqueKey()`,`aggregateBy()` as default methods.
* Implemented `with()`, `without()`, `withAll()`, `withoutAll()` as default methods.
* Refactored `PersonAndPetKatatTest` to use newer APIs.
* Removed duplicate `forEach` overrides.
* Created a simple utility to aid in Javadoc creation.
* Updated common primitive stack template for `BooleanArrayStack` code generation. 
* Used direct formulas to calculate `sum()`, `mean()`, and `average()` on `IntInterval`.
* Memoized `size()` value on `IntInterval` and `Interval`.
* Optimized `toImmutable()` method on FastList and Primitive Lists to avoids creating a redundant array copy.
* Added @Override annotations for tap() (and one toString()) implementations.

# Build Changes
-----------------
* Added OSGi metadata and correct repository build.
* Added JDK 14 builds and JDK 15 EA builds.
* Remove redundant travis builds as Eclipse Collections uses GitHub Actions Worfklow. 
* Upgraded maven from 3.6.1 to 3.6.3.
* Upgraded maven plugins.
* Upgraded ebr plugin to 1.2.0.
* Upgraded tycho plugin from 1.2.0 to 1.7.0.
* Upgraded dependencies used by tests and builds like jmh, guava, slf4j.
* Upgraded CheckStyle from 8.29 to 8.34 and other settings.

# Documentation Changes
-----------------
* Added badges to README.md for GitHub Actions build.
* Added `Working with GitHub` wiki page.
* Added Javadocs for `immutableObjectPrimitiveMap`, `immutablePrimitiveObjectMap`, `immutablePrimitivePrimitiveMap`.
* Added Javadocs for `mutableObjectPrimitiveMap`, `mutablePrimitiveObjectMap`, `mutablePrimitivePrimitiveMap`, `mutablePrimitiveValuesMap`.
* Added Javadocs for `primitiveObjectMaps`, `primitivePrimitiveMaps`, `primitiveValuesMaps`, `objectPrimitiveMaps`.
* Improved documentation of `Function2`, `Function3` and `MutableCollection#injectIntoWith`.
* Added README_EXAMPLES.md.
* Fixed gradle dependency settings in website.

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
compile 'org.eclipse.collections:eclipse-collections-api:10.3.0'
compile 'org.eclipse.collections:eclipse-collections:10.3.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:10.3.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:10.3.0'
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
