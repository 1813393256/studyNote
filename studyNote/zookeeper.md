# Zookeeper

## 概述

1. Zookeeper是一个开源的分布式的，为分布式应用提供协调服务的Apache项目

## 工作机制

1. 基于观察者模式设计的分布式服务管理框架，它负责存储和管理大家都关心的数据，然后接受观察者的注册，一旦这些数据的状态发生变化，Zookeeper就将负责通知已经在Zookeeper上注册的那些观察者做出相应的反应。
2. Zookeeper=文件系统+通知机制

## 特点

1. Zookeeper：一个领导者(Leader），多个跟随者（Follower）组成的集群
2. 集群中只要有半数以上节点存活，Zookeeper集群就能正常服务
3. 全局数据一致：每个Server保存一份相同的数据副本，Client无论连接到哪个Server，数据都是一致的
4. 更新请求顺序进行，来自同一个Client的更新请求按其发送顺序依次执行
5. 数据更新原子性，一次数据更新要么成功，要么失败
6. 实时性，在一定时间范围内，Client能读到最新数据

## 数据结构

1. Zookeeper数据模型的结构与Unix文件系统很类似，整体上都可以看作是一颗树，每个节点称作一个ZNode。每一个ZNode默认能够存储1MB的数据，每一个ZNode都可以通过其路径唯一标识。

## 应用场景

1. **统一命名服务**

   分布式环境下，经常需要对应用/服务进行统一命名，便于识别。例：IP不易记，域名容易记。

2. **统一配置管理**

   1. 分布式环境下，配置文件同步非常常见
      1. 一般要求一个集群中，所有节点的配置信息是一致的，比如Kafka集群
      2. 对配置文件修改后，希望能够快速同步到各个节点上
   2. 配置管理可交由Zookeeper实现
      1. 可将配置信息写入Zookeeper上的一个Znode。
      2. 各个客户端服务器监听这个Znode
      3. 一旦Znode中的数据被修改，Zookeeper将通知各个客户端服务器

3. **统一集群管理**

   1. 分布式环境中，实时掌握每个节点的状态是必要的。
      1. 可根据节点实时状态做出一些调整
   2. Zookeeper可以实现实时监控节点状态变化
      1. 可将节点信息写入Zookeeper上的一个ZNode
      2. 监听这个ZNode可获取它的实时状态变化

4. **服务器节点动态上下线**

   1. 客户端能实时洞察到服务器上下线的变化

5. **软负载均衡等。**

   1. 在Zookeeper中记录每台服务器的访问数，让访问数最少的服务器去处理最新的客户端请求

6. **下载地址**：https://zookeeper.apache.org/releases.html

## 安装

1. **安装jdk**

2. 拷贝Zookeeper安装包到Linux系统下

3. **解压到指定目录**

   tar -zxvf zookeeper-3.4.10.tar.gz -C /opt/module/

4. **配置修改**

   1. 将/opt/module/zookeeper-3.4.10/conf这个路径下的zoo sample.cfg修改为zoo.cfg;

      ```linux
      mv zoo_sample.cfg zoo.cfg
      ```

   2. 打开zoo.cfg文件，修改dataDir路径:

      ```java
      vim zoo.cfg
      //修改内容如下：
      dataDir=/opt/module/zookeeper-3.4.10/zkData
      ```

   3. 在/opt/module/zookeeper-3.4.10/这个目录下创建zkData文件夹

      ```linux
      mkdir zkData
      ```

5. 操作**Zookeeper**

   ```linux
   //1.启动Zookeeper
   bin/zkServer.sh start
   //2.查看进程是否启动
   jps
   //3.查看状态
   bin/zkServer.sh status
   //4.启动客户端
   bin/zkCli.sh
   //5.退出客户端
   quit
   //6.停止Zookeeper
   bin/zkServer.sh stop
   ```

6. **参数配置解读**

   ```markdown
   1. tickTime=2000	//心跳时间2秒
   2. initLimit=10		//初始化的最大心跳数，leader和follower刚开始通信时的最大延迟时间为2000ms*10=20s
   3. syncLimit=5：		//集群已正常启动之后，LF之间的通信时的最大延迟时间2000ms*5=10s
   ```

## 内部原理

1. **选举机制**

   1. 半数机制：集群中半数以上机器存活，集群可用。所以Zookeeper适合安装奇数台服务器
   2. Zookeeper虽然在配置文件中并没有指定Master和Slave，但Zookeeper工作时，是有一个节点为Leader，其他则为Follower，Leader是通过内部的选举机制临时产生的
   3. 每个服务上线都先投自己，发现票数不到一半，则投给在本服务中id最大的，票数超过一半，则被选为Leader，其他则为Follower.

2. **节点类型**

   1. **持久**：客户端和服务端断开连接后，创建的节点不删除

      1. 持久化目录节点

         客户端与Zookeeper断开连接后，该节点依旧存在

      2. 持久化顺序编号目录节点

         客户端与Zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号

      3. 说明

         创建Znode时设置顺序标识，Znode名称后会附加一个值，顺序号时一个单调递增的计数器，由父节点维护

      4. 注意

         在分布式系统中，顺序号可以被用于为所有的事件进行全局排序，这样客户端可以通过顺序号推断事件的顺序

   2. **短暂**：客户端和服务端断开连接后，创建的节点自己删除

## 实战

