10.2.0
====================

This is the 10.2.0 minor release.
This release is to provide new features requested by the community, bug fix for edge case issues, and reduce tech debt.

# New Functionality
-----------------
* Exposed the `allocateTable` method as `protected` in Primitive Maps and Primitive Sets.

# Bug Fixes
-----------------
* Fixed size edge case issues in Interval and IntInterval.

# Tech Debt Reduction
---------------------
* Optimized `removeIf` on UnifiedMap.
* Implemented `removeIf` as a `default` method on MutableMapIterable.
* Replaced usages of `Comparators.nullSafeEquals()` with `Objects.equals()`.

# Build Changes
-----------------
* Moved some maven configuration from .travis.yml to jvm.config.
* Changed Maven plugin repository to use https.

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>10.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>10.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>10.2.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>10.2.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:10.2.0'
compile 'org.eclipse.collections:eclipse-collections:10.2.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:10.2.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:10.2.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="10.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="10.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="10.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="10.2.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/10.2.0/repository
