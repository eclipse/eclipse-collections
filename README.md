<!--
  ~ Copyright (c) 2018 Goldman Sachs and others.
  ~ All rights reserved. This program and the accompanying materials
  ~ are made available under the terms of the Eclipse Public License v1.0
  ~ and Eclipse Distribution License v. 1.0 which accompany this distribution.
  ~ The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
  ~ and the Eclipse Distribution License is available at
  ~ http://www.eclipse.org/org/documents/edl-v10.php.
  -->
[![][travis img]][travis]
[![][maven img]][maven]
[![][release img]][release]
[![][license-epl img]][license-epl]
[![][license-edl img]][license-edl]

![logo](artwork/eclipse-collections-logo.png) 


## [Eclipse Collections](http://www.eclipse.org/collections)
##### [中文](https://www.eclipse.org/collections/cn/index.html) | [Deutsch](https://www.eclipse.org/collections/de/index.html) | [Français](https://www.eclipse.org/collections/fr/index.html) | [日本語](https://www.eclipse.org/collections/ja/index.html) | [Português-Brasil](https://www.eclipse.org/collections/pt-br/index.html) | [Русский](https://www.eclipse.org/collections/ru/index.html)
Eclipse Collections is a collections library for Java with a rich, functional and fluent API. The library provides optimized List, Set and Map implementations and has data structures not found in the JDK including Bags, Multimaps and BiMaps. There is support for primitive versions of Lists, Sets, Bags, Stacks and Maps for all primitive types. The iteration protocol was inspired by the Smalltalk collection framework, and the collections are compatible with the Java Collection Framework types.


## Learn Eclipse Collections

* The [Eclipse Collections Katas](https://github.com/eclipse/eclipse-collections-kata), a fun way to help you learn idiomatic Eclipse Collections usage.
    * Start Here - [Pet Kata](http://eclipse.github.io/eclipse-collections-kata/pet-kata/#/) 
    * Continue Here - [Company Kata](http://eclipse.github.io/eclipse-collections-kata/company-kata/#/)
* The [Eclipse Collections Reference Guide](https://github.com/eclipse/eclipse-collections/blob/master/docs/guide.md), an overview of the extensive features available in the framework.
* The [Javadoc](https://www.eclipse.org/collections/javadoc/9.2.0/overview-summary.html) 


## Acquiring Eclipse Collections

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
```

### Gradle

```groovy
compile 'org.eclipse.collections:eclipse-collections-api:9.2.0'
compile 'org.eclipse.collections:eclipse-collections:9.2.0'
```

## Some Quick Examples

Eclipse Collections puts iteration methods directly on the container types. Here's several code examples that demonstrate the simple and flexible style of programming with Eclipse Collections.

First, we will define a simple class named *Person* to hold the first and last names of three people.

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
    
    public boolean lastNameEquals(String name)
    {
        return name.equals(this.lastName);
    }
}
```
Now we will create three instances of the *Person* class..

```java
Person person1 = new Person("Sally", "Smith");
Person person2 = new Person("Ted", "Watson");
Person person3 = new Person("Mary", "Williams");
```
##### Collect (aka map, transform)
Now we will create a *MutableList* with the three people, *collect* their names, and output them to a comma delimited String. 
```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
MutableList<String> lastNames = people.collect(person -> person.getLastName());
Assert.assertEquals("Smith, Watson, Williams", lastNames.makeString());
```
The lambda in the example above can also be replaced with a method reference.

```java
MutableList<String> lastNames = people.collect(Person::getLastName);
```
Eclipse Collections has support for both [Mutable](http://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/api/collection/MutableCollection.html) and [Immutable](http://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/api/collection/ImmutableCollection.html) collections, and the return types of methods are covariant.  While the *collect* method on a *MutableList* returned a *MutableList*, the *collect* method on an *ImmutableList* will return an *ImmutableList*.  Here we use the same [Lists](https://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/impl/factory/Lists.html) factory to create an *ImmutableList*.

```java
ImmutableList<Person> people = Lists.immutable.with(person1, person2, person3);
ImmutableList<String> lastNames = people.collect(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.makeString());
```
Eclipse Collections has a [lazy API](http://www.eclipse.org/collections/javadoc/9.2.0/org/eclipse/collections/api/LazyIterable.html) as well, which is available by calling the method *asLazy*.  The method *collect* will now return a *LazyIterable*.  The *LazyIterable* that is returned does not evaluate anything until the call to a terminal method is made.  In this case, the call to *makeString* will force the *LazyIterable* to collect the last names. 

```java
LazyIterable<String> lastNames = people.asLazy().collect(Person::getLastName);
Assert.assertEquals("Smith, Watson, Williams", lastNames.makeString());
```
##### Select / Reject (aka filter / filter not)
We can find all of the people with the last name "Smith" using the method named *select*.
```java
MutableList<Person> people = Lists.mutable.with(person1, person2, person3);
MutableList<Person> smiths = people.select(person -> person.lastNameEquals("Smith"));
Assert.assertEquals("Smith", smiths.collect(Person::getLastName).makeString());
```
If we want to use a method reference, we can use the method *selectWith*.
```java
MutableList<Person> smiths = people.selectWith(Person::lastNameEquals, "Smith");
Assert.assertEquals("Smith", smiths.collect(Person::getLastName).makeString());
```
We can find all the people who do not have a last name of "Smith" using the method named *reject*.
```java
MutableList<Person> notSmiths = people.reject(person -> person.lastNameEquals("Smith"));
Assert.assertEquals("Watson, Williams", notSmiths.collect(Person::getLastName).makeString());
```
If we want to use a method reference, we can use the method *rejectWith*.
```java
MutableList<Person> notSmiths = people.rejectWith(Person::lastNameEquals, "Smith");
Assert.assertEquals("Watson, Williams", notSmiths.collect(Person::getLastName).makeString());
```


## How to Contribute

We welcome contributions! We accept contributions via pull requests here in GitHub. Please see [How To Contribute](CONTRIBUTING.md) to get started.


## Additional information

* Project Website: http://www.eclipse.org/collections
* Eclipse PMI: https://projects.eclipse.org/projects/technology.collections
* StackOverflow: http://stackoverflow.com/questions/tagged/eclipse-collections
* Mailing lists: https://dev.eclipse.org/mailman/listinfo/collections-dev
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

[sonarqube]:https://sonarqube.com/dashboard?id=org.eclipse.collections%3Aeclipse-collections-parent
[sonarqube img]:https://sonarqube.com/api/badges/gate?key=org.eclipse.collections:eclipse-collections-parent

