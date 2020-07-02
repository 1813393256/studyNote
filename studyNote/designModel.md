# 设计模式

## 设计模式的六大原则

### 开闭原则

> 对扩展开放，对修改关闭

### 里氏代换原则

> 任何基类可以出现的地方，子类一定可以出现。对实现抽象化的具体步骤的规范

### 依赖倒转原则

> 针对接口编程，依赖于抽象而不依赖于具体

### 接口隔离原则

> 使用多个隔离的接口，比使用单个接口要好，降低类之间的耦合度

### 迪米特法则，又称最少知道原则

> 一个实体应当尽量少的与其他实体之间发生相互作用，使得系统功能模块相对独立

### 合成复用原则

> 尽量使用合成/聚合的方式，而不是使用继承

## 创建型模式

> 创建对象的同时隐藏创建逻辑

### 工厂模式

> 主要解决接口选择的问题 
>
> **应用：** 您需要一辆汽车，可以直接从工厂里面提货，而不用去管这辆汽车是怎么做出来的，以及这个汽车里面的具体实现。 ![](https://www.runoob.com/wp-content/uploads/2014/08/AB6B814A-0B09-4863-93D6-1E22D6B07FF8.jpg )

### 抽象工厂模式

> 主要解决接口选择的问题 
>
> **应用：** QQ 换皮肤，一整套一起换。 
>
> ![](https://www.runoob.com/wp-content/uploads/2014/08/3E13CDD1-2CD2-4C66-BD33-DECBF172AE03.jpg )

### 单例模式

> 一个全局使用的类频繁地创建与销毁 
>
> **应用：** 一个班级只有一个班主任 
>
> ![](https://www.runoob.com/wp-content/uploads/2014/08/62576915-36E0-4B67-B078-704699CA980A.jpg )

### 建造者模式

>解决套餐点餐问题，不同的食物组合会生成不同的套餐
>
>**应用：**  1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。 2、JAVA 中的 StringBuilder。 
>
>![](https://www.runoob.com/wp-content/uploads/2014/08/builder_pattern_uml_diagram.jpg )

### 原型模式

> 在运行期建立和删除原型 
> **应用：** 1、细胞分裂。 2、JAVA 中的 Object clone() 方法
>
> ![](https://www.runoob.com/wp-content/uploads/2014/08/prototype_pattern_uml_diagram.jpg )

## 结构型模式

### 适配器模式

> 解决接口不兼容问题
>
> ![](https://www.runoob.com/wp-content/uploads/2014/08/adapter_pattern_uml_diagram.jpg )

 

### 桥接模式

> 在有多种可能会变化的情况下，用继承会造成类爆炸问题，扩展起来不灵活 
>
> **应用：** 1、对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统。2、 一个类存在两个独立变化的维度，且这两个维度都需要进行扩展。 
>
> ![](https://www.runoob.com/wp-content/uploads/2014/08/bridge_pattern_uml_diagram.jpg )

### 过滤器模式

### 组合模式

### 装饰者模式

### 外观模式

### 享元模式

### 代理模式

## 行为型模式

### 责任链模式

### 命令模式

### 解释器模式

### 迭代模式

### 中介者模式

### 备忘录模式

### 观察者模式

### 状态模式

### 空对象模式

### 策略模式

### 模板模式

### 访问者模式

## J2EE模式

### MVC模式

### 业务代表模式

### 组合实体模式

### 数据访问对象模式

### 前端控制器模式

### 拦截过滤器模式

### 服务定位模式

### 传输对象模式
