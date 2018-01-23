# 项目分享

分享一个项目：[boot-feign-start](https://github.com/cumt-cx/spring-boot-lettelesd-feign-start.git),feign集成至springBoot,自定义了部分常用的配置
# 项目介绍

自定义的spring-boot的feign starter，为更方便的feign的使用并集成spring-boot的auto configuration
# 打包
修改相关的maven私服地址,在feign工程下
```shell
mvn clean install
```
# 使用方式
## 依赖
```
        <dependency>
            <groupId>com.littlesd.start</groupId>
            <artifactId>feign</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
 ```
## 集成
在spring-boot项目的application.yml文件中加入配置实例如下：

```
feign:
  clients:
    github:
      base-url: https://api.github.com
      request-headers:
        K-AppCode: java
        K-AppKey: helloworld
        K-SignVer: 2
    huobi:
      base-url: https://api.huobi.pro/market
      loglovel: FULL
     
```
## 使用
* 将上述配置项赋予正确的值
* interface定义以及返回http序列化对象定义

```java
package com.littlesd.demo.feign.github;

import javax.ws.rs.*;

@Path("/")
public interface GitHubClientAPI {

    @GET
    @Path("/users/{userName}")
    GitHubUser getUser(@PathParam("userName") String userName);

}
```

* GitHubClientAPI的使用

```java
@RestController
@RequestMapping("/github")
public class GitHubWeb {
    
    @Autowired
    private GitHubClientAPI gitHubClientAPI;

    @RequestMapping(value = "/users/{user}")
    public GitHubUser getUser(@PathVariable(value = "user")String userName){
        return gitHubClientAPI.getUser(userName);
    }

}
```

### 其他
