10.0.0
====================

This is the 10.0.0 major release. 
Central theme of this release is addition of new APIs, features as requested by the community and decrease Tech Debt. 
This is our yearly major release.

# New Functionality
-----------------
* Changed collection factories to be services that get loaded by the ServiceLoader in the API package.
* Changed RichIterable.groupByUniqueKey() so that it's target extends MutableMapIterable instead of MutableMap.
* Implemented UnmodifiableMutableOrderedMap.
* Added \<primitive1>\<primitive2>To\<primitive1>Function.
* Added specialized MultiReader interfaces for List, Set and Bag to improve the interface hierarchy.
* Implemented RichIterable.getAny().
* Implemented RichIterable.countByEach().
* Implemented RichIterable.toMap() with target.
* Implemented RichIterable.toBiMap().
* Implemented MutableMapIterable.removeIf().
* Implemented MutableMapIterable.removeAllKeys().
* Implemented Bag.collectWithOccurrences(ObjectIntToObjectFunction).
* Implemented Multimap.collectKeyMultiValues().
* Implemented MutableMultimap.getIfAbsentPutAll().
* Implemented UnifiedSetWithHashingStrategy.addOrReplace(Object).
* Implemented LazyIterate.cartesianProduct().
* Added override for replaceAll() and sort() on List implementations.
* Implemented fromStream(Stream) on Mutable Collection Factories and Immutable Collection Factories.
* Implemented ImmutableSortedBagMultimapFactory for Multimaps
* Implemented HashingStrategySets.ofInitialCapacity() and HashingStrategySets.withInitialCapacity().
* Implemented a Map factory method that takes a Map as a parameter
* Implemented PrimitiveIterable.reduce() and PrimitiveIterable.reduceIfEmpty().
* Implemented PrimitiveList.primitiveStream().
* Implemented PrimitiveMap.updateValues().
* Implemented factory methods to convert Iterable\<BoxedPrimitive> to PrimitiveList, PrimitiveSet, PrimitiveBag, PrimitiveStack.
* Implemented ofInitialCapacity() and withInitialCapacity() to Primitive Map Factories.
* Added ability to create ObjectPrimitiveMap, PrimitiveObjectMap, PrimitivePrimitiveMap from Iterable.

# Optimizations
-----------------
* Revamped and standardized resize/rehash for all primitive hash structures.

# Bug Fixes
-----------------
* Fixed ImmutableDoubletonMap.flipUniqueValues() to throw exception when it has duplicates.
* Added missing toSentinel() calls in UnifiedSet.put(), UnifiedSetWithHashingStrategy.put().
* Added validation in FastList for initial capacity < 0.

# Tech Debt Reduction
---------------------
* Fixed SerializeTestHelper to use try-with-resources.
* Reduced duplicate readlock and writelock code in MultiReaderFastList, MultiReaderHashBag and MultiReaderUnifiedSet.
* Replaced Apache commons-codec by Java 8 Base64 in Verify.
* Added missing bounded wildcards on generic types.
* Added wildcard types in MutableMapIterable.putPair(), MutableMapIterable.add(), MutableMultimap.putAllPairs() and MutableMultimap.add().
* Fixed generics on MultiReaderFastList.newList().
* Added overrides for methods in MutableOrderedMap.
* Fixed assignment order in constructor of IntIntervalSpliterator.
* Removed unnecessary casts, redundant type arguments.
* Fixed invalid cast in UnifiedSet.trimToSize().
* Changed Boxed result array to Primitive array to avoid unnecessary boxing in Primitive reduce.
* Fixed several inspection violations, checkstyle violations and static analysis violations to increase code hygiene. 
* Fixed incorrect org.eclipse.collections.api.list api doc.
* Fixed Javadoc lint errors, legacy Javadoc to update references to Eclipse Collections.
* Moved implementations for forEach(), toSortedListBy(), toSortedSetBy(), toSortedBagsBy(), toSortedMapBy(), toArray(), countBy(), countByWith(), selectUnique(), flatCollectWith(), reverseForEach(), reverseForEachWithIndex() as default methods to interfaces.
* Moved implementations for tap(), select(), selectWith(), reject(), rejectWith(), collect(), collectWith(), collectIf(), flatCollect(), toImmutable(), toReversed(), reverseThis(), shuffleThis() to MutableList as default implementations.
* Removed default implementations for RichIterable.toSortedMapBy(), MutableListFactory.ofInitialCapacity(), selectUnique() from all Bag interfaces viz. Bag, ImmutableBag, ImmutableBagIterable, ImmutablePrimitiveBag, ImmutableSortedBag, MutableBag, MutableBagIterable, MutablePrimitiveBag, MutableSortedBag, PrimitiveBag, SortedBag, UnsortedBag. These were added to allow inclusion in minor release.
* Changed collection constructor factories from enums to singleton classes.
* Upgraded CollectPrimitiveProcedure to be generated at build time by using stg file.
* Added SerializationTest for CollectPrimitiveProcedure.
* Added test coverage for IntInterval, MultiReaderHashBag, CharAdapter, CodePointAdapter, CodePointList, Lists, Primitive Factories, RichIterable.minOptional(), RichIterable.maxOptional(), RichIterable.zip(), MapIterable.detectOptional, MapIterable.detectWithOptional and MutableMapIterable.removeIf().
* Updated IntListJMHTest to include benchmarks using IntStream.
* Reduced benchmark errors by consuming every object created in a benchmark to avoid DCE.

# Removed Functionality
-----------------------
* Removed unused ant build module.
* Removed deprecated classes marked for removal: SummaryStatistics, SerializableIntSummaryStatistics, SerializableLongSummaryStatistics, and SerializableDoubleSummaryStatistics.

# Build Changes
-----------------
* Added Javadoc build for pull requests to ensure correct CI.
* Upgraded maven plugins for build time stability.
* Updated Tycho Version to 1.3.0.
* Configured Travis to run maven using the maven wrapper.
* Fixed 'maven install' for newer versions of Java by using maven profiles to link modules with the version of Java that can build them.
* Fixed maven pluginRepositories settings to include maven central.
* Fixed Travis builds to cache the maven local cache without including Eclipse Collections jars.
* Removed EclipseCollectionsCodeGenerator Task.
* Removed unused dependencies from maven build.
* Removed obsolete maven prerequisites.
* Customized IntelliJ maven argline settings.
* Turned on Eclipse Collections Code Generator plugin on configuration for Eclipse IDE.

# Breaking Changes
------------------
_Warning_: These changes are already mentioned above. The list below might not be exhaustive, make sure to test your application and usages to verify.
* Changed groupByUniqueKey() so that it's target extends MutableMapIterable instead of MutableMap. This breaks binary compatibility.
* Added missing bounded wildcards on generic types.
* Removed deprecated classes marked for removal: SummaryStatistics, SerializableIntSummaryStatistics, SerializableLongSummaryStatistics, and SerializableDoubleSummaryStatistics.
* Removed default implementations for RichIterable.toSortedMapBy(), MutableListFactory.ofInitialCapacity(), selectUnique() from all Bag interfaces viz. Bag, ImmutableBag, ImmutableBagIterable, ImmutablePrimitiveBag, ImmutableSortedBag, MutableBag, MutableBagIterable, MutablePrimitiveBag, MutableSortedBag, PrimitiveBag, SortedBag, UnsortedBag. These were added to allow inclusion in minor release.
* Upgraded CollectPrimitiveProcedure to be generated at build time by using stg file. *This might break serialization*.
* Added validation in FastList for initial capacity < 0.
* Fixed ImmutableDoubletonMap.flipUniqueValues() to throw exception when it has duplicates.

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>10.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>10.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>10.0.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>10.0.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:10.0.0'
compile 'org.eclipse.collections:eclipse-collections:10.0.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:10.0.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:10.0.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="10.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="10.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="10.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="10.0.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/10.0.0/repository
