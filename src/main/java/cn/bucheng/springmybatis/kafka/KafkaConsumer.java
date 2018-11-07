package cn.bucheng.springmybatis.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author buchengyin
 * @Date 2018/11/7 22:53
 **/
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test_demo",groupId = "group1")
    public void listen(ConsumerRecord<?,String> record) {
        String value = record.value();
        System.out.println("--------------------->consumer1  value:"+value);
        System.out.println("--------------------->consumer1  record:"+record);
    }
}
