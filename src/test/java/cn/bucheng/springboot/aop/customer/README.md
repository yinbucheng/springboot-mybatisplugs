# 模拟springboot中的aop核心类

```
主要接口：
Match
MethodInterceptor

主要类：
ProxyFactory
ReflectMethodProcced

主要思想：
1.编写一个接口Joinpoint，其具有一个核心方法procced
2.编写一个接口MethodInterceptor，其包含一个方法持有上面接口
3.编写一个接口Match，其提供当前目标方法匹配结果
4.编写实现Joinpoint，Match的实现类
  >>持有MethodInterceptor列表
  >>持有目标对象Target
  >>持有目标方法Method
  >>持有目标方法参数 args
  >>实现procced方法
  
5.编写一个类实现InvocationHandler其内部创建上面的实现类  


```

设计总结（这就是变异后的责任链）
```
1.至少需要两个对象A,B
2.A持有B对象(或者B对象列表)
3.B上面的b方法入参存在一个为A对象
4.A对象上面执行一段逻辑可能会调用B.b方法
5.B对象上面b方法执行一段逻辑一定会调用A.a方法
6.起始于A.a方法结束于A.a方法，中间可能会中转B.b方法


A对象是个包装对象其会持有需要链式处理的对象（也就是责任对象）
```



![image](https://github.com/yinbucheng/mypic/blob/master/aop2.png?raw=true)