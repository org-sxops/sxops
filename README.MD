# 项目初始化操作
## Mybatis生成相关文件
- mapper
- model
- mybatis的xml配置文件

修改resource/mybatis-generator/generatorConfig.xml文件jdbcConnection的连接源。
运行mvn插件mybatis-generator。

# 备注
## 数据库
项目默认使用sqllite，如果需要改变，修改application.yml配置项 `spring.datasource.driver-class-name` 和 `spring.datasource.url` 即可。
## 包命名
项目默认使用了 `com.test` 作为groupId，修改时需要注意一下几点。

- 修改pom配置文件中的 `groupId` 
- 修改application.yml中的 `mapper.mappers` 配置项

## redis
redis在项目路径 `resource/redis-2.8.12` 下，启动 `redis-server.exe` 即可快速启动一个本地redis。来进行redis操作。
如需要连接远程redis，可修改application.yml配置项 `redis.hostName` 、 `redis.port` 和 `redis.password` 。

## 接口权限
在接口上添加类似 `@RequiresPermissions("animal:add")` 的注解，若用户存在 `animal:add` 权限，则可以访问。
权限拦截器 `PermissionInterceptor` 中只实现了部分功能，对于用户所拥有的权限，需要自行实现。

### common.utils
- 获取Spring上下文 `ApplicationContext` 对象。（SpringContextHolder）
- 错误异常响应处理
- 发送Http请求，获取 `RestTemplate` 对象。（HttpRest）

### common.model
- `Department` ERP系统中的部门对象
- `AdUser` ERP系统中的用户对象

### common.mapper
- `MysqlBaseMapper` 所有的Mapper接口都需要实现这个接口。（通用Mapper）
 
### common.log
- ID生成
- 日志相关对象

### common.id
- 简洁的ID生成

### common.aspect
- 业务前面，用来打印业务调用日志

## configcenter
```xml
<dependency>
    <groupId>com.test</groupId>
    <artifactId>configcenter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
用于获取系统中的配置变量。
使用如下：
```java
ConfigurationHolder.get().getProperty(spring.profiles.active);
```
可参考使用 
```java
@Value("${spring.profiles.active}")
```

## mbg
```xml
<dependency>
    <groupId>com.test</groupId>
    <artifactId>mbg</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
应该是用户MyBatis生成器的插件。具体不详。这里不多说了，欢迎补充
**_PS_**： 本项目使用了插件生成。具体 `Mybatis生成相关文件` 。


### web.utils
- 发送Http请求。 `HttpClientUtils`
- 获取客户端IP
- 请求参数处理工具

### web.aspect
控制层切面。应用于所有 `RestController` 注解的类。
用于接口调用打印日志。