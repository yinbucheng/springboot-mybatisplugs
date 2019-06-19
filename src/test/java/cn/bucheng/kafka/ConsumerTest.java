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
        //开启自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        //每次提交间隔时间
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        //所处的分组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test37");
        //最大下拉的记录条数，也就是poll方法返回的记录数量
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,10);
        properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG,"3");
        properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG,"1024");
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,"1024");
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,2000);
        //配置topic上面不存在offset的策略，earliest表示从头开始
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,80000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        Consumer<String,String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("test_topic"));
        for(;;){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
            if(poll.isEmpty()){
                continue;
            }
            Iterator<ConsumerRecord<String, String>> iterator = poll.iterator();
            while(iterator.hasNext()){
                ConsumerRecord<String, String> next = iterator.next();
                System.out.println("==================="+next.key()+" "+next.value()+" "+next.partition());
            }
        }
    }

    @Test
    public void testNotAutoCommit(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test4");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,10);
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,2000);
        //配置topic上面不存在offset的策略，earliest表示从头开始
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,80000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        Consumer<String,String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("test_topic"));
        for(;;){
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
            if(poll.isEmpty()){
                continue;
            }
            Iterator<ConsumerRecord<String, String>> iterator = poll.iterator();
            while(iterator.hasNext()){
                ConsumerRecord<String, String> next = iterator.next();
                System.out.println("==================="+next.key()+" "+next.value()+" "+next.partition());
            }
            consumer.commitSync();
        }
    }
}
