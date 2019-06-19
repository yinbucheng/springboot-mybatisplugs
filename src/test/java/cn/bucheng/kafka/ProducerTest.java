package cn.bucheng.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author ：yinchong
 * @create ：2019/6/19 8:52
 * @description：kafka生成者测试代码
 * @modified By：
 * @version:
 */
public class ProducerTest {

    @Test
    public void test() throws Exception {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,5);
//        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,1024*1024*20)
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG,1024*1024*10);
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,60);
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,1024*1024);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        Producer<String,String> producer = new KafkaProducer<>(properties);
        for(;;){
            ProducerRecord<String,String> record = new ProducerRecord<>("test_topic","test"+new Random().nextInt(1000),"test");
            producer.send(record);
//            RecordMetadata recordMetadata = producer.send(record).get();
//            System.out.println(recordMetadata.partition());
            Thread.sleep(1000);
        }
    }


    @Test
    public void testSyncReplicasAck() throws Exception {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,5);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,10*1024*1024);
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,60);
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG,30);
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG,1024*1024*2);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,1024*1024);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        Producer<String,String> producer = new KafkaProducer<>(properties);
        for(;;){
            ProducerRecord<String,String> record = new ProducerRecord<>("test_topic3","test"+new Random().nextInt(1000),"test");
            producer.send(record,(metadata, exception) -> {
                if(null!=exception){
                    System.err.println(exception);
                    return;
                }
                System.out.println(metadata.partition()+" "+metadata.offset());
            });
            Thread.sleep(1*1000);
        }
    }

    @Test
    public void testSyncMasterAck(){

    }

    @Test
    public void testAsyncAck(){

    }

}
