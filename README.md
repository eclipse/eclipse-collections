![logo]
===================

[![][travis img]][travis]
[![][maven img]][maven]
[![][release img]][release]
[![][license-epl img]][license-epl]
[![][license-edl img]][license-edl]

Eclipse Collections is a collections framework for Java. It has optimized List, Set and Map implementations with a rich and fluent API.  The library provides additional data structures not found in the JDK like Bags, Multimaps and BiMaps.  The framework also provides primitive versions of Lists, Sets, Bags, Stacks and Maps with a rich and fluent API.  There is support for both Mutable and Immutable versions of all containers in the library.  The iteration protocol was inspired by the Smalltalk collection framework, and the collections are compatible with the Java Collection Framework types.

Eclipse Collections has been presented at [various conferences and meetups](https://github.com/eclipse/eclipse-collections/wiki/Conference-talks-and-meetups) including JavaOne, Java Day Tokyo, EclipseCon NA & Europe, GOTO Chicago, ECOOP (Curry On) and QCon New York.

The framework is actively being developed, with a growing number of contributors, at the Eclipse Foundation.  The current roadmap for the framework is available [here](https://github.com/eclipse/eclipse-collections/wiki/Roadmap).

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

Eclipse Collections puts iteration methods directly on the container types. Here's several code examples that demonstrate the simple and flexible style of programming with Eclipse Collections.

First, we will define a simple class named Person to hold the first and last names of three people.

```java
public class Person
{
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }
}
```
Now we will setup three instances of the Person class in the person1, person2 and person3 variables.

```java
Person person1 = new Person("Sally", "Smith");
Person person2 = new Person("Ted", "Watson");
Person person3 = new Person("Mary", "Williams");
```
Now that the three people are instantiated, we can store them in a MutableList using the Lists factory class.  Then we will collect the last names of each person to a MutableList of String.  As a final step we will assert the expected value of the last names as a comma separated string should be equal to "Smith, Watson, Williams". 

```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
MutableList<String> lastNames = people.collect(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.makeString());
```
The method reference in the example above can also be replaced with a lambda:

```java
MutableList<String> lastNames = people.collect(person -> person.getLastName());
```
Eclipse Collections has support for both [Mutable](http://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/api/collection/MutableCollection.html) and [Immutable](http://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/api/collection/ImmutableCollection.html) collections, and the return types of methods are covariant.  While the collect method on a MutableList returned a MutableList, the collect method on an ImmutableList will return an ImmutableList.  Here we use the same Lists factory to create an ImmutableList.

```java
ImmutableList<Person> people = Lists.immutable.with(person1, person2, person3);
ImmutableList<String> lastNames = people.collect(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.makeString());
```
Eclipse Collections has a [lazy API](http://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/api/LazyIterable.html) as well, which is available by calling the method asLazy().  The method collect will now return a LazyIterable.  The LazyIterable that is returned does not evaluate anything until the call to a terminal method is made.  In this case, the call to makeString() will force the LazyIterable to collect the last names. 

```java
ImmutableList<Person> people = Lists.immutable.with(person1, person2, person3);
LazyIterable<String> lastNames = people.asLazy().collect(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.makeString());
```
The MutableCollections in Eclipse Collections also have the Stream API available in Java, since they extend their corresponding JDK types.  In this case, a MutableList extends java.util.List.  We use the stream() and map() methods to return a Stream of String.  Then we call the terminal method collect on the Stream with the Collectors.joining() as a parameter to convert the MutableList of Person to their comma separated last names.

```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
Stream<String> lastNames = people.stream().map(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.collect(Collectors.joining(", "));
```
Eclipse Collections 8.0 also introduced a new set of Collectors in the class [Collectors2](http://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/impl/collector/Collectors2.html).  The Collectors2.makeString() is equivalent to Collectors2.joining(", "), but does not require the object in the Stream to be a String.

```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
Stream<String> lastNames = people.stream().map(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.collect(Collectors2.makeString());
```
There are also a set of Adapter classes which can be used to add the fluent Eclipse Collections protocols on top of JDK Collection types.

```java
List<Person> people = Arrays.asList(person1, person2, person3);
MutableList<String> lastNames = ListAdapter.adapt(people).collect(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.makeString());
```

Why Eclipse Collections?
------------------------

* Improves readability and reduces duplication of iteration code (enforces DRY/OAOO principles)
* Implements many high-level iteration patterns (select, reject, collect, inject into, etc.) on "humane" container interfaces which are extensions of the JDK interfaces
* Provides a consistent mechanism for iterating over Collections, Arrays, Maps, and Strings
* Provides replacements for ArrayList, HashSet, and HashMap optimized for performance and memory usage
* Adds new containers including Bag, Interval, Multimap, BiMap, and immutable versions of all types
* Adds primitive containers for Lists, Sets, Bags, Stacks and Maps for all primitive Java types (boolean, byte, char, short, int, float, long, double)
* Supports Mutable, Immutable, Synchronized, Unmodifiable and MultiReader Collections
* Supports eager evaluation with APIs directly on containers and lazy evaluation via a method called asLazy()
* Encapsulates a lot of the structural complexity of parallel iteration in a parallel API and parallel utility classes 
* Provides adapters and optimized utility classes for iterating over JDK Collection types with a fluent and friendly API 
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
  <version>9.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>9.2.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-testutils</artifactId>
  <version>9.2.0</version>
  <scope>test</scope>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-forkjoin</artifactId>
  <version>9.2.0</version>
</dependency>
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:9.2.0'
compile 'org.eclipse.collections:eclipse-collections:9.2.0'
testCompile 'org.eclipse.collections:eclipse-collections-testutils:9.2.0'
compile 'org.eclipse.collections:eclipse-collections-forkjoin:9.2.0'
```

### Ivy

```xml
<dependency org="org.eclipse.collections" name="eclipse-collections-api" rev="9.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections" rev="9.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-testutils" rev="9.2.0" />
<dependency org="org.eclipse.collections" name="eclipse-collections-forkjoin" rev="9.2.0"/>
```

### OSGi Bundle

Eclipse software repository location: http://download.eclipse.org/collections/9.2.0/repository

Additional information
----------------------

* Project Website: http://www.eclipse.org/collections
* Issues: https://github.com/eclipse/eclipse-collections/issues
* Wiki: https://github.com/eclipse/eclipse-collections/wiki
* StackOverflow: http://stackoverflow.com/questions/tagged/eclipse-collections
* Mailing lists: https://dev.eclipse.org/mailman/listinfo/collections-dev
* Eclipse PMI: https://projects.eclipse.org/projects/technology.collections
* Forum: https://www.eclipse.org/forums/index.php?t=thread&frm_id=329

[logo]:https://github.com/eclipse/eclipse-collections/blob/master/artwork/eclipse-collections-logo.png

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

[sonarqube]:https://sonarqube.com/dashboard?id=org.eclipse.collections%3Aeclipse-collections-parent
[sonarqube img]:https://sonarqube.com/api/badges/gate?key=org.eclipse.collections:eclipse-collections-parent

