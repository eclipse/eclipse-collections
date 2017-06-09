8.2.0 (June 2017)
====================

This is the 8.2.0 minor release.   

New Functionality
-----------------

* Implemented OrderedIterable.getFirstOptional() and OrderedIterable.getLastOptional().
* Implemented RichIterable.minOptional() and RichIterable.minByOptional().
* Implemented RichIterable.maxOptional() and RichIterable.maxByOptional().
* Provided a p2 repository with new OSGi bundle.

Optimizations
-------------

* Optimized ImmutableTreeSet constructor to not create a new TreeSortedSet.
* Optimized constructors of ImmutableTreeSet which take an array as input.
* Optimized minOptional(), minByOptional(), maxOptional() and maxByOptional() on LazyIterable.
* Optimized size() on CompositeIterable.

Java 9 Compatibility Fixes
--------------------------

* Added workaround for reflection in ArrayListIterate.
* Deprecated SerializableDoubleSummaryStatistics, SerializableIntSummaryStatistics,SerializableLongSummaryStatistics, SummaryStatistics.
* Fixed compilation errors for JDK 9.
* Changed 'throws' tests to support Java 8 and Java 9.
* Added workaround for reflection in Verify#assertShallowClone().
* Deprecated Verify#assertShallowClone().

Tech Debt Reduction
-------------------

* Deprecated EMPTY_INSTANCE in ImmutableBiMapFactoryImpl.
* Deprecated EMPTY Multimap instances in org.eclipse.collections.impl.factory.Multimaps.
* Pulled up implementation of detectIfNone(), toSortedList() from AbstractRichIterable to RichIterable.
* Replaced the type specification with the diamond operator ("<>") in primitive collections.
* Removed duplicate and unnecessary imports.
* Annotated interfaces with the @FunctionalInterface annotation.
* Added missing @Override annotations.
* Fixed angle brackets in Javadoc.
* Added unit tests for PairPredicate and MapEntryPredicate.

Miscellaneous
-------------
* Enabled SonarQube analysis.
* Upgraded versions of maven-jar-plugin, maven-source-plugin, maven-plugin-plugin, maven-compiler, maven-javadoc plugin, checkstyle, JaCoCo.
* Upgraded versions of Guava, HPPC, JMH, Logback, SLF4J.


Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>8.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>8.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>8.2.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>8.2.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:8.2.0'
compile 'org.eclipse.collections:eclipse-collections:8.2.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:8.2.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:8.2.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="8.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="8.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="8.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="8.2.0"/>
```

