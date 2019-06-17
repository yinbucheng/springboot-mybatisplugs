package cn.bucheng.authmanager.service;

import cn.bucheng.authmanager.dao.UserMapper;
import cn.bucheng.authmanager.model.po.UserPO;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author buchengyin
 * @Date 2019/5/20 9:13
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService  {
}
