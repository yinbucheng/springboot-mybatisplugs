# mysql使用

常用命令
````
#查看当前事务情况
select * from information_schema.innodb_trx

#查看当前长事务（大于一分钟）
select trx_id,trx_mysql_thread_id from information_schema.INNODB_TRX where now()-trx_started>60

#查看当前活跃执行语句
select * from information_schema.processlist

#查看当前锁情况
select * from information_schema.innodb_locks

#查看更多信息
show engine innodb status \G;

````