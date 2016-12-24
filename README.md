Eclipse Collections
===================

[![][travis img]][travis]
[![][maven img]][maven]
[![][release img]][release]
[![][license-epl img]][license-epl]
[![][license-edl img]][license-edl]

Eclipse Collections is a collections framework for Java. It has JDK-compatible List, Set and Map implementations with a rich API, additional types not found in the JDK like Bags, Multimaps, and set of utility classes that work with any JDK compatible Collections, Arrays, Maps, or Strings. The iteration protocol was inspired by the Smalltalk collection framework.

Learn Eclipse Collections
------------------------
The [Eclipse Collections Reference Guide](https://github.com/eclipse/eclipse-collections/blob/master/docs/guide.md) is a great way to get an overview of the extensive features available in the framework.

Check out the [Eclipse Collections Kata](https://github.com/eclipse/eclipse-collections-kata), a fun way to help you learn idiomatic Eclipse Collections usage.
A [kata](https://en.wikipedia.org/wiki/Kata) is an exercise in martial arts.
A [code kata](http://codekata.com/) is an exercise in programming which helps hone your skills through practice and repetition.
This particular kata is set up as a series of unit tests which fail.
Your task is to make them pass, using Eclipse Collections.

Quick Example
-------------

Eclipse Collections puts iteration methods directly on the container types. Here's a code example that demonstrates the simple style of programming with Eclipse Collections.

```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
MutableList<String> lastNames = people.collect(Person::getLastName);
System.out.println("Comma separated last names: " + lastNames.makeString());
```
The method reference in the example above can also be replaced with a lambda:

```java
MutableList<String> lastNames = people.collect(person -> person.getLastName());
```
Eclipse Collections has support for both [Mutable](http://www.eclipse.org/collections/javadoc/8.0.0/org/eclipse/collections/api/collection/MutableCollection.html) and [Immutable](http://www.eclipse.org/collections/javadoc/8.0.0/org/eclipse/collections/api/collection/ImmutableCollection.html) collections, and the return types of methods are covariant.

```java
ImmutableList<Person> people = Lists.immutable.with(person1, person2, person3);
ImmutableList<String> lastNames = people.collect(Person::getLastName);
System.out.println("Comma separated last names: " + lastNames.makeString());
```
Eclipse Collections has a [lazy API](http://www.eclipse.org/collections/javadoc/8.0.0/org/eclipse/collections/api/LazyIterable.html) as well, which is available by calling the method asLazy().

```java
ImmutableList<Person> people = Lists.immutable.with(person1, person2, person3);
LazyIterable<String> lastNames = people.asLazy().collect(Person::getLastName);
System.out.println("Comma separated last names: " + lastNames.makeString());
```
The MutableCollections in Eclipse Collections also have the Stream API available in Java, since they extend their corresponding JDK types.

```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
Stream<String> lastNames = people.stream().map(Person::getLastName);
System.out.println("Comma separated last names: " + lastNames.collect(Collectors.joining(", "));
```
Eclipse Collections 8.0 also introduced a new set of Collectors in the class [Collectors2](http://www.eclipse.org/collections/javadoc/8.0.0/org/eclipse/collections/impl/collector/Collectors2.html).

```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
Stream<String> lastNames = people.stream().map(Person::getLastName);
System.out.println("Comma separated last names: " + lastNames.collect(Collectors2.makeString());
```



Why Eclipse Collections?
------------------------

* Improves readability and reduces duplication of iteration code (enforces DRY/OAOO)
* Implements several, high-level iteration patterns (select, reject, collect, inject into, etc.) on "humane" container interfaces which are extensions of the JDK interfaces
* Provides a consistent mechanism for iterating over Collections, Arrays, Maps, and Strings
* Provides replacements for ArrayList, HashSet, and HashMap optimized for performance and memory usage
* Performs more "behind-the-scene" optimizations in utility classes
* Encapsulates a lot of the structural complexity of parallel iteration and lazy evaluation
* Adds new containers including Bag, Interval, Multimap, BiMap, and immutable versions of all types
* Has been under active development since 2005 and is a mature library

License
-------

Eclipse Collections is open sourced under the Eclipse Public License v1.0 and the Eclipse Distribution License v1.0.

How to Contribute
-----------------

We welcome contributions!

We accept contributions via pull requests here in GitHub. Please see [How To Contribute](CONTRIBUTING.md) to get started.

Project Roadmap
---------------

https://github.com/eclipse/eclipse-collections/wiki/Roadmap

Acquiring Eclipse Collections
-----------------------------

### Maven
```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>8.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>8.0.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>8.0.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>8.0.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:8.0.0'
compile 'org.eclipse.collections:eclipse-collections:8.0.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:8.0.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:8.0.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="8.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="8.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="8.0.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="8.0.0"/>
```

Additional information
----------------------

* Project Website: http://www.eclipse.org/collections
* Issues: https://github.com/eclipse/eclipse-collections/issues
* Wiki: https://github.com/eclipse/eclipse-collections/wiki
* StackOverflow: http://stackoverflow.com/questions/tagged/eclipse-collections
* Mailing lists: https://dev.eclipse.org/mailman/listinfo/collections-dev
* Eclipse PMI: https://projects.eclipse.org/projects/technology.collections
* Forum: https://www.eclipse.org/forums/index.php?t=thread&frm_id=329

[travis]:https://travis-ci.org/eclipse/eclipse-collections
[travis img]:https://travis-ci.org/eclipse/eclipse-collections.svg?branch=master

[maven]:http://search.maven.org/#search|gav|1|g:"org.eclipse.collections"%20AND%20a:"eclipse-collections"
[maven img]:https://maven-badges.herokuapp.com/maven-central/org.eclipse.collections/eclipse-collections/badge.svg

[release]:https://github.com/eclipse/eclipse-collections/releases
[release img]:https://img.shields.io/github/release/eclipse/eclipse-collections.svg

[license-epl]:LICENSE-EPL-1.0.txt
[license-epl img]:https://img.shields.io/badge/License-EPL-blue.svg

[license-edl]:LICENSE-EDL-1.0.txt
[license-edl img]:https://img.shields.io/badge/License-EDL-blue.svg

