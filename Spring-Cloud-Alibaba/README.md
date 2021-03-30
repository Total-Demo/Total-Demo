## 版本说明
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

## 相关地址
spring-cloud-alibaba：https://github.com/Macintosh-c/spring-cloud-alibaba

nacos：https://github.com/Macintosh-c/nacos

Sentinel：https://github.com/Macintosh-c/Sentinel

## blog：
https://blog.didispace.com/spring-cloud-learning/
https://www.jianshu.com/p/9a8d94c0c90c


## Nacos部署
Nacos 依赖 Java 环境来运行。如果你是从代码开始构建并运行Nacos，还需要为此配置Maven环境，请确保是在以下版本环境中安装使用:

64 bit OS，支持 Linux/Unix/Mac/Windows，推荐选用 Linux/Unix/Mac。
64 bit JDK 1.8+
Maven 3.2.x+

### 下载并安装

#### 下载源码
git clone https://github.com/alibaba/nacos.git
注意：默认为develop分支，需要切换到对应的版本分支

#### 安装到本地仓库
cd nacos/
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U 

#### 启动Nacos
cd distribution/target/nacos-server-$version/nacos/bin

##### Linux
./startup.sh -m standalone

##### Windows
startup.cmd

#### 访问服务
打开浏览器访问：http://localhost:8848/nacos
注：从 0.8.0 版本开始，需要登录才可访问，默认账号密码为 nacos/nacos

#### 参考：
https://www.jianshu.com/p/9a8d94c0c90c



## Sentinel 控制台

### 下载编译
git clone https://github.com/alibaba/Sentinel.git
mvn clean package

### 启动控制台
Sentinel 控制台是一个标准的 SpringBoot 应用，以 SpringBoot 的方式运行 jar 包即可。
cd sentinel-dashboard\target
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar

```
java -Dserver.port=8480 -Dcsp.sentinel.dashboard.server=localhost:8480 -Dproject.name=sentinel-dashboard -Dsentinel.dashboard.auth.username=sentinel -Dsentinel.dashboard.auth.password=123456 -jar sentinel-dashboard-1.7.2.jar
-Dserver.port=8480 # 指定控制台的端口为8480
-Dcsp.sentinel.dashboard.server=localhost:8480 # 指定要被哪个控制台监控（这里指定的是自己监控自己）
-Dproject.name=sentinel-dashboard # 指定实例名称（名称会在控制台左侧以菜单显示）
-Dsentinel.dashboard.auth.username=sentinel # 设置登录的帐号为：sentinel
-Dsentinel.dashboard.auth.password=123456 # 设置登录的密码为：123456
```

如果8080端口冲突请修改-Dserver.port=自定义端口号

### 访问服务 默认的登录帐号和密码都是：sentinel
打开浏览器访问：http://localhost:8080/#/dashboard/home