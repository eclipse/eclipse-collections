9.0.0 (September 2017)
====================

This is the 9.0.0 major release.   

New Functionality
-----------------

* Implement distinctBy on ListIterable.
* Implement adapt on factory classes.
* Implement UnifiedSet.trimToSize().
* Implement UnifiedMap.trimToSize().
* Add new profiles for p2 releases.
* Implement ofAll and withAll for Int, Long, Double collection factories with primitive streams.
* Implement stream, parallelStream and spliterator on all object valued maps.
* Sign p2 repository artifacts.
* Implement HashingStrategies.nullSafeFromFunction(Function).
* Implement spliterator, stream, parallelStream and castToCollection on ImmutableCollection.
* Add an efficient default implementation of forEach.
* Implement flatCollect on lazy primitive iterables and cartesianProduct on primitive sets factory.
* Add covariant override for subList() in ListIterable hierarchy.
* Implement averageIfEmpty and medianIfEmpty on primitive iterables.
* Implement countBy and countBy with target as default methods on RichIterable.

Java 9 Compatibility Fixes
--------------------------

* For Java 9 add "Automatic-Module-Name" to manifests of JAR files.
* Add a Java 9 regression build.

Tech Debt Reduction
-------------------

* Fix Travis build, remove Java 9 build, remove Sonar badge.
* Activate Checkstyle RequireThis module.
* Fix warnings generated during Javadoc build.
* Remove Apache Felix plugin.
* Fix build to run Sonar analysis.
* Reduce scope of EMPTY_INSTANCE in ImmutableBiMapFactoryImpl.

Miscellaneous
-------------

* Add logo in reference guide.
* Add Eclipse Collections logo in README.
* Add Eclipse Collections logo.


Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>9.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>9.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>9.0.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>9.0.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:9.0.0'
compile 'org.eclipse.collections:eclipse-collections:9.0.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:9.0.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:9.0.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="9.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="9.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="9.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="9.0.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/9.0.0/repository
