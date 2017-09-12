9.0.0 (September 2017)
====================

This is the 9.0.0 major release.   

New Functionality
-----------------

* Implemented flipUniqueValues() on primitive maps.
* Implemented tap on primitive iterables.
* Implemented Implement ReversibleIterable.reverseForEachWithIndex().
* Implemented distinctBy on ListIterable.
* Implemented adapt on factory classes.
* Implemented UnifiedSet.trimToSize().
* Implemented UnifiedMap.trimToSize().
* Implemented ofAll and withAll for Int, Long, Double collection factories with primitive streams.
* Implemented stream, parallelStream and spliterator on all object valued maps.
* Implemented HashingStrategies.nullSafeFromFunction(Function).
* Implemented spliterator, stream, parallelStream and castToCollection on ImmutableCollection.
* Added an efficient default implementation of forEach.
* Implemented flatCollect on lazy primitive iterables and cartesianProduct on primitive sets factory.
* Added covariant override for subList() in ListIterable hierarchy.
* Implemented averageIfEmpty and medianIfEmpty on primitive iterables.
* Implemented countBy and countBy with target as default methods on RichIterable.
* For Java 9 added "Automatic-Module-Name" to manifests of JAR files.

Tech Debt Reduction
-------------------

* Activated Checkstyle RequireThis module.
* Fixed warnings generated during Javadoc build.

Breaking Change
---------------

* Removed Apache Felix plugin.
* Deprecated or hide collection factory constructors. 
* Reduced scope of EMPTY_INSTANCE in ImmutableBiMapFactoryImpl.

Miscellaneous
-------------

* Added logo in reference guide.
* Added Eclipse Collections logo in README.
* Added Eclipse Collections logo.


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
