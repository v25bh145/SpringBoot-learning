package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//路由从 /demo 开始
@RequestMapping(path="/demo")
public class MainController {
    //自动从库中装载类到这个变量(默认取相同的类)
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    //@ResponseBody意味着这个接口返回数据而不返回视图
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        //@RequestParam意味着这个参数将从get/ost中获得
        //其实这里应该再抽象一层为Service层，负责对数据进行逻辑处理

        //这里一般的框架逻辑在这里可以稍微讲一下了:
        //首先，最底层的是@Entity类，其负责的是将数据库中的表以可实体化为对象的class类表示
        //之后，@UserRepository负责@Entity类与数据库交互的接口部分
        //再向上，Service层负责处理数据的逻辑，例如实现存储数据/验证数据等等
        //最后，我们可以通过@Controller层调用@Service层提供的方法来实现对数据库的修改操作
        User n = new User();
        n.setName(name);
        n.setEmail(email);

        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
