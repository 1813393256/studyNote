# Spring Security OAuth2.0认证授权

## 1. 基本概念

### 1.1 什么是认证

**认证：**判断一个用户的身份是否合法的过程

### 1.2 认证方式

基于session方式和基于token方式

### 1.3 什么是授权

**授权：**用户认证通过后根据用户的权限来控制用户访问资源的过程，拥有资源的访问权限则正常访问，没有权限则拒绝访问。

### 1.4 RBAC

1. **RBAC：**基于角色的访问控制(Role-Based Access Control)是按角色进行授权

   ```java
   if(主体.hasRole("总经理角色id")||主体.hasRole("部门经理角色id")){
   	查询工资
   }//扩展性不高
   ```

   

2. **RBAC**：基于资源的访问控制(Resource-Based Access Control)是按资源(或权限)进行授权

   ```java
   if(主体.hasPerssion("查询工资权限标识")){
   	查询工资
   }//扩展性好
   ```

## 2. Spring Security快速上手

### 2.1 pom.xml

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>5.1.5.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>5.1.5.RELEASE</version>
</dependency>
<packaging>war</packaging>
<build>
   <finalName>security-springmvc-session</finalName>
   <pluginManagement>
      <plugins>
         <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                 <path>/${project.artifactId}</path>
            </configuration>
         </plugin>
         <plugin>
             <groupId>org.apache.maven.plugins</groupId>
             <artifactId>maven-compiler-plugin</artifactId>
             <configuration>
                 <source>1.8</source>
                 <target>1.8</target>
              </configuration>
          </plugin>
          <plugin>
             <artifactId>maven-resources-plugin</artifactId>
             <configuration>
                <encoding>utf-8</encoding>
                <useDefaultDelimiters>true</useDefaultDelimiters>
                <resources>
                   <resource>
                       <directory>src/main/resources</directory>
                        <filtering>true</filtering>
                        <includes>
                            <include>**/*</include>
                         </includes>
                    </resource>
                 	<resource>
                        <directory>src/main/java</directory>
                        <includes>
                            <include>**/*.xml</include>
                         </includes>
                     </resource>
                </resources>
              </configuration>
            </plugin>
        </plugins>
   </pluginManagement>
</build>
```