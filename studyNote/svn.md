# SVN版本控制

**svn属于c/s结构软件(客户端与服务端)**

```
服务端软件：VisualSVN
网址：https://www.visualsvn.com/ 

客户端软件：Tortoisesvn
网址：https://tortoisesvn.net/downloads.html
```

1. 第一次与服务器连接并下载项目到本地，checkout（检出）指令

2. 以后更新数据到本地，update指令

3. commit（提交指令）


## SVN服务端配置

1. 在svn服务端创建一个公有目录WebApp，在WebApp下创建一个项目目录Shop文件夹，作为Shop(版本仓库),每一个项目即为一个版本仓库
2. 创建版本仓库，基本语法：svnadmin create D:/svn/WebApp/Shop
3. 进行服务端监管，基本语法：svnserve -d(后台运行) -r(监管目录) 版本仓库路径。svnserve -d -r D:/svn/WebApp。
4. 此时客户端可以通过输入svn://localhost或ip地址就可直接指向Shop版本仓库
5. 权限控制。svn默认是禁止匿名用户接入的，所以可以取消Shop/conf/svnserve.conf文件下的anon-access=read注解

## SVN客户端配置

1. 安装客户端Tortoisesvn
2. 右键点击Tortoisesvn->Repo-browser建立与服务端svn的连接
3. 输入svn://localhost即可，然后在新界面点击鼠标右键检出即可

## SVN版本回退

鼠标右键Tortoisesvn->Update to reversion...->show log->选择版本->ok->ok

## SVN版本冲突

1. 合理分配项目开发时间：person1上午，person2下午

2. 合理分配项目开发模块：person1购物，person2文章

3. svn解决

   1. 更新服务端数据到本地

      hello.txt:整合后的hello.txt文件

      hello.txt:自己修改后的hello.txt文件

      hello.txt.r6:未冲突前的文件

      hello.txt.r7:先提交的那个人的文件

   2. 删除除hello.txt以外的其他三个文件

   3. 修改整合hello.txt文件

   4. 重新提交数据到svn服务器端

## 配置多仓库与管理权限

​			列：WebApp目录下有Shop项目和WeChat项目

### 配置多仓库

可以通过监管父目录来达到监管所有仓库的目的

svnserve -d(后台运行) -r(监管目录) WebApp(项目总目录)

svn://localhost或ip地址来访问D:/svn/WebApp目录

若访问Shop项目: svn://localhost/Shop

### 配置权限控制

要使用权限控制，需要先开启权限功能，在每个仓库中都有一个conf文件夹，里面有三个文件：

**authz文件：**授权文件。告诉哪些用户具有哪些权限

**passwd文件：**认证文件。标识当前svn系统中某个仓库具有哪些用户以及相应的密码

**svnserve.conf文件**，对以上两个文件进行开启或禁用

**开启步骤如下：**

1. 注释匿名用户的可读写权限：#anon-access=write

2. 开启认证文件和授权文件：password-db=passwd，authz-db=authz

3. passwd文件，编写认证文件定义相关用户名和密码

   用户名(admin)=密码(admin)

4. authz文件，编写授权文件

   1. 先进行分组groups下(组名=用户列表)
   2. 对项目进行配置：[Shop:/]通过@组名对改组进行权限设置，@admin=rw  @itcast=r   *=r(*代表匿名用户)

   

## SVN服务的配置与管理

### 配置自启动服务

```
sc create SVNService binpath="D:\subversion\bin\svnserve.exe --service -r D:/svnroot" start= auto depend= Tcpip

sc create 服务名称 binpath=空格"svnserve.exe --service -r D:/svn/WebApp" start=空格 auto 
```

### 创建批处理文件（.bat）文件

```
启动服务： net start 服务名称
停止服务： net stop 服务名称
删除服务： sc delete 服务名称
```

## SVN钩子指令

钩子程序相当于触发器，钩子程序默认情况可以采用批处理指令或Shell指令来进行编写

**示例：**使用post-commit.bat钩子程序

1. 设置服务器端SVN路径：SET SVN= "D:\svn\bin\svn.exe"

2. 设置服务器端项目运行目录：SET DIR= "D:\server\apache\htdocs\shop"

3. 通过update指令实时更新数据到DIR目录：SVN update %DIR%

4. 具体步骤如下：

   1. 复制post-commit.tmpl文件为post-commit.bat文件

   2. 填入相关批处理指令

      SET SVN= "D:\svn\bin\svn.exe"

      SET DIR= "D:\server\apache\htdocs\Shop"

      SVN update %DIR%

   3. 在server\apache\htdocs下创建Shop目录并更新SVN服务端数据到本地

   4. 更新数据到SVN服务器端时，Shop目录下可以实时自动获取到最新数据

   5. 可以通过虚拟主机形式直接访问更新文件

      localhost/Shop/update.php

## SVN扩展程序

BAE云引擎

# SVN指令

## SVN服务端(DOS命令)

创建版本库：svnadmin create D:/svn/WebApp/Shop

进行服务监管：svnserve -d -r D:/svn/WebApp

自启动：sc create SVNService binpath="D:\subversion\bin\svnserve.exe --service -r D:/svn/WebApp" start= auto depend= Tcpip

## SVN客户端(DOS命令)

检出：svn checkout svn://localhost/Shop

添加：svn add HelloSvn.txt

上传：svn commit -m '说明' HelloSvn.txt

更新：svn update HelloSvn.txt