8.1.0 (July 2016)
====================

This is the 8.1.0 minor release.   

New Functionality
-----------------

* Added collect<primitive>() methods to all implementations of the <primitive>List interface, i.e., <primitive>ArrayList, Synchronized<primitive>List and Unmodifiable<primitive>List.

For example, IntArrayList now has:
    * collectInt()
    * collectFloat()
    * collectDouble()
    * collectLong()
    * collectShort()
    * collectByte()
    * collectChar()
    * collectBoolean()

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

