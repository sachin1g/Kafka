package procom;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Collections;
import java.util.Properties;

public class KafkaFirstConsumer {
    public static void main(String[] args) {
        Properties kafkaProps=new Properties();

        kafkaProps.setProperty("bootstrap.servers","localhost:9092");
        kafkaProps.setProperty("group.id","test-consumer-group");
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "k");

        KafkaConsumer consumer=new KafkaConsumer(kafkaProps);
        System.out.println("Topics List:");
        for(int i=0;i< consumer.listTopics().values().toArray().length;i++){
            System.out.println((consumer.listTopics().values().toArray()[i]));
        }
        System.out.println("Reading data");

        consumer.subscribe(Collections.singletonList("firsttopic"));
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(100);
            for (ConsumerRecord<Integer, String> record : records) {
                System.out.println(record.value().toString());
            }
        }
    }
}
