<!--
  ~ Copyright (c) 2022 Goldman Sachs and others.
  ~ All rights reserved. This program and the accompanying materials
  ~ are made available under the terms of the Eclipse Public License v1.0
  ~ and Eclipse Distribution License v. 1.0 which accompany this distribution.
  ~ The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
  ~ and the Eclipse Distribution License is available at
  ~ http://www.eclipse.org/org/documents/edl-v10.php.
  -->
[![][maven img]][maven]
[![][release img]][release]
[![][javadoc api img]][javadoc api]
[![][javadoc impl img]][javadoc impl]

[![][license-epl img]][license-epl]
[![][license-edl img]][license-edl]
[![][snyk-badge img]][snyk-badge]

[![][actions unit-tests img]][actions unit-tests]
[![][actions acceptance-tests img]][actions acceptance-tests]
[![][actions performance-tests img]][actions performance-tests]
[![][actions checkstyle img]][actions checkstyle]
[![][actions javadoc img]][actions javadoc]

<a href="https://www.eclipse.org/collections/"><img src="https://github.com/eclipse/eclipse-collections/blob/master/artwork/eclipse-collections-logo.png" height="50%" width="50%"></a>

#### [English](https://www.eclipse.org/collections/) | [中文](https://www.eclipse.org/collections/cn/index.html) | [Deutsch](https://www.eclipse.org/collections/de/index.html) | [Español](https://www.eclipse.org/collections/es/index.html) | [Ελληνικά](https://www.eclipse.org/collections/el/index.html) | [Français](https://www.eclipse.org/collections/fr/index.html) | [日本語](https://www.eclipse.org/collections/ja/index.html) | [Norsk (bokmål)](https://www.eclipse.org/collections/no-nb/index.html) | [Português-Brasil](https://www.eclipse.org/collections/pt-br/index.html) | [Русский](https://www.eclipse.org/collections/ru/index.html) | [हिंदी](https://www.eclipse.org/collections/hi/index.html)
Eclipse Collections is a comprehensive collections library for Java. The library enables productivity and performance by delivering an expressive and efficient set of APIs and types. The iteration protocol was inspired by the Smalltalk collection framework, and the collections are compatible with the Java Collection Framework types.

Eclipse Collections is compatible with Java 8+. Eclipse Collections is a part of the OpenJDK [Quality Outreach](https://wiki.openjdk.java.net/display/quality/Quality+Outreach) program, and it is validated for different versions of the OpenJDK.

## Why Eclipse Collections?

* Productivity
    * [Rich][RichIterable], functional, and fluent APIs with great symmetry
    * [`List`][ListIterable], [`Set`][SetIterable], [`Bag`][Bag], [`Stack`][StackIterable], [`Map`][MapIterable], [`Multimap`][Multimap], [`BiMap`][BiMap], [`Interval`][Interval] Types
    * [Readable][RichIterable], [`Mutable`][MutableCollection], and [`Immutable`][ImmutableCollection] Types
    * Mutable and Immutable Collection [Factories][Factories]
    * [Adapters][Adapters] and [Utility][Utilities] classes for JCF Types
* Performance
    * Memory Efficient Containers
    * Optimized Eager, [`Lazy`][LazyIterable] and [`Parallel`][ParallelIterable] APIs
    * [Primitive][PrimitiveIterable] Collections for all primitive types

## Learn Eclipse Collections

* [Some Quick Code Examples](./README_EXAMPLES.md)
* [Eclipse Collections Katas](https://github.com/eclipse/eclipse-collections-kata), a fun way to help you learn idiomatic Eclipse Collections usage.
    * Start Here - [Pet Kata](http://eclipse.github.io/eclipse-collections-kata/pet-kata/#/)
    * Continue Here - [Company Kata](http://eclipse.github.io/eclipse-collections-kata/company-kata/#/)
* [Eclipse Collections Reference Guide](https://github.com/eclipse/eclipse-collections/blob/master/docs/0-RefGuide.adoc) and [Javadoc](https://www.eclipse.org/collections/javadoc/11.1.0/overview-summary.html)
* [Serializing Eclipse Collections with Jackson](./docs/jackson.md)
* [Articles](https://github.com/eclipse/eclipse-collections/wiki/Articles) and [Blogs](https://medium.com/tag/eclipse-collections/latest)
* Some OSS projects that use Eclipse Collections
  * [Neo4J](https://github.com/neo4j/neo4j), [FINOS Legend](https://github.com/finos/legend-pure), [Reladomo](https://github.com/goldmansachs/reladomo), [Liftwizard](https://github.com/motlin/liftwizard), [Exchange Core](https://github.com/mzheravin/exchange-core), [Dataframe EC](https://github.com/vmzakharov/dataframe-ec), [MapDB](https://github.com/jankotek/mapdb), [Code Browser](https://github.com/yawkat/code-browser), [Obevo](https://github.com/goldmansachs/obevo), [BNY Mellon Code Katas](https://github.com/BNYMellon/CodeKatas), [Eclipse Nebula NatTable](https://www.eclipse.org/nattable/index.php), [Eclipse VIATRA](https://github.com/viatra/org.eclipse.viatra), [Jackson Datatypes Collections](https://github.com/FasterXML/jackson-datatypes-collections)
  * If you work on an open source project that uses Eclipse Collections, let us know!

## Eclipse Collections and JDK Compatibility Matrix

|EC     | 7.x.x | 8.x.x | 9.x.x |[10.0.0][10-0-Release] |[10.1.0][10-1-Release] |[10.2.0][10-2-Release] |[10.3.0][10-3-Release] |[10.4.0][10-4-Release] |[11.0.0][11-0-Release] | [11.1.0][11-1-Release] |
|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|
|JDK 5 - 7  |&check;|       |       |       |       |       |       |       | ||
|JDK 8  |&check;|&check;|&check;|&check;|&check;|&check;|&check;|&check;| &check;|&check;|
|JDK 9 - 14  |       |       |&check;|&check;|&check;|&check;|&check;|&check;| &check;|&check;|
|JDK 15 - 18|       |       |       |       |       |       |       |&check;|&check;|&check;|

## Acquiring Eclipse Collections

### Maven
```xml
<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections-api</artifactId>
  <version>11.1.0</version>
</dependency>

<dependency>
  <groupId>org.eclipse.collections</groupId>
  <artifactId>eclipse-collections</artifactId>
  <version>11.1.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'org.eclipse.collections:eclipse-collections-api:11.1.0'
implementation 'org.eclipse.collections:eclipse-collections:11.1.0'
```

### OSGi Bundle
Eclipse software repository location: http://download.eclipse.org/collections/11.1.0/repository


## How to Contribute

We welcome contributions! We accept contributions via pull requests here in GitHub. Please see [How To Contribute](CONTRIBUTING.md) to get started.


## Additional information

* Project Website: http://www.eclipse.org/collections
* Eclipse PMI: https://projects.eclipse.org/projects/technology.collections
* StackOverflow: http://stackoverflow.com/questions/tagged/eclipse-collections
* Mailing lists: https://dev.eclipse.org/mailman/listinfo/collections-dev
* Forum: https://www.eclipse.org/forums/index.php?t=thread&frm_id=329
* Working with GitHub: https://github.com/eclipse/eclipse-collections/wiki/Working-with-GitHub

[actions acceptance-tests]:https://github.com/eclipse/eclipse-collections/actions?query=workflow%3A%22Acceptance+Tests%22
[actions acceptance-tests img]:https://github.com/eclipse/eclipse-collections/workflows/Acceptance%20Tests/badge.svg?branch=master

[actions unit-tests]:https://github.com/eclipse/eclipse-collections/actions?query=workflow%3A%22Unit+tests%22
[actions unit-tests img]:https://github.com/eclipse/eclipse-collections/workflows/Unit%20tests/badge.svg?branch=master

[actions performance-tests]:https://github.com/eclipse/eclipse-collections/actions?query=workflow%3A%22Performance+Tests%22
[actions performance-tests img]:https://github.com/eclipse/eclipse-collections/workflows/Performance%20Tests/badge.svg?branch=master

[actions checkstyle]:https://github.com/eclipse/eclipse-collections/actions?query=workflow%3A%22Checkstyle%22
[actions checkstyle img]:https://github.com/eclipse/eclipse-collections/workflows/Checkstyle/badge.svg?branch=master

[actions javadoc]:https://github.com/eclipse/eclipse-collections/actions?query=workflow%3A%22JavaDoc%22
[actions javadoc img]:https://github.com/eclipse/eclipse-collections/workflows/JavaDoc/badge.svg?branch=master

[maven]:http://search.maven.org/#search|gav|1|g:"org.eclipse.collections"%20AND%20a:"eclipse-collections"
[maven img]:https://maven-badges.herokuapp.com/maven-central/org.eclipse.collections/eclipse-collections/badge.svg

[release]:https://github.com/eclipse/eclipse-collections/releases
[release img]:https://img.shields.io/github/release/eclipse/eclipse-collections.svg

[javadoc api]:https://javadoc.io/doc/org.eclipse.collections/eclipse-collections-api
[javadoc api img]:https://javadoc.io/badge2/org.eclipse.collections/eclipse-collections-api/javadoc_eclipse_collections_api.svg

[javadoc impl]:https://javadoc.io/doc/org.eclipse.collections/eclipse-collections
[javadoc impl img]:https://javadoc.io/badge2/org.eclipse.collections/eclipse-collections/javadoc_eclipse_collections.svg

[license-epl]:LICENSE-EPL-1.0.txt
[license-epl img]:https://img.shields.io/badge/License-EPL-blue.svg

[license-edl]:LICENSE-EDL-1.0.txt
[license-edl img]:https://img.shields.io/badge/License-EDL-blue.svg

[snyk-badge]:https://snyk.io/vuln/maven:org.eclipse.collections:eclipse-collections@11.1.0?utm_medium=referral&utm_source=badge&utm_campaign=snyk-widget
[snyk-badge img]:https://snyk-widget.herokuapp.com/badge/mvn/org.eclipse.collections/eclipse-collections/11.1.0/badge.svg

[RichIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/RichIterable.html
[ListIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/list/ListIterable.html
[SetIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/set/SetIterable.html
[Bag]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/bag/Bag.html
[StackIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/stack/StackIterable.html
[MapIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/map/MapIterable.html
[Multimap]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/multimap/Multimap.html
[BiMap]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/bimap/BiMap.html
[Interval]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/impl/list/Interval.html
[MutableCollection]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/collection/MutableCollection.html
[ImmutableCollection]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/collection/ImmutableCollection.html
[LazyIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/LazyIterable.html
[ParallelIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/ParallelIterable.html
[PrimitiveIterable]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/api/PrimitiveIterable.html
[Utilities]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/impl/utility/package-summary.html
[Adapters]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/impl/collection/mutable/AbstractCollectionAdapter.html

[Factories]: https://www.eclipse.org/collections/javadoc/11.1.0/org/eclipse/collections/impl/factory/package-summary.html
[10-0-Release]: https://github.com/eclipse/eclipse-collections/releases/tag/10.0.0
[10-1-Release]: https://github.com/eclipse/eclipse-collections/releases/tag/10.1.0
[10-2-Release]: https://github.com/eclipse/eclipse-collections/releases/tag/10.2.0
[10-3-Release]: https://github.com/eclipse/eclipse-collections/releases/tag/10.3.0
[10-4-Release]: https://github.com/eclipse/eclipse-collections/releases/tag/10.4.0
[11-0-Release]: https://github.com/eclipse/eclipse-collections/releases/tag/11.0.0
[11-1-Release]: https://github.com/eclipse/eclipse-collections/releases/tag/11.1.0
