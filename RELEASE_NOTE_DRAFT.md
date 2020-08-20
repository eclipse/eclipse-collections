10.4.0
====================

This is the 10.4.0 minor release. This release includes mandatory fixes to make the library compatible with JDK-15.

# Changes
-----------------
* Added CharAdapter.isEmpty(), CodePointAdapter.isEmpty(), CodePointList.isEmpty(), as JDK-15 introduced CharSequence.isEmpty().  
* Fixed Javadoc errors for MapIterable, Multimaps, ImmutablePrimitiveBagFactory, MutablePrimitiveBagFactory, ImmutablePrimitiveListFactory, MutablePrimitiveListFactory, ImmutablePrimitiveSetFactory, MutablePrimitiveSetFactory, ImmutablePrimitiveStackFactory, MutablePrimitiveStackFactory. 

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>10.4.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>10.4.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>10.4.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>10.4.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'org.eclipse.collections:eclipse-collections-api:10.4.0'
implementation 'org.eclipse.collections:eclipse-collections:10.4.0'
testImplementation 'org.eclipse.collections:eclipse-collections-testutils:10.4.0'
implementation 'org.eclipse.collections:eclipse-collections-forkjoin:10.4.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="10.4.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="10.4.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="10.4.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="10.4.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/10.4.0/repository
