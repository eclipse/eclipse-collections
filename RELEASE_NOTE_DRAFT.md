8.1.0 (March 2017)
====================

This is the 8.1.0 minor release.   

New Functionality
-----------------

* Implement BigDecimalSummaryStatistics and BigIntegerSummaryStatistics.
* Implement SummaryStatistics and Collectors2.summarizing.
* Integrate JaCoCo for test coverage.
* Implement flatCollect on Collectors2.
* Modify PersonAndPetKataTest.getAgeStatisticsOfPets to use summaryStatistics.
* Update reference guide.
* Add Abstract primitive Stacks.
* Add the Eclipse Collections reference guide.

Optimizations
-------------

* Change collect and collectWith with target collections on InternalArrayIterate to use ensureCapacity for FastLists and ArrayLists.

Bug fixes
---------

* Remove JMH tests and generator codes from Javadoc output.
* Update links to point to the Eclipse Collections Kata instead of the GS Collections Kata.
* Fix addAll() method in CompositeFastList to return false on isEmpty().

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

