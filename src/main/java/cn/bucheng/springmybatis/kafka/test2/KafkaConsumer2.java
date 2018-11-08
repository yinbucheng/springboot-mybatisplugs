package cn.bucheng.springmybatis.kafka.test2;

/**
 * @ClassName KafkaConsumer2
 * @Author buchengyin
 * @Date 2018/11/8 13:52
 **/
public class KafkaConsumer2  extends Consumer{
    public static void main(String[] args) {
        commitOffset(null,null,true);
//        assignPartition();
    }


}
