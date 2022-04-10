11.0.0
====================

This is the 11.0.0 major release.

Central theme of this release is addition of new APIs, features as requested by the community and decrease Tech Debt.

The Eclipse Collections team gives a huge thank you to everyone who participated in this release.

# New Functionality
-----------------
* Added toImmutableList/Set/Bag/Map/BiMap to RichIterable.
* Added toImmutableSortedList/Set/Bag to RichIterable.
* Added toImmutableSortedBag/List/Set with Comparator to RichIterable. 
* Added toImmutableSortedBagBy/ListBy/SetBy with Function to RichIterable.
* Added selectWithIndex and rejectWithIndex to OrderedIterable and ListIterable.
* Added putAllMapIterable method to MutableMap.
* Added withMapIterable to MutableMap.
* Added newWithMap and newWithMapIterable to ImmutableMap.
* Added anySatisfyWithOccurrences, allSatisfyWithOccurrences, noneSatisfyWithOccurrences, detectWithOccurrences to Bag.
* Added covariant overrides for sortThis().
* Added covariant return types to methods in MultiReaderList that return `this`.
* Added primitive singleton iterator.
* Added union, intersect, difference, symmetric difference, cartesianProduct , isSubsetOf, isProperSubsetOf to primitive sets.
* Added containsAny and containsNone on primitive iterables.
* Added toSortedList(Comparator) and toSortedListBy(Function) to primitive iterables.
* Added isEqual and isSame to Pair and Triple as default methods.
* Added converters from Pair and Triple to List types.
* Added toImmutableSortedBagBy to Collectors2.
* Added toImmutableSortedMap and toImmutableSortedMapBy to Collectors2.
* Added toSortedMap and toSortedMapBy to Collectors2.
* Added Norwegian translation for the Eclipse Collections website.
* Added ClassComparer utility. 

# Optimizations
----------------------
* Optimized ImmutableListFactoryImpl.withAll(Iterable).
* Optimized containsAll on primitive iterables.
* Optimized primitive hash set newSet and withAll.
* Optimized sumByLong and sumByInt Primitive methods for Bags.
* Optimized toImmutable on primitive sets.
* Optimized union and difference on SetIterables.
* Added enhanced for-loop syntax wherever as possible.

# Tech Debt Reduction
---------------------
* Deprecated  ImmutableArrayStack.
* Fixed bug with noneSatisfy for ImmutableBooleanEmptySet.
* Fixed compiler errors for JDK-15-EA.
* Fixed return types of aggregateBy().
* Fixed types on Multimap.*MultiValues().
* Fixed primitive hash map values collections removed  for special key zero.
* Fixed CollectIterable detect methods.
* Added missing overrides for toImmutable methods on synchronized primitive object maps.
* Added missing overrides of aggregateBy() and aggregateInPlaceBy().
* Added documentation on serializing Eclipse Collections with jackson.
* Added logic to throw UnsupportedOperationException on calling withMap method in FixedSizeMap. This is a breaking change.
* Made org.eclipse.collections.impl.list.Interval#goForward private. This is a breaking change.
* Replaced HashingStrategies.longHashCode with Java 8 Long.hashCode. This is a breaking change.
* Deleted version.properties as the file is no longer needed.
* Removed extra calls to map.get in sumByDoubleFunction and sumByFloatFunction.
* Removed implementations of OrderedIterable.toStack() that can use the default implementation instead.
* Removed implementations of aggregateBy and aggregateInPlaceBy that can use default implementations instead.
* Removed references to deleted classes in spotbugs-exclude file.

# Removed Functionality
--------------------------
* Removed Verify.assertThrows() which takes a Runnable in favor of Assert.assertThrows(). This is a breaking change.
* Removed assertNotEquals() from Verify as Assert already has the same API. This is a breaking change.

# Build Changes
-----------------
* Upgraded EBR plugin to 1.3.0.
* Upgraded actions/cache to 2.1.6.
* Upgraded actions/upload-artifact to 2.2.4.
* Upgraded antlr ST4 to 2.1.5.
* Upgraded checkstyle plugin to 3.1.2.
* Upgraded checkstyle to 8.42.
* Upgraded codehaus maven plugin to 2.8.1.
* Upgraded jacoco to v2.2.3.
* Upgraded jmh-core to 1.33.
* Upgraded maven-resources-plugin to 3.2.0.
* Upgraded to JUnit 4.13.1.
* Upgraded to setup-java v2.
* Skip p2 repository module during EA builds and Java 15+ builds.
* Enabled Dependabot v2.
* Added GitHub action to generate code coverage report.
* Added JDK Early Access GitHub Actions.

# Breaking Changes
-------------------------
Warning: These changes are already mentioned above. The list below might not be exhaustive, make sure to test your application and usages to verify.

* Added logic to throw UnsupportedOperationException on calling withMap method in FixedSizeMap.
* Made org.eclipse.collections.impl.list.Interval#goForward private.
* Replaced HashingStrategies.longHashCode with Java 8 Long.hashCode.
* Removed Verify.assertThrows() which takes a Runnable in favor of Assert.assertThrows().
* Removed assertNotEquals() from Verify as Assert already has the same API.

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
  <version>11.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>11.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>11.0.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>11.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'org.eclipse.collections:eclipse-collections-api:11.0.0'
implementation 'org.eclipse.collections:eclipse-collections:11.0.0'
testImplementation 'org.eclipse.collections:eclipse-collections-testutils:11.0.0'
implementation 'org.eclipse.collections:eclipse-collections-forkjoin:11.0.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="11.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="11.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="11.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="11.0.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/11.0.0/repository
