/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.hibench.streambench.storm.topologies;

import backtype.storm.topology.*;
import backtype.storm.*;

import com.intel.hibench.streambench.storm.util.*;

public class SingleSpoutTops extends AbstractStormSpoutTops {

    public SingleSpoutTops(StormBenchConfig c) {
        super(c);
    }

    public void run() throws Exception {
        StormSubmitter.submitTopology(config.benchName, getConf(), getBuilder().createTopology());
    }

    public Config getConf() {
        Config conf = new Config();
        conf.setMaxTaskParallelism(200);
        conf.put("topology.spout.max.batch.size", 64 * 1024);
        conf.setNumWorkers(config.workerCount);
        if (!config.ackon)
            conf.setNumAckers(0);
        return conf;
    }

    public TopologyBuilder getBuilder() {
        TopologyBuilder builder = new TopologyBuilder();
        setSpout(builder);
        setBolt(builder);
        return builder;
    }


}
