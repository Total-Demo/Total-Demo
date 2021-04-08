## 版本说明
>https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

## 相关地址
spring-cloud-alibaba：
>https://github.com/Macintosh-c/spring-cloud-alibaba

nacos：
>https://github.com/Macintosh-c/nacos

Sentinel：
>https://github.com/Macintosh-c/Sentinel

## blog：
>https://blog.didispace.com/spring-cloud-learning/
>https://www.jianshu.com/p/9a8d94c0c90c
>https://blog.csdn.net/enjoyedu/category_10498794.html

## 一.Nacos部署
持久化:
>https://blog.didispace.com/spring-cloud-alibaba-4/

集群搭建：
>https://blog.didispace.com/spring-cloud-alibaba-5/

Nacos 依赖 Java 环境来运行。如果你是从代码开始构建并运行Nacos，还需要为此配置Maven环境，请确保是在以下版本环境中安装使用:

64 bit OS，支持 Linux/Unix/Mac/Windows，推荐选用 Linux/Unix/Mac。

64 bit JDK 1.8+

Maven 3.2.x+

### 1.下载并安装
#### 1)下载源码
git clone https://github.com/alibaba/nacos.git

注意：默认为develop分支，需要切换到对应的版本分支

#### 2)安装到本地仓库
cd nacos/

mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U 

#### 3)启动Nacos
cd distribution/target/nacos-server-$version/nacos/bin

- Linux

./startup.sh -m standalone

- Windows

startup.cmd

#### 4)访问服务
打开浏览器访问：http://localhost:8848/nacos

注：从 0.8.0 版本开始，需要登录才可访问，默认账号密码为 nacos/nacos

#### 5)参考：
>https://www.jianshu.com/p/9a8d94c0c90c


## 二.Sentinel 控制台

### 1.下载编译
git clone https://github.com/alibaba/Sentinel.git

注意：需要切换到对应的版本分支

mvn clean package

### 2.启动控制台
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

### 3.访问服务 默认的登录帐号和密码都是：sentinel
打开浏览器访问：http://localhost:8080/#/dashboard/home


## 三.学习知识点总结:
### 1.Nacos 配置中心
#### 1)区分不同环境
- profile
- group id
- namespace
#### 2)持久化到外部mysql
#### 3)集群化部署
#### 4)灰度发布-ip
勾选“Beta发布”，在文本框里填入要下发配置的IP，多个IP用逗号分隔

### 2.Sentinel 
> https://github.com/Macintosh-c/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/sentinel-example/sentinel-core-example/readme-zh.md
#### 1)自定义限流处理逻辑
- 默认限流异常处理
- 使用 @SentinelResource 注解下的限流异常处理

#### 2)动态规则扩展
- 文件配置
- Nacos配置
- ZooKeeper配置
- Apollo配置
>https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95#datasource-%E6%89%A9%E5%B1%95

#### 3)使用Nacos存储规则
>https://blog.didispace.com/spring-cloud-alibaba-sentinel-2-1/

#### 4)各种规则理解与配置
- 流控规则  flowrule
- 降级规则  degraderrule
- 热点规则  param-flow
- 系统规则  system
- 授权规则  authority
>https://blog.csdn.net/enjoyedu/category_10498794.html

### 3.Gateway  网关
> https://blog.csdn.net/rxh811/article/details/106667220/
> https://blog.csdn.net/u010046908/article/details/100015060
> https://www.jb51.net/article/179062.htm
#### 1)断言
内置路由断言工厂
自定义路由断言工厂

#### 2)过滤器
局部过滤器
- 内置局部过滤器
- 自定义局部过滤
全局过滤器
- 内置全局过滤器
- 自定义全局过滤器

#### 2)网关限流


### 4.Sleuth 链路追踪
[为服务名称，traceId，spanId，是否将链路的追踪结果输出到第三方平台]
[nacos-provider,********,*********,false]
traceId:一条链路，多个微服务相同
spanId：一条链路，多个微服务不同

