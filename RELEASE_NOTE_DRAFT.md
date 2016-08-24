8.0.0-M2 (July 2016)
====================

This is the 8.0.0 Milestone 2 release.   

New Functionality
-----------------
* Added a factory class for MutableSortedBagMultimap to Multimaps.
* Implemented synchronized Multimaps.
* Implemented selectByOccurrences on primitive Bags.
* Implemented top/bottomOccurrences on primitive Bags.
* Changed primitive functional interfaces to extend the corresponding JDK functional interfaces.

Optimizations
-------------

* Placeholder.

Bug fixes
---------

* Placeholder.

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>8.0.0-M2</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>8.0.0-M2</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>8.0.0-M2</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>8.0.0-M2</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:8.0.0-M2'
compile 'org.eclipse.collections:eclipse-collections:8.0.0-M2'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:8.0.0-M2'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:8.0.0-M2'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="8.0.0-M2" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="8.0.0-M2" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="8.0.0-M2" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="8.0.0-M2"/>
```

