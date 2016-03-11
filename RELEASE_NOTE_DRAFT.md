7.1.0 (April 2016)
==================

The primary theme of the 7.1.0 release is community engagement.

Community Engagement
--------------------

* Migrated the GS Collections Kata to the [Eclipse Collections Kata](https://github.com/eclipse/eclipse-collections-kata).
* Implemented the [Eclipse Collections converter](https://github.com/eclipse/gsc-ec-converter) which can replace usages of GS Collection with Eclipse Collections in your codebases.
* Updated Eclipse project files.

New Functionality
-----------------

* Implemented Iterate.toMultimap().
* Implemented Iterate.groupByAndCollect().

Optimizations
-------------

* Simplified and optimized a few methods in ImmutableSingletonBag.
* Fixed performance problem in ArrayStack.minBy().
* Optimized UnifiedMap.getFirst() to not delegate to an iterator.
* Optimized AbstractBag for all MutableBagIterable targets, not just MutableBag targets.
* Optimized iteration patterns in a few LazyIterables to not delegate to an Iterator.
* Extracted size variables to make iteration patterns in RandomAccessListIterate consistent.

Test Improvements
-----------------

* Implemented Verify.assertListMultimapsEqual(), Verify.assertSetMultimapsEqual(), Verify.assertBagMultimapsEqual(), Verify.assertSortedSetMultimapsEqual(), Verify.assertSortedBagMultimapsEqual().
* Added new assertions Verify.assertNotSerializable() and Verify.assertNotInstanceOf().
* Fixed IterableTestCase.checkNotSame() to factor in empty sorted immutable collections having different instances due to construction with a comparator.
* Added test coverage for additional classes and methods in the java 8 test suite.
* Replaced forkMode with forkCount for surefire plugin.

Bug fixes
---------

* Fixed bug in ArrayIterate.chunk().

Documentation
-------------

* Added instructions for commit sign-off to contribution guide.
* Fixed syntax of examples in Javadoc.
* Added badge for GitHub release notes to the README.

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

