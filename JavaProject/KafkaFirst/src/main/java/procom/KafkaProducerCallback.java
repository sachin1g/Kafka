package procom;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static config.KafkaConfig.*;
import java.util.Properties;

//A Producer with asynchronous send with a callback function
public class KafkaProducerCallback {
    private static final Logger logger = LogManager.getLogger(KafkaProducerCallback.class);

    public static void main(String[] args) {

        logger.info("Creating Kafka async with callback Producer...");
        System.out.println("Creating kafka async with callback producer");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        class DemoProducerCallback implements Callback
        {

            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e != null){
                    System.out.println(e.getStackTrace());
                }
            }
        }
        logger.info("async with callback Send");
        System.out.println("async with callback send");

        for(int i=0;i<MessageCount;i++){
            producer.send(new ProducerRecord<>(topicName,i,"async with callback"+i),new DemoProducerCallback());
        }

        logger.info("Finished - Closing Kafka Producer.");
        producer.close();

    }
}