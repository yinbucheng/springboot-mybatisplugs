package cn.bucheng.springmybatis.mapper;

import cn.bucheng.springmybatis.entity.User;

import java.util.List;

public interface UserMapper {
    int save(User user);
    List<User> listAll();
}
