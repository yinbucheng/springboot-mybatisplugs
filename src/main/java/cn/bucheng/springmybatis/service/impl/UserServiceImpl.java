package cn.bucheng.springmybatis.service.impl;

import cn.bucheng.springmybatis.entity.User;
import cn.bucheng.springmybatis.mapper.UserMapper;
import cn.bucheng.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }
}
