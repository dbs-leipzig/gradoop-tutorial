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

package org.gradoop.tutorial.operators.subgraph;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.gradoop.common.model.api.entities.Edge;
import org.gradoop.common.model.impl.properties.PropertyValue;
import org.gradoop.flink.io.impl.csv.CSVDataSource;
import org.gradoop.flink.io.impl.dot.DOTDataSink;
import org.gradoop.flink.model.impl.epgm.LogicalGraph;
import org.gradoop.flink.util.GradoopFlinkConfig;
import org.gradoop.tutorial.helper.TutorialHelper;

import java.io.File;

/**
 * Gradoop tutorial
 * ================
 *
 * Operator: Subgraph
 * Task number: 3
 * Short description: Some edge types have a ‘creationDate’ property.
 *                    Get all edges that contain this property and are crated in 2011-01-01
 *                    between 1 pm (incl.) and 2pm (excl.) by using a user defined edge filter function.
 *
 *                    Optional: Use parameters (by adding a constructor) to define the lower and upper bound
 *                    for the condition.
 */
public class SubgraphTutorial_3 {

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

    /**
     *
     * Insert your code here!
     *
     * Also modify the content of class CreatedInPeriod (see below)
     *
     */

    // print the graph to the console for verification
    graph
      .writeTo(new DOTDataSink("gradoop_tutorial_output" + File.separator + "01_subgraph_3.dot", true,
        DOTDataSink.DotFormat.HTML));

    // finally execute
    env.execute("Gradoop Tutorial - Subgraph - Task 3");
  }

  /**
   * A user defined edge filter function.
   *
   * @param <E> the edge type.
   */
  private static class CreatedInPeriod<E extends Edge> implements FilterFunction<E> {

    /**
     * Default constructor.
     */
    CreatedInPeriod() {
      /**
       * The optional task requires an modified constructor.
       */
    }

    @Override
    public boolean filter(E edge) throws Exception {

      /**
       *
       * Insert your logic here!
       *
       * Functions that may help you:
       * @see Edge#hasProperty(String)
       * @see Edge#getPropertyValue(String)
       * @see PropertyValue#getDateTime()
       * @see java.time.LocalDateTime#isBefore(...) etc.
       *
       */

      return true;
    }
  }
}
