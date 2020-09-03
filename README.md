# Gradoop Tutorial

**This repository provides a tutorial for the Gradoop API.**

In this tutorial you will learn to:

* use Gradoop for extensive graph analytics.
* use Gradoop-Operators to answer specific analytical questions.
* execute Gradoop on a shared-nothing Flink cluster.

Please find the [tutorial instructions](https://github.com/dbs-leipzig/gradoop-tutorial/wiki/Tutorial-Instructions) in the Wiki of this
 repository.

### Requirements

This tutorial is based on pre-compiled Gradoop maven artifacts. To set up the tutorial environment please
 clone this repository.
 
```git clone https://github.com/dbs-leipzig/gradoop-tutorial.git```

A sample dataset is already included in the repository.
 
 You also require:
 
* Java 8
* Maven 3.*
* [Graphviz](https://graphviz.gitlab.io/download/) for result visualization
* IDE to code :) preferably [IntelliJ IDEA](https://www.jetbrains.com/de-de/idea/)

## What is Gradoop?

[Gradoop](http://www.gradoop.com) is an open source (ALv2) research framework for scalable 
graph analytics built on top of [Apache Flink](http://flink.apache.org/). It offers a graph data model which 
extends the widespread [property graph model](https://github.com/tinkerpop/blueprints/wiki/Property-Graph-Model) 
by the concept of logical graphs and further provides operators that can be applied 
on single logical graphs and collections of logical graphs. The combination of these 
operators allows the flexible, declarative definition of graph analytical workflows.
Gradoop can be easily integrated in a workflow which already uses Flink&reg; operators
and Flink&reg; libraries (i.e. Gelly, ML and Table).

The project's documentation can be found in our [Gradoop-Wiki](https://github.com/dbs-leipzig/gradoop/wiki).