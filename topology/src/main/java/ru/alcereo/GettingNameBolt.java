package ru.alcereo;

import org.apache.log4j.MDC;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GettingNameBolt extends BaseBasicBolt {
    private static final Logger log = LoggerFactory.getLogger(GettingNameBolt.class);

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
//        System.out.println("execute getting name bolt");
        MDC.put("id", input.getLongByField("id"));
        MDC.put("name", input.getStringByField("name"));
        MDC.put("desc", input.getStringByField("desc"));

        log.debug("get tuple from spout, executing name counting");

        collector.emit(new Values(
           input.getStringByField("name")
        ));

        MDC.clear();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("name"));
    }
}
