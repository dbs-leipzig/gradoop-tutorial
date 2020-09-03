/*
 * Copyright © 2014 - 2020 Leipzig University (Database Research Group)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradoop.tutorial.operators.complex;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.gradoop.flink.io.impl.csv.CSVDataSource;
import org.gradoop.flink.model.impl.epgm.LogicalGraph;
import org.gradoop.flink.model.impl.functions.epgm.LabelIsIn;
import org.gradoop.flink.model.impl.operators.combination.ReduceCombination;
import org.gradoop.flink.util.GradoopFlinkConfig;
import org.gradoop.tutorial.helper.TutorialHelper;

/**
 * Gradoop tutorial
 * ================
 *
 * Operator: Query
 * Task number: 1
 * Short description: Create a friendship graph of all friends of the person named "John".
 */
public class ComplexTutorial_1 {

  /**
   * The main function to run your application.
   *
   * @param args no arguments needed
   * @throws Exception in case of an error
   */
  public static void main(String[] args) throws Exception {
    // create flink execution environment
    ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
    // create Gradoop config
    GradoopFlinkConfig config = GradoopFlinkConfig.createConfig(env);
    // create the data source
    CSVDataSource dataSource = new CSVDataSource(TutorialHelper.getExampleDataPath(), config);
    // load the graph
    LogicalGraph graph = dataSource.getLogicalGraph();

    graph = graph.vertexInducedSubgraph(new LabelIsIn<>(
      "person", "tag", "country", "city", "post"));


    graph = graph
      .query(
        "MATCH (p1:person)<-[:hasCreator]-(po:post)<-[:likes]-(p2:person)\n" +
        "(p1)-[:isLocatedIn]->(c1:city)\n" +
        "(p2)-[:isLocatedIn]->(c2:city)\n" +
        "(po)-[:hasTag]->(t:tag)\n" +
        "(c1)-[:isPartOf]->(ca:country)<-[:isPartOf]-(c2)\n" +
        "WHERE p1 != p2", "(ca)-[new:hasInterest]->(t)")
      .reduce(new ReduceCombination<>());

    System.out.println(graph.getVertices().count());

//    // group on vertex and edge labels + count grouped edges
//    LogicalGraph groupedGraph  = graph.callForGraph(
//      new Grouping.GroupingBuilder()
//        .setStrategy(GroupingStrategy.GROUP_COMBINE)
//        .addVertexGroupingKey("name")
//        .useEdgeLabel(true).useVertexLabel(true)
//        .addEdgeAggregateFunction(new Count())
//        .build());
//
//    // filter all edges below a fixed threshold
//    groupedGraph = groupedGraph.edgeInducedSubgraph(new FilterFunction<EPGMEdge>() {
//      @Override
//      public boolean filter(EPGMEdge epgmEdge) throws Exception {
//        return epgmEdge.getPropertyValue("count").getLong()>5;
//      }
//    });
//
//    // print the graph to the console for verification
//    groupedGraph.writeTo(new DOTDataSink("gradoop_tutorial_output" + File.separator + "04_complex_1.dot",
//      true,
//      DOTDataSink.DotFormat.HTML));
//
//    // finally execute
//    env.execute("Gradoop Tutorial - Grouping - Task 1");
  }
}
