package cn.bucheng.springmybatis.service.impl;

import cn.bucheng.springmybatis.entity.User;
import cn.bucheng.springmybatis.mapper.UserMapper;
import cn.bucheng.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Transactional
    @Override
    public List<User> listAll() {
        User user = new User();
        user.setName("test%");
        user.setAge(10);
        return userMapper.listAll(user);
    }
}
