package procom;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * A Kafka producer that sends numEvents (# of messages) to a given topicName
 *
 * @author prashant
 * @author www.learningjournal.guru
 */
public class KafkaFirstProducer {
    private static final Logger logger = LogManager.getLogger(KafkaFirstProducer.class);
    public final static String applicationID = "HelloProducer";
    public final static String bootstrapServers = "localhost:9092";
    public final static String topicName = "firsttopic";
    public final static int numEvents = 10;

    public static void main(String[] args) {

        logger.info("Creating Kafka Producer...");
        System.out.println("Creating kafka producer");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        logger.info("Start sending messages...");
        System.out.println("Start Sending message");
        int i=0;

          for(i=0;i<numEvents;i++){
              producer.send(new ProducerRecord<>(topicName,i,"new"+i));
          }

        logger.info("Finished - Closing Kafka Producer.");
        producer.close();

    }
}