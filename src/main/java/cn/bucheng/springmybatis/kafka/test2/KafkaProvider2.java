package cn.bucheng.springmybatis.kafka.test2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;

import java.util.Properties;

/**
 * @ClassName KafkaProvider2
 * @Author buchengyin
 * @Date 2018/11/8 13:51
 **/
public class KafkaProvider2 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        String topic ="my-topic";
        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>(topic, Integer.toString(i), "yinchong_"+i));
            System.out.println("----------------->提交第"+i+"消息到broken中");
        }

        producer.close();
    }
}
