11.1.0
====================

This is the 11.1.0 minor release.

Central theme of this release is addition of new APIs, features as requested by the community and decrease Tech Debt.

The Eclipse Collections team gives a huge thank you to everyone who participated in this release.

# New Functionality
-----------------
* Added Bag.distinctView(). 
* Added Bags.mutable.withInitialCapacity.
* Added forEachInBoth to Primitive Lists.
* Added MultiReader to Bags factory in API module.
* Added MultiReader to Lists factory in API module.
* Added MultiReader to Sets factory in API module.
* Added Primitive Bag factories in API module.
* Added Primitive Set factories in API module.
* Added Primitive Stack factories in API module.
* Added factory for ObjectPrimitiveHashMapWithHashingStrategy.
* Added withInitialCapacity api in mutableObjectPrimitiveHashingStrategyMapFactory.
* Added ability to create Hashing Strategy Sets, Maps and Bags using Function.
* Added injectInto for primitive types to primitive iterables.
* Added injectIntoKeyValue to MapIterable.
* Added injectIntoKeyValue to objectPrimitiveMap.
* Added injectIntoKeyValue to primitiveObjectMap.
* Added injectIntoKeyValue to primitivePrimitiveMap.
* Added mutable reduction scenario to injectIntoKeyValue test for primitive maps.
* Added object to primitive map factory to API module.
* Added of and with factory methods to object -> primitive map factories.
* Added of and with factory methods to primitive -> primitive and primitive -> object map factories.
* Added withKeyMultiValues to MutableMultimap and subtypes. 
* Added withKeyValue to MutableMultimap and subtypes.
* Added getAndPut to mutableObjectPrimitiveMap to retrieve the value associated with the key if one exists.
* Added peekAndPop methods in ImmutableStack.
* Added fused method for collect + makeString.
* Added missing implementations of toString().
* Added selectWithIndex and rejectWithIndex to Ordered Primitive Iterables.
* Implemented containsAny, containsNone, containsAnyIterable, containsNoneIterable on RichIterable.

# Optimizations
----------------------
* Optimized asParallel() for immutable sets in O(1) instead of O(n).
* Optimized some implementations of toString() that were delegating to iterators.
* Improved equals() performance for Set implementations.

# Tech Debt Reduction
---------------------
* Replaced implementation factories and dependencies with API factories where possible.
* Fixed CheckStyle configuration for NewlineAtEndOfFile so that it works across operating systems.
* Made forEach a default method on primitiveIterable.stg.
* Made noneSatisfy a default method on primitiveIterable.stg.
* Removed unnecessary implementations of toSortedList/Set/Bag/MapBy.
* Disambiguate and deprecate primitive injectInto methods on RichIterable.
* Improved code generation logic into separate goals for sources, test-sources, and resources.
* Improved PIT mutation test coverage.
* Improved overall test coverage by adding missing tests. 

# Documentation Changes
----------------------
* Updated reference guide and convert to AsciiDoc.
* Updated CONTRIBUTING.md to reflect contribution guidelines around commit sign-off.
* Updated README.md with OSS projects that use Eclipse Collections.

# Build Changes
-----------------
* Upgraded CheckStyle from 9.1 to 10.1.
* Upgraded actions/cache from 2.1.7 to 3.0.2.
* Upgraded checkstyle-configuration.xml from 1.2 schema to 1.3 schema.
* Upgraded setup-java v3.
* Upgraded actions/cache from 2.1.6 to 2.1.7.
* Upgraded actions/checkout from 2 to 3.
* Upgraded actions/upload-artifact from 2.2.4 to 3.
* Upgraded maven-plugin-api from 3.6.3 to 3.8.5.
* Upgraded maven-shade-plugin from 3.2.2 to 3.2.4.
* Upgraded maven-site-plugin from 3.9.0 to 3.11.0.
* Switched to oracle-actions to download JDK for EA builds.
* Turned on additional CheckStyle rules and IntelliJ inspections. 
* Removed FindBugs build from GitHub workflows.

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
 <version>11.1.0</version>
</dependency>

<dependency>
 <groupId>org.eclipse.collections</groupId>
 <artifactId>eclipse-collections</artifactId>
 <version>11.1.0</version>
</dependency>

<dependency>
 <groupId>org.eclipse.collections</groupId>
 <artifactId>eclipse-collections-testutils</artifactId>
 <version>11.1.0</version>
 <scope>test</scope>
</dependency>

<dependency>
 <groupId>org.eclipse.collections</groupId>
 <artifactId>eclipse-collections-forkjoin</artifactId>
 <version>11.1.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'org.eclipse.collections:eclipse-collections-api:11.1.0'
implementation 'org.eclipse.collections:eclipse-collections:11.1.0'
testImplementation 'org.eclipse.collections:eclipse-collections-testutils:11.1.0'
implementation 'org.eclipse.collections:eclipse-collections-forkjoin:11.1.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="11.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="11.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="11.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="11.1.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/11.1.0/repository
