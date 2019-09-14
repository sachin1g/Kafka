package procom;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serializers.CustomSerializer;

import java.util.Properties;

public class KafkaProducerCustomSerializer {
    private static final Logger logger = LogManager.getLogger(KafkaProducerCustomSerializer.class);
    public final static String applicationID = "HelloProducer";
    public final static String bootstrapServers = "localhost:9092";
    public final static String topicName = "customSerdeTopic";
    public final static int numEvents = 10;

    public static void main(String[] args) {

        logger.info("Creating Kafka Producer with custom serializer...");
        System.out.println("Creating kafka producer with custom serializer");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class.getName());

        KafkaProducer<Integer, User> producer = new KafkaProducer<>(props);
        logger.info("Start sending messages with custom serializer...");
        System.out.println("Start Sending messages with custom serializer");
        for(int i=0;i<numEvents;i++){
            producer.send(new ProducerRecord<Integer, User>(topicName,i,new User(i,"name"+i)));
        }

        logger.info("Finished - Closing Kafka Producer with custom serializer.");
        producer.close();

    }
}