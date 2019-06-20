# es使用

## 基本概念
```
1.document
对应一条记录,类似数据库中记录

2.segment
提高es的写入速度

3.translog
用于异常恢复es中写入内存中的数据

4.type
类似数据库中的表，es7后只支持一个index下一个type

5.field
字段对应的列

6.分片
用于进行负载均衡，类型kafka中的分区

7.index
类似数据库中的库(schema)

8.副本
用于保证es的高可用
```

#原理讲解

es所有原理
![image](https://github.com/yinbucheng/mypic/blob/master/es.png?raw=true)

es写入原理
![image](https://github.com/yinbucheng/mypic/blob/master/es1.png?raw=true)

es更新原理
![image](https://github.com/yinbucheng/mypic/blob/master/es2.png?raw=true)

es整体原理
![image](https://github.com/yinbucheng/mypic/blob/master/es3.png?raw=true)