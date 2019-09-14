package procom;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static config.KafkaConfig.*;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

//A Producer with asynchronous send with a callback function
public class KafkaProducerSync {
    private static final Logger logger = LogManager.getLogger(KafkaProducerSync.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        logger.info("Creating Kafka Sync Producer...");
        System.out.println("Creating kafka Sync producer");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        logger.info("Sync Send");
        System.out.println("Sync send");

        for(int i=0;i<MessageCount;i++){
            producer.send(new ProducerRecord<>(topicName,i,"SyncSend"+i)).get();
        }

        logger.info("Finished - Closing Kafka Producer.");
        producer.close();

    }
}