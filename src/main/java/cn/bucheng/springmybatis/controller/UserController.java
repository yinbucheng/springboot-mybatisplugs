package cn.bucheng.springmybatis.controller;

import cn.bucheng.springmybatis.entity.User;
import cn.bucheng.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/testSave")
    public Object testSave(){
        User user = new User();
        user.setName("yinchong"+(new Random().nextInt(10)));
        user.setAge(new Random().nextInt(100));
        userService.save(user);
        return "success";
    }

    @RequestMapping("/testList")
    public Object testList(){
        throw new RuntimeException("error");
//        return userService.listAll();
    }
}
