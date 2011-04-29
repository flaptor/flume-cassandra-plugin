package com.flaptor.indextank;

import java.io.IOException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.safehaus.uuid.UUID;
import org.safehaus.uuid.UUIDGenerator;

import com.cloudera.flume.conf.Context;
import com.cloudera.flume.conf.SinkFactory.SinkBuilder;
import com.cloudera.flume.core.Event;
import com.cloudera.flume.core.EventSink;
import com.cloudera.util.Pair;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.ConsistencyLevelPolicy;
import me.prettyprint.hector.api.HConsistencyLevel;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.model.ConfigurableConsistencyLevel;
/**
 * Allows Cassandra to be used as a sink, primarily for log messages.
 * 
 * When the Cassandra sink receives an event, it does the following:
 *
 * 1. Creates a column where the name is a type 1 UUID (timestamp based) and the
 *    value is the event body.
 * 2. Inserts it into row "YYYYMMDD" (the current date) in the given ColumnFamily.
 *
 * CassandraSink primarily targets log storage right now.
 */
public class CassandraSink extends EventSink.Base {
    private static final UUIDGenerator uuidGen = UUIDGenerator.getInstance();
    private static final long MILLI_TO_MICRO = 1000; // 1ms = 1000us

    private String dataColumnFamily;
    private String indexColumnFamily;
    private Mutator<String> mutator; 
    private final StringSerializer stringSerializer = StringSerializer.get();
    private final String servicename;
    private final String subservicename;


    public CassandraSink(String servicename, String subservicename) {
        this.servicename = servicename;
        this.subservicename = subservicename;
        this.dataColumnFamily = dataColumnFamily;
        this.indexColumnFamily = indexColumnFamily;

        ConfigurableConsistencyLevel cl = new ConfigurableConsistencyLevel();
        cl.setDefaultReadConsistencyLevel(HConsistencyLevel.ONE);
        cl.setDefaultWriteConsistencyLevel(HConsistencyLevel.ONE);

        CassandraHostConfigurator chc = new CassandraHostConfigurator("cassandra1.internal.indextank.com:9160,cassandra2.internal.indextank.com:9160,cassandra3.internal.indextank.com:9160");
        chc.setAutoDiscoverHosts(true);

        Cluster cluster = HFactory.getOrCreateCluster("IndexTank storage", chc);
        Keyspace keyspace = HFactory.createKeyspace("LOG", cluster, cl);
        mutator = HFactory.createMutator(keyspace, stringSerializer);
    }

    @Override
        public void open() throws IOException {
            //this.cClient.open();
        }

    /**
     * Writes the message to Cassandra.
     * The key is the current date (YYYYMMDD) and the column
     * name is a type 1 UUID, which includes a time stamp
     * component.
     */
    @Override
        public void append(Event event) throws IOException, InterruptedException {

            long hour = event.getTimestamp();

            // Make the index column
            String uuid = uuidGen.generateTimeBasedUUID().toString();
            mutator.addInsertion(uuid, "log", HFactory.createStringColumn("timestamp", Long.toString(event.getTimestamp())))
                .addInsertion(uuid, "log", HFactory.createStringColumn("data", new String(event.getBody())))
                .addInsertion(uuid, "log", HFactory.createStringColumn("hour", String.valueOf(hour)))
                .addInsertion(uuid, "log", HFactory.createStringColumn("host", event.getHost()))
                .addInsertion(uuid, "log", HFactory.createStringColumn("service", servicename))
                .addInsertion(uuid, "log", HFactory.createStringColumn("severity", event.getPriority().toString()));
            if (null != subservicename) {
                mutator.addInsertion(uuid, "log", HFactory.createStringColumn("subservice", subservicename));
            }
            mutator.execute();
            super.append(event);
        }

    /**
     * Returns a String representing the current date to be used as
     * a key.  This has the format "YYYYMMDDHH".
     */
    private byte[] getKey() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        StringBuffer buff = new StringBuffer();
        buff.append(year);
        if(month < 10)
            buff.append('0');
        buff.append(month);
        if(day < 10)
            buff.append('0');
        buff.append(day);
        if(hour < 10)
            buff.append('0');
        buff.append(hour);
        return buff.toString().getBytes();
    }

    @Override
    public void close() throws IOException {
    }

    public static SinkBuilder builder() {
        return new SinkBuilder() {
            @Override
                public EventSink build(Context context, String ... args) {
                    if (args.length < 1 || args.length > 2) {
                        throw new IllegalArgumentException("usage: cassandra\"servicename\"[, subservicename]");
                    }
                    if (args.length == 1) {
                        return new CassandraSink(args[0], null);
                    } else {
                        return new CassandraSink(args[0], args[1]);
                    }
                }
        };
    }

    /**
     * This is a special function used by the SourceFactory to pull in this class
     * as a plugin sink.
     */
    public static List<Pair<String, SinkBuilder>> getSinkBuilders() {
        List<Pair<String, SinkBuilder>> builders =
            new ArrayList<Pair<String, SinkBuilder>>();
        builders.add(new Pair<String, SinkBuilder>("cassandra", builder()));
        return builders;
    }
}
