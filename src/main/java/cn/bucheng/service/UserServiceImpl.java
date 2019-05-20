package cn.bucheng.service;

import cn.bucheng.dao.UserMapper;
import cn.bucheng.domain.UserEntity;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author buchengyin
 * @Date 2019/5/20 9:13
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserEntity> implements UserService  {
}
