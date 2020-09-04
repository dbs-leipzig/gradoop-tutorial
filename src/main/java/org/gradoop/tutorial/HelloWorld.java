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

package org.gradoop.tutorial;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.gradoop.flink.util.FlinkAsciiGraphLoader;
import org.gradoop.flink.util.GradoopFlinkConfig;

/**
 * A simple hello world application to verify if everything is working as needed for the tutorial.
 */
public class HelloWorld {

  /**
   * The main function to run the hello world example.
   */
  public static void main(String[] args) throws Exception {
    // create flink execution environment
    ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
    // create Gradoop config
    GradoopFlinkConfig config = GradoopFlinkConfig.createConfig(env);
    // load an example graph
    FlinkAsciiGraphLoader loader = new FlinkAsciiGraphLoader(config);
    loader.initDatabaseFromString("g0[(v:Hello)-[]->(v2:World)]");
    // print the graph
    loader.getLogicalGraph().print();

    System.out.println("" +
      " #####                                            \n" +
      "#     # #####    ##   #####   ####   ####  #####  \n" +
      "#       #    #  #  #  #    # #    # #    # #    # \n" +
      "#  #### #    # #    # #    # #    # #    # #    # \n" +
      "#     # #####  ###### #    # #    # #    # #####  \n" +
      "#     # #   #  #    # #    # #    # #    # #      \n" +
      " #####  #    # #    # #####   ####   ####  #      \n" +
      "                                                  ");
  }
}
