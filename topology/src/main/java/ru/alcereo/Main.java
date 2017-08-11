package ru.alcereo;

import org.apache.storm.Config;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import static java.util.Arrays.asList;
import static ru.alcereo.StormRunner.runTopologyLocally;
import static ru.alcereo.StormRunner.runTopologyRemotely;

/**
 * Created by alcereo on 09.02.17.
 */
public class Main {

    private final static String TOPOLOGY_NAME = "simple-name-counting-topology";

    public static void main(String[] args) throws InterruptedException, InvalidTopologyException, AuthorizationException, AlreadyAliveException {

        System.out.println("Start configuring topology...");
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("simple-spot", new SimpleSpout());

        builder
            .setBolt("geting-name-bolt", new GettingNameBolt())
            .shuffleGrouping("simple-spot");

        builder
           .setBolt("name-count-bolt", new NameCountingBolt())
           .fieldsGrouping("geting-name-bolt", new Fields("name"));

        Config config = new Config();
        config.setDebug(true);
        config.setNumWorkers(5);

        System.out.println("Finish configuring topology...");
        System.out.println("Start building and run topology...");

        if (asList(args).contains("local")) {
            runTopologyLocally(
                    builder.createTopology(),
                    TOPOLOGY_NAME,
                    config,
                    60
            );
        }else if (asList(args).contains("remote")){
            runTopologyRemotely(
                    builder.createTopology(),
                    TOPOLOGY_NAME,
                    config
            );
        }else{
            throw new IllegalArgumentException("Require argument: local or remote");
        }

        System.out.println("Finish building and run topology...");
        System.out.println("Shutdown...");

    }
}
