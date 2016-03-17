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

Check out the [Eclipse Collections Kata](https://github.com/eclipse/eclipse-collections-kata), a fun way to help you learn idiomatic Eclipse Collections usage.
A [kata](https://en.wikipedia.org/wiki/Kata) is an exercise in martial arts.
A [code kata](http://codekata.com/) is an exercise in programming which helps hone your skills through practice and repetition.
This particular kata is set up as a series of unit tests which fail.
Your task is to make them pass, using Eclipse Collections.

Quick Example
-------------

Eclipse Collections puts iteration methods on the container types. Lambdas are simulated using anonymous inner classes. Here's a code example that demonstrates the usual style of programming with Eclipse Collections.

```java
MutableList<Person> people = FastList.newListWith(person1, person2, person3);
MutableList<String> sortedLastNames = people.collect(Person.TO_LAST_NAME).sortThis();
System.out.println("Comma separated, sorted last names: " + sortedLastNames.makeString());
```

Person.TO_LAST_NAME is defined as a constant Function in the Person class.

```java
public static final Function<Person, String> TO_LAST_NAME = new Function<Person, String>()
{
    public String valueOf(Person person)
    {
        return person.lastName;
    }
};

```
In Java 8, the Function can be replaced with a lambda:

```java
MutableList<String> sortedLastNames = people.collect(person -> person.getLastName()).sortThis();
```

Or, a method reference:

```java
MutableList<String> sortedLastNames = people.collect(Person::getLastName).sortThis();
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
  <version>7.0.2</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>7.0.2</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>7.0.2</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>7.0.2</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:7.0.2'
compile 'org.eclipse.collections:eclipse-collections:7.0.2'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:7.0.2'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:7.0.2'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="7.0.2" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="7.0.2" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="7.0.2" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="7.0.2"/>
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

