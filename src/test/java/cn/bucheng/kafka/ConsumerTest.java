package cn.bucheng.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author ：yinchong
 * @create ：2019/6/19 8:51
 * @description：kafka消费者测试代码
 * @modified By：
 * @version:
 */
public class ConsumerTest {

    @Test
    public void testAutoCommit(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,10);
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,20);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,60);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"test");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        Consumer<String,String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("test_topic"));
        for(;;){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(10));
            if(poll.isEmpty()){
                continue;
            }
            Iterator<ConsumerRecord<String, String>> iterator = poll.iterator();
            while(iterator.hasNext()){
                ConsumerRecord<String, String> next = iterator.next();
                System.out.println(next.key()+" "+next.value()+" "+next.partition());
            }
        }
    }
}
