# Spring

## spring设计模式

### 1)工厂设计模式

spring使用工厂模式可以通过BeanFactory(延迟注入,用时注入，程序启动快)或者ApplicationContext创建bean对象(一次性创建所有bean)

### 2)单例设计模式

Spring中bean的默认作用域：Singleton，prototype，request,session,global-session

### 3)动态代理设计模式

spring AOP就是基于动态代理的，代理对象实现了某个接口，spring AOP会使用JDK Proxy；否则使用cglib生成一个被代理对象的子类来作为代理

### 4)模板方法设计模式

将一些步骤延迟到子类中，spring中jdbcTemplate类

### 5)观察者设计模式

spring事件驱动模型就是观察者模式很经典的应用

### 6)适配器模式

springmvc中的HandlerAdapter适配器

### 7)装饰者设计模式

spring中配置DataSource时，DataSource可能是不同的数据库和数据源

### 8)策略设计模式

spring框架的资源访问接口（Resource）就是基于策略设计模式实现的