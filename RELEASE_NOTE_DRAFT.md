8.1.0 (March 2017)
====================

This is the 8.1.0 minor release.   

New Functionality
-----------------

* Implemented primitive case procedures.
* Implemented primitive case functions.
* Implemented select and reject with targets on primitive iterables.
* Implemented collect and flatCollect with target collections on primitiveIterable.stg.
* Added collect<Primitives> with target collections as default methods in primitiveIterable.stg.
* Made SummaryStatistics Serializable.
* Implemented summingBigDecimal, sumByBigDecimal, summingBigInteger, sumByBigInteger on Collectors2. 
* Implemented BigDecimalSummaryStatistics and BigIntegerSummaryStatistics.
* Implemented SummaryStatistics and Collectors2.summarizing.
* Integrated JaCoCo for test coverage.
* Implemented flatCollect on Collectors2.
* Modified PersonAndPetKataTest.getAgeStatisticsOfPets to use summaryStatistics.
* Updated reference guide.
* Added Abstract primitive Stacks.
* Added the Eclipse Collections reference guide.

Optimizations
-------------

* Changed collect and collectWith with target collections on InternalArrayIterate to use ensureCapacity for FastLists and ArrayLists.

Bug fixes
---------

* Removed JMH tests and generator codes from Javadoc output.
* Updated links to point to the Eclipse Collections Kata instead of the GS Collections Kata.
* Fixed addAll() method in CompositeFastList to return false on isEmpty().

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>8.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>8.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>8.1.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>8.1.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:8.1.0'
compile 'org.eclipse.collections:eclipse-collections:8.1.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:8.1.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:8.1.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="8.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="8.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="8.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="8.1.0"/>
```

