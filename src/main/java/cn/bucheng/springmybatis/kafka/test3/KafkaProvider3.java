package cn.bucheng.springmybatis.kafka.test3;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author buchengyin
 * @Date 2019/2/1 12:17
 * 采用同步提交
 **/
public class KafkaProvider3 {
    public static void main(String[] args)throws Exception {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("producer.type", "sync");
        properties.put("request.required.acks", "-1");
        //properties.put("retries", 0);//retries = MAX 无限重试，直到你意识到出现了问题:
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>("test_topic2", i, "hello "+i);
            kafkaProducer.send(producerRecord);
            System.out.println("------------------------>send message to kafka");
            Thread.sleep(10000);
        }
        kafkaProducer.close();
        System.out.println("product end");
    }
}
