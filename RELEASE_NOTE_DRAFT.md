10.1.0
====================

This is the 10.1.0 minor release.
Central theme of this release is to fix issues identified for SimRel repository. There are also few new features, enhancements and bug fixes.

# New Functionality
-----------------
* Implemented RichIterable.groupByAndCollect().
* Implemented NoopProcedure.

# Bug Fixes
-----------------
* Fixed IntInterval.fromToBy() for same values of from and to with a negative step.
* Fixed IntInterval.injectInto() for same values of from and to with a negative step.

# Tech Debt Reduction
---------------------
* Added examples for creating and modifying Immutable Collections in documentation.
* Changed internal variable name in Multimaps class for ImmutableSortedBagFactory
* Fixed generated Eclipse features for p2 repository to ensure correct EPLv1 license is downloaded.
* Fixed generated Eclipse features for p2 repository to ensure correct signatures on artifacts.
* Fixed repository path for Eclipse features.
* Optimize removeIf() for UnifiedMap.

# Build Changes
-----------------
* Fixed configuration to gpg sign plugin to work on new CI infrastructure.
* Improved .gitignore for maven to reduce noise.
* Modified build to use maven's --no-transfer-progress option to reduce noise.
* Upgraded maven plugins.

Acquiring Eclipse Collections
-----------------------------

### Maven

```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>10.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>10.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>10.1.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>10.1.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:10.1.0'
compile 'org.eclipse.collections:eclipse-collections:10.1.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:10.1.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:10.1.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="10.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="10.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="10.1.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="10.1.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/10.1.0/repository
