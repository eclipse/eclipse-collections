
7.1.0 (April 2016)
================

Primary theme of Eclipse Collections 7.1.0 release is the community engagement.

Community engagement
--------------------

* Migrated the GS Collections Kata to the [Eclipse Collections Kata](https://github.com/eclipse/eclipse-collections-kata).
* Implemented the [Eclipse Collections converter](https://github.com/eclipse/gsc-ec-converter), with provideing instruction on migration from GS Collections to Eclipse Collections.

New Functionalities
------------------

* Implemented Verify.assertListMultimapsEqual(), Verify.assertSetMultimapsEqual(), Verify.assertBagMultimapsEqual(), Verify.assertSortedSetMultimapsEqual(), Verify.assertSortedBagMultimapsEqual().
* Implemented Verify.assertNotInstanceOf. 
* Implemented Iterate.toMultimap().
* Implemented Iterate.groupByAndCollect().

Optimizations
-------------

* Simplified and optimize a few methods in ImmutableSingletonBag.
* Fixed performance problem in ArrayStack.minBy().
* Optimized UnifiedMap.getFirst() to not delegate to an iterator.
* Optimized AbstractBag for all MutableBagIterable targets, not just MutableBag targets.
* Optimized iteration patterns in a few LazyIterables to not delegate to an Iterator. 
* Extracted size variables to make iteration patterns in RandomAccessListIterate consistent.


Test Improvements
-----------------

* Added new assertions and test coverage.
* Fixed IterableTestCase.checkNotSame to factor in empty sorted immutable collections having different instances due to construction with a comparator.
* Added a new assertion Verify.assertNotSerializable().
* Added test coverage for UnmodifiableSortedBag and extract UnmodifiableBagIterableTestCase.
* Added test coverage for additional classes in the java 8 test suite.
* Added test coverage for smaller collections: forEach(), contains(), anySatisfy(), allSatisfy(), noneSatisfy().
* Added assertions that methods which take target collections return the same instance.
* Added additional sanity checks within assertion methods in IterableTestCase.
* Consistently use IterableTestCase.addAllTo() in implementations of IterableTestCase.newWith() to ensure usage of the correct parent interface, RichIterableTestCase or RichIterableWithDuplicatesTestCase.
* Added test coverage for removing from the sublist of a sublist.
* Added additional test coverage for sets with hash collisions.

Bug fixes
---------

* Fixed UnifiedSet.ChainedBucket.removeLongChain() method to handle many collisions in one bucket.
* Fixed memory leak in HashBiMap.
* Fixed incorrect code path in key collision handling and keyset iterator based remove operation in primitive Maps with Hashing Strategy.
* Fixed bug in ArrayIterate.chunk(). 

Documentation, build and configuration
-------------------------------

* Added instructions for commit sign-off to contribution guide. 
* Fixed syntax of examples in Javadoc. 
* Added badge for GitHub release notes to the README.
* Added periodic logging to the parallel map acceptance tests to prevent timeouts.
* Set up a Travis job to compile, but not run, jmh-tests and performance-tests.
* Moved the slowest Travis job to run first.
* Replaced forkMode with forkCount for surefire plugin.

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>7.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>7.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>7.1.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>7.1.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:7.1.0'
compile 'org.eclipse.collections:eclipse-collections:7.1.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:7.1.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:7.1.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="7.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="7.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="7.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="7.1.0"/>
```

