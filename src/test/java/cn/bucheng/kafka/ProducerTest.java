package cn.bucheng.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;
import java.util.Random;

/**
 * @author ：yinchong
 * @create ：2019/6/19 8:52
 * @description：kafka生成者测试代码
 * @modified By：
 * @version:
 */
public class ProducerTest {

    @Test
    public void testSyncReplicasAck(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,5);
//        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,1024*1024*20)
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG,1024*1024*10);
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,60);
        properties.put(ProducerConfig.ACKS_CONFIG,-1);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,1024*1024);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        Producer<String,String> producer = new KafkaProducer<>(properties);
        for(;;){
            ProducerRecord<String,String> record = new ProducerRecord<>("test_topic","test"+new Random().nextInt(1000),"test");
            producer.send(record);
        }
    }

    @Test
    public void testSyncMasterAck(){

    }

    @Test
    public void testAsyncAck(){

    }

}
