# kafka中使用

## broken中核心配置

````
#配置回话最大超时配置
group.max.session.timeout.ms=300000

#配置回话的最小超时
group.min.session.timeout.ms=60000

#配置同步时isr中最小数量(这主要是用于发送端ack为-1时结合使用)
min.insync.replicas=2

#配置broken接收单批次消息的最大的大小
message.max.bytes=31457280

#配置默认创建主题的分区数量
num.partitions=3

````



## 生产者核心配置
````
#应对模式
ack=-1(-1 配置broken中min.insync.replicas使用,0 表示不需要确认, 1 只需要主broken确认)

#配置一次发送数据到broken上面最大大小
max.request.size=xxx

#批量发送大小,用于整合将多次发送同一分区的数据记录，减少和broken网络交换次数
batch.size=xxx 

#如果上面条件没满足，则等待下面时间然后将数据再整合后进行发送，如果在等待时间过程中满足则立即发送
linger.ms=xxx

#生产者最大使用内存，用于缓存一定记录，再发送到broken
buffer.memory=xxx

#上述内存不足时，等待多长时间再抛出异常
max.block.ms=xxx
````

## 消费者核心配置

````
````

### 架构图

![image](https://raw.githubusercontent.com/yinbucheng/mypic/master/640.png)

## 常见问题
```
1. kafka生产者只需send().get()方法获取元数据时会出现 TimeoutException: Failed to update metadata after 60 ms.
修改为send(,callback)回调就没有这个问题

```