package ru.alcereo;

import org.apache.log4j.MDC;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alcereo on 10.02.17.
 */
public class NameCountingBolt extends BaseBasicBolt{

    private static final Logger log = LoggerFactory.getLogger(NameCountingBolt.class);

    private Map<String,Integer> counter = new HashMap<>();

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {

        MDC.put("name", input.getStringByField("name"));
        log.debug("get new name");

        counter.put(
                input.getStringByField("name"),
                counter.get(input.getStringByField("name"))==null? 1: counter.get(input.getStringByField("name"))+1
        );

//        System.out.print("\r");
//        counter.forEach(
//                (s, integer) -> System.out.printf("%s - %d |",s,integer)
//        );

        log.debug("Counter context: {}", collector);

        MDC.clear();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
