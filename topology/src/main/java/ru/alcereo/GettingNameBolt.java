package ru.alcereo;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;


public class GettingNameBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
//        System.out.println("execute getting name bolt");

        collector.emit(new Values(
           input.getStringByField("name")
        ));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//        System.out.println("declare fields getting name bolt");

        declarer.declare(new Fields("name"));
    }
}
