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

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.gradoop.common.model.api.entities.Vertex;
import org.gradoop.common.model.impl.properties.PropertyValue;
import org.gradoop.flink.io.impl.csv.CSVDataSource;
import org.gradoop.flink.io.impl.dot.DOTDataSink;
import org.gradoop.flink.model.api.functions.KeyFunction;
import org.gradoop.flink.model.impl.epgm.LogicalGraph;
import org.gradoop.flink.model.impl.functions.epgm.LabelIsIn;
import org.gradoop.flink.model.impl.operators.keyedgrouping.KeyedGrouping;
import org.gradoop.flink.util.GradoopFlinkConfig;
import org.gradoop.tutorial.helper.AddAgeToPerson;
import org.gradoop.tutorial.helper.TutorialHelper;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Gradoop tutorial
 * ================
 *
 * Operator: Grouping
 * Task number: 3
 * Short description: How many people are born on the same weekday (Mon – Sun)?
 *                    How old is the youngest and oldest person in the group?
 *                    How many know each other from these groups?
 * Note: You already have a subgraph with 'person' nodes and 'knows' edges.
 *       All person vertices have now an additional property 'age' as Integer.
 */
public class GroupingTutorial_3 {

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
    LogicalGraph graph = dataSource.getLogicalGraph()
      .subgraph(new LabelIsIn<>("person"), new LabelIsIn<>("knows"))
      // Adds a property 'age' to all person vertices.
      .transformVertices(new AddAgeToPerson());
    
    //-------------------------------------------------------------------------------------------------------
    // Add the right grouping key functions and aggregates in the lists below.
    //
    // Also modify the content of class GetDayOfDate (see below)
    //
    //  Classes that may help you:
    //  @see org.gradoop.flink.model.impl.operators.keyedgrouping.GroupingKeys
    //  @see org.gradoop.flink.model.impl.operators.aggregation.functions.count.Count
    //  @see org.gradoop.flink.model.impl.operators.aggregation.functions.average.AverageProperty
    //  @see org.gradoop.flink.model.impl.operators.aggregation.functions.min.MinProperty
    //  @see org.gradoop.flink.model.impl.operators.aggregation.functions.max.MaxProperty
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
    graph.writeTo(new DOTDataSink("gradoop_tutorial_output" + File.separator + "02_grouping_3.dot", true,
        DOTDataSink.DotFormat.HTML));

    // finally execute
    env.execute("Gradoop Tutorial - Grouping - Task 3");
  }

  /**
   * A user defined grouping key function.
   *
   * @param <V> the type of the vertex.
   */
  private static class GetDayOfDate<V extends Vertex> implements KeyFunction<V, String> {
    /**
     * Extracts the key to group.
     *
     * @param vertex the input vertex entity
     * @return the key used to group the vertices
     */
    @Override
    public String getKey(V vertex) {
      String dayOfWeek = "unknown";
      /**
       *
       * Insert your logic here!
       *
       * Functions that may help you:
       * @see Vertex#hasProperty(String)
       * @see Vertex#getPropertyValue(String)
       * @see PropertyValue#getDate()
       * @see LocalDate#getDayOfWeek()
       * @see DayOfWeek#name()
       *
       */
      return dayOfWeek;
    }

    /**
     * Adds a property to the group that shows the grouping key.
     */
    @Override
    public void addKeyToElement(V element, Object key) {
      element.setProperty("birthDayOfWeek", key);
    }

    @Override
    public TypeInformation<String> getType() {
      return TypeInformation.of(String.class);
    }
  }
}
