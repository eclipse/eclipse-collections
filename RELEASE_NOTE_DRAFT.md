9.1.0 (September 2017)
====================

This is the 9.1.0 minor release.

New Functionality
-----------------

* Implemented a multi reader lists factory - Lists.multiReader

Tech Debt Reduction
-------------------

Breaking Change
---------------

Miscellaneous
-------------

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>9.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>9.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>9.1.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>9.1.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:9.1.0'
compile 'org.eclipse.collections:eclipse-collections:9.1.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:9.1.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:9.1.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="9.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="9.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="9.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="9.1.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/9.1.0/repository
