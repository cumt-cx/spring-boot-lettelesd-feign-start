package com.littlesd.demo.web;

import com.littlesd.demo.feign.github.GitHubClientAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/github")
public class GitHubWeb {

    @Autowired
    private GitHubClientAPI gitHubClientAPI;

    @RequestMapping(value = "/users/{user}")
    public String getUser(@PathVariable(value = "user")String userName){
        return gitHubClientAPI.getUser(userName).toString();
    }

    /**
     * 测试访问地址/hello
     * @return 格式化字符串
     */
    @RequestMapping(value = "/hello")
    public String sayHello()
    {
        return "hello";
    }

}
