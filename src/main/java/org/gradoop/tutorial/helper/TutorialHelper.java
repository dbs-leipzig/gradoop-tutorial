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

package org.gradoop.tutorial.helper;

import org.gradoop.flink.model.impl.epgm.LogicalGraph;

import org.gradoop.flink.model.impl.operators.aggregation.functions.count.Count;
import org.gradoop.flink.model.impl.operators.keyedgrouping.GroupingKeys;
import org.gradoop.flink.model.impl.operators.keyedgrouping.KeyedGrouping;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

/**
 * Helper class with common used functions.
 */
public abstract class TutorialHelper {

  /**
   * Get the path to the example social network dataset.
   *
   * @return the path to the dataset
   */
  public static String getExampleDataPath() {
    return TutorialHelper.class.getResource(File.separator + "data" + File.separator + "ldbc_sample")
      .getPath();
  }

  /**
   * Creates a schema graph with count aggregation for better visualization.
   *
   * @param graph the input graph
   * @return the schema graph
   */
  public static LogicalGraph getSchemaGraph(LogicalGraph graph) {
    return graph.callForGraph(
      new KeyedGrouping<>(
        Collections.singletonList(GroupingKeys.label()),
        Collections.singletonList(new Count()),
        Collections.singletonList(GroupingKeys.label()),
        Collections.singletonList(new Count())));
  }

  /**
   * Creates a schema graph with count aggregation for better visualization.
   * The property 'browserUsed' is also part of the grouped result.
   *
   * @param graph the input graph
   * @return the schema graph
   */
  public static LogicalGraph getSchemaGraphWithBrowserProperty(LogicalGraph graph) {
    return graph.callForGraph(
      new KeyedGrouping<>(
        Arrays.asList(GroupingKeys.label(), GroupingKeys.property("browserUsed")),
        Collections.singletonList(new Count()),
        Collections.singletonList(GroupingKeys.label()),
        Collections.singletonList(new Count())));
  }
}
