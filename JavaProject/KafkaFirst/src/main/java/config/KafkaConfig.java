package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KafkaConfig {

    public final static String applicationID = "HelloProducer";
    public final static String bootstrapServers = "localhost:9092";
    public final static String topicName = "firsttopic";
    public final static int MessageCount = 10;
}
