# Gradoop Tutorial

**This repository provides a tutorial for the Gradoop API.**

In this tutorial you will learn to:

* use Gradoop for extensive graph analytics.
* use Gradoop-Operators to answer specific analytical questions.
* execute Gradoop on a shared-nothing Flink cluster.

Please find the [tutorial instructions](https://github.com/dbs-leipzig/gradoop-tutorial/wiki) in the Wiki of this
 repository.

### Requirements

This tutorial is based on pre-compiled Gradoop maven artifacts. To set up the tutorial environment please
 clone this repository.
 
```git clone https://github.com/dbs-leipzig/gradoop-tutorial.git```

A sample dataset is already included in the repository.
 
 You also require:
 
* Java 8
* Maven 3.*
* [Graphviz](https://graphviz.gitlab.io/download/) for result visualization (Online GraphViz tool is
 available [here](https://dreampuf.github.io/GraphvizOnline/#digraph%20%7B%0A%0Asubgraph%20cluster_g5f510d30ea80a38fa8609449%7B%0Alabel%3D%22Gradoop%20Tutorial%22%3B%0Av5f510d30ea80a38fa86094465f510d30ea80a38fa8609449%20%5Bshape%3DMrecord%2C%20label%3D%22Hello%22%5D%3B%0Av5f510d30ea80a38fa86094475f510d30ea80a38fa8609449%20%5Bshape%3DMrecord%2C%20label%3D%22World%22%5D%3B%0Av5f510d30ea80a38fa86094465f510d30ea80a38fa8609449-%3Ev5f510d30ea80a38fa86094475f510d30ea80a38fa8609449%20%5Blabel%3D%225f510d30ea80a38fa8609448%22%5D%3B%0A%7D%0A%0A%7D))
* IDE to code :) preferably [IntelliJ IDEA](https://www.jetbrains.com/de-de/idea/)

For more information see the [Setup](https://github.com/dbs-leipzig/gradoop-tutorial/wiki/Setup) page of the tutorial wiki.

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