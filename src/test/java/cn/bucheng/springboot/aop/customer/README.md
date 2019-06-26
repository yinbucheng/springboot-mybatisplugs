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