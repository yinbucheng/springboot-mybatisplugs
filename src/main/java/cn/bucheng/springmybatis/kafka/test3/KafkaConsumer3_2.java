package cn.bucheng.springmybatis.kafka.test3;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author buchengyin
 * @Date 2019/2/1 12:16
 * 采用手动提交
 **/
public class KafkaConsumer3_2 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("group.id", "hello-group4");
        props.put("auto.offset.reset","latest");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("exclude.internal.topics", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test_topic2"));
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
            if (!records.isEmpty()) {
                for (ConsumerRecord<Integer, String> record : records) {
                    System.out.println("------------------>detail4:"+record);
                    //提交offset
                    consumer.commitSync();
                }
            }

        }
    }
}
