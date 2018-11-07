package cn.bucheng.springmybatis.service;

import cn.bucheng.springmybatis.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> listAll();
}
