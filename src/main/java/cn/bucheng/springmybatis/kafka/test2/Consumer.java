package cn.bucheng.springmybatis.kafka.test2;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * @ClassName Consumer
 * @Author buchengyin
 * @Date 2018/11/8 14:12
 **/
public class Consumer {
    public static void autoCommitOffset(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("my-topic", "test-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("----------->offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                System.out.println();
            }
        }
    }


    /**
     * 提交指定的offset
     * @param partition
     * @param offset
     */
    public static void commitOffset(Integer partition,Long offset,Boolean commit){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("my-topic"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.println("------------>revoke partition");
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
           for(ConsumerRecord<String,String> record:records){
               System.out.println("---------------->partition:"+ record.partition() +" offset:"+record.offset()+" key:"+record.key()+" value:"+record.value());
           }
           if(commit!=null&&commit) {
               if (partition == null || offset == null) {
                   consumer.commitSync();
               } else {
                   Map<TopicPartition, OffsetAndMetadata> hashMap = new HashMap<>();
                   hashMap.put(new TopicPartition("my-topic", partition), new OffsetAndMetadata(offset, "no metadata"));
                   consumer.commitSync(hashMap);
               }
           }
        }
    }

    public static void finallyCommit(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        Map<TopicPartition, OffsetAndMetadata> hashMap = new HashMap<>();
        consumer.subscribe(Arrays.asList("my-topic", "test-topic"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
               consumer.commitSync(hashMap);
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                  for(TopicPartition topicPartition:partitions){
                      consumer.seek(topicPartition,10000);
                  }
            }
        });
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
//            consumer.seek(new TopicPartition("my-topic",0),1000);
            for(ConsumerRecord<String,String> record:records){
                System.out.println("---------------->partition:"+ record.partition() +" offset:"+record.offset()+" key:"+record.key()+" value:"+record.value());
                hashMap.clear();
                hashMap.put(new TopicPartition("my-topic", record.partition()), new OffsetAndMetadata(record.offset()+1, "no metadata"));
            }
           consumer.commitSync(hashMap);
        }
    }

    public static void assignPartition(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        String topic ="my-topic";
        TopicPartition partition0 = new TopicPartition(topic,0);
        TopicPartition partition1 = new TopicPartition(topic,1);
        consumer.assign(Arrays.asList(partition0,partition1));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("----------->offset = %d,partition=%d, key = %s, value = %s%n", record.offset(),record.partition(), record.key(), record.value());
                System.out.println();
            }
        }
    }
}
