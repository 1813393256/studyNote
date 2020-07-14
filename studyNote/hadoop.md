# Hadoop

## 介绍

1. Hadoop时Apache旗下的一个用java语言实现开源软件框架，是一个开发和运行处理大规模数据的软件平台，允许使用简单的编程模型和大量计算机集群上对大型数据集进行分布式处理

### 核心组件

1. HDFS(分布式文件系统):解决海量数据存储
2. YARN(作业调度和集群资源管理的框架):解决资源任务调度
3. MapReduce(分布式运算编程框架):解决海量数据计算

### 特性优点

1. 扩容能力(Scalable):Hadoop是在可用的计算机集群间分配数据并完成计算任务的，这些集群可以方便的扩展到数以千计的节点中。
2. 成本低(Economical)：Hadoop通过普通廉价的机器组成服务器集群来分发以及处理数据，以至于成本很低。
3. 高效率(Efficient):通过并发数据，Hadoop可以在节点之间动态并行的移动数据，使得速度非常快
4. 可靠性(Rellable):能自动维护数据的多份复制，并且在任务失败后能自动地重新部署(redeploy)计算任务。

## 集群搭建

### HDFS集群

1. 负责海量数据的存储
2. 集群中的角色主要有:NameNode、DataNode、SecondaryNameNode

### YARN集群

1. 负责海量数据运算时的资源调度
2. 集群中的角色主要有:ResourceManager、NodeManage

### MapReduce

1. mapreduce是一个分布式运算编程框架，是应用程序开发包，由用户按照编程规范进行程序开发，后打包运行在HDFS集群上，并且受到YARN集群的资源调度管理

### Hadoop部署方式

1. Hadoop部署方式分三种：Standalone mode(独立模式)、Pseudo-Distributed mode(伪分布式模式)、Cluster mode(集群模式)，其中前两种都是在单机部署

**独立模式：**又称单机模式，仅一个机器运行一个java进程，主要用于调试

**伪分布模式：**也是在一个机器上运行HDFS的NameNode和DataNode、YARN的ResourceManager和NodeManager，但分别启动单独的java进程，主要用于调试。

**集群模式：**主要用于生产环境部署。会使用N台主机组成一个Hadoop集群。这种部署模式下，主节点和从节点会分开部署到不同的机器上。

**角色分配如下：**

```
node-01		NameNode	DataNode	ResourceManager
node-02		DataNode	NodeManager	SecondaryNameNode
node-03		DataNode	NodeManeger
```

### 服务器系统设置

1. **同步时间：**

   ```linux
   #手动同步集群各机器时间
   date -s "2017-03-03 03:03:03"
   #网络同步时间
   yum install ntpdate
   ntpdate cn.pool.ntp.org
   ```

2. **设置主机名：**

   ```linux
   vi /etc/sysconfig/network
   NETWORKING=yes
   HOSTNAME=node-1
   ```

3. **配置IP、主机名映射**

   ```linux
   vi /etc/hosts
   192.168.33.101	 node-1
   192.168.33.102 	 node-2
   192.168.33.103	 node-3
   ```

4. **配置ssh免密登录密钥**

   ```linux
   #生成ssh免密登录密钥
   ssh-keygen -t rsa(四个回车)
   #执行完该命令后，会生成id_rsa(私钥)、id_rsa.pub(公钥)
   #将公钥拷贝到要免密登录的目标机器上
   ssh-copy-id node-02
   ```

5. **配置防火墙**

   ```linux
   #查看防火墙状态
   service iptables status
   #关闭防火墙
   service iptables stop
   #查看防火墙开机启动状态
   chkconfig iptables --list
   #关闭防火墙开机启动
   chkconfig iptables off
   ```

6. **JDK环境安装**

   ```linux
   #上传jdk安装包
   jdk-8u65-linux-x64.tar.gz
   #解压安装包
   tar zxvf jdk-8u65-linux-x64.tar.gz -C /root/apps
   #配置环境变量 /etc/profile
   export JAVA_HOME=/root/apps/jdk1.8.0_65
   export PATH=$PATH:$JAVA_HOME/bin
   export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/bin/tools.jar
   #刷新配置
   source /etc/profile
   ```

7. **Hadoop配置文件修改**

   ```linux
   #hadoop安装主要就是配置文件的修改，一搬在主节点进行修改，完毕后scp下发给其他各个节点机器
   7.1	hadoop-env.sh
   	文件中设置的是Hadoop运行时需要的环境变量。JAVA_HOME时必须设置的，即使我们当前的系统中设置了JAVA_HOME,它也是不认识的，因为Hadoop即使是在本机上执行，它也是把当前的执行环境当成远程服务器。
   	vi hadoop-env.sh
   	export JAVA_HOME=/root/apps/jdk1.8.0_65
   
   7.2 core-site.xml
   	指定Hadoop所使用的文件系统schema(URI)，HDFS的老大(NameNode)的地址
   	<property>
   		<name>fs.defaultFS</name>
   		<value>hdfs://node-1:9000</value>
   		tfs://
   		file://
   		gfs://
   	</property>
   	指定hadoop运行时产生文件的存储目录，默认/tmp/hadoop-${user.name}
   	<property>
   		<name>hadoop.tmp.dir</name>
   		<value>/home/hadoop/hadop-2.4.1/tmp</value>
   	</property>
   
   7.3	hdfs-site.xml
   	指定HDFS副本的数量
   	<property>
   		<name>dfs.replication</name>
   		<value>2</value>
   	</property>
   	<property>
   		<name>dfs.namenode.secondary.http-address</name>
   		<value>node-2:50090</value>
   	</property>
   	
   7.4 mapred-site.xml
   	mv mapred-site.xml.template mapred-site.xml
   	vi mapred-site.xml
   	指定mr运行时框架，这里指定在yarn上，默认时local
   	<property>
   		<name>mapreduce.framework.name</name>
   		<value>yarn</value>
   	</property>
   
   7.5 yarn-site.xml
   	指定YARN的老大(ResourceManager)的地址
   	<property>
   		<name>yarn.resourcemanager.hostname/name>
   		<value>node-1</value>
   	</property>
   	NodeManager上运行的附属服务。需配置成mapreduce_shuffle,才可运行MapReduce程序默认值
   	<property>
   		<name>yarn.nodemanager.aux-services</name>
   		<value>mapreduce_shuffle</value>
   	</property>
   
   7.6 slaves文件，里面写上从节点所在的主机名字
   vi slaves
   	node-1
   	node-2
   	node-3
   
   7.7 将hadoop添加到环境变量
   vim /etc/profile
   	export JAVA_HOME=/root/apps/jdk1.8.0_65
   	export HADOOP_HOME=/root/apps/hadoop-2.7.4
   	export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME:$HADOOP_HOME/sbin
   	
   source /etc/profile
   
   7.9 将配置好的文件远程拷贝到另一台服务器上
   scp -r /export/server/hadoop-2.7.4/ root@mode-2:/export/server/
   
   7.10 环境拷贝
   scp -r /etc/profile root@node-2:/etc/profile
   
   source /etc/profile
   ```

### Hadoop集群启动、初体验

**启动方式**

```linux
要启动Hadoop集群，需要启动HDFS和YARN两个集群。

注意：首次启动HDFS时，必须对其进行格式化操作。本质上是进行文件系统的初始化操作，创建一些自己所需要的文件，格式化之后，集群启动成功，后续再也不要进行格式化，格式化在主节点（namenode）中进行

hdfs namenode -format (hadoop namenode -format)
```

**单个节点逐个启动**

```
在主节点上使用以下命令启动HDFS NameNode；
hadoop-deamon.sh start namenode

在每个从节点上使用以下命令启动HDFS DataNode；
hadoop-deamon.sh start datanode

在主节点上使用以下命令启动YARN ResourceManager；
yarn-deamon.sh start resourcemanager

在每个从节点上使用以下命令启动YARN nodemanager
yarn-deamon.sh start nodemanager

以上脚本位于$HADOOP_PREFIX/sbin/目录下。如果想要停止某个节点上某个角色，只需要把命令中的start改为stop
```

**脚本一键启动**

```linux
如果配置了etc/hadoop/slaves和ssh免密登录，则可使用程序脚本启动所有Hadoop两个集群的相关进程，在主节点所设定的机器上执行。
	hdfs:$HADOOP_PREFIX/sbin/start-dfs.sh
	yarn:$HADOOP_PREFIX/sbin/start-yarn.sh
	停止集群：stop-dfs.sh、stop-yarn.sh
```

**集群Web-ui**

```
一旦Hadoop集群启动并运行，可以通过web-ui进行集群查看，如：
NameNode	http://node-1:post/ 	 默认5070
ResourceManager		http://node-1:port		默认8088
```

