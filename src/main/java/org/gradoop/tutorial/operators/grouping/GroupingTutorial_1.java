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

package org.gradoop.tutorial.operators.grouping;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.gradoop.flink.io.impl.csv.CSVDataSource;
import org.gradoop.flink.io.impl.dot.DOTDataSink;
import org.gradoop.flink.model.impl.epgm.LogicalGraph;
import org.gradoop.flink.model.impl.operators.keyedgrouping.KeyedGrouping;
import org.gradoop.flink.util.GradoopFlinkConfig;
import org.gradoop.tutorial.helper.TutorialHelper;

import java.io.File;
import java.util.Arrays;

/**
 * Gradoop tutorial
 * ================
 *
 * Operator: Grouping
 * Task number: 1
 * Short description: Create a schema graph by grouping vertices and edges by label.
 *                    How many vertices and edges exist per label?
 */
public class GroupingTutorial_1 {

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

    //-------------------------------------------------------------------------------------------------------
    // Add the right grouping key functions and aggregates in the lists below.
    //
    //  Classes that may help you:
    //  @see org.gradoop.flink.model.impl.operators.keyedgrouping.GroupingKeys
    //  @see org.gradoop.flink.model.impl.operators.aggregation.functions.count.Count
    //-------------------------------------------------------------------------------------------------------
    graph = graph.callForGraph(
      new KeyedGrouping<>(
        Arrays.asList(/* Add vertex grouping key functions here */),
        Arrays.asList(/* Add vertex aggregate functions here */),
        Arrays.asList(/* Add edge grouping key functions here */),
        Arrays.asList(/* Add edge aggregate functions here */)
      )
    );

    // print the graph to the console for verification
    graph.writeTo(new DOTDataSink("gradoop_tutorial_output" + File.separator + "02_grouping_1.dot", true,
        DOTDataSink.DotFormat.HTML));

    // finally execute
    env.execute("Gradoop Tutorial - Grouping - Task 1");
  }
}
