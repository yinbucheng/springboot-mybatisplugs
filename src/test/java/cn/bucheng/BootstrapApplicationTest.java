package cn.bucheng;

import cn.bucheng.dao.UserMapper;
import cn.bucheng.domain.UserEntity;
import cn.bucheng.service.UserService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapApplicationTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        UserEntity entity = new UserEntity();
        entity.setAge(100);
        entity.setGender("nan");
        entity.setName("yinchong");
        entity.setRemark("test");
        userMapper.insert(entity);
    }

    @Test
    public void testBatchSaveUser() {
        for (int i = 0; i < 100; i++) {
            UserEntity entity = new UserEntity();
            entity.setAge(i);
            entity.setGender("nan");
            entity.setName("xiaoming");
            entity.setRemark("test");
            userMapper.insert(entity);
        }
    }

    @Test
    public void testFindOneUser() {
        UserEntity entity = userMapper.selectById(1);
        System.out.println(entity);
    }

    @Test
    public void testUpdateUser() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("yinchong");
        entity.setGender("nv");
        entity.setAge(20);
        userMapper.updateById(entity);
    }

    @Test
    public void testUpdateWrapperUser() {
        UserEntity entity = userMapper.selectById(1);
        entity.setName("xiaohong");
        Wrapper<UserEntity> wrapper = new Condition().eq("id", 1L);
        userMapper.update(entity, wrapper);
    }

    @Test
    public void testUpdateLikeWrapperUser(){
        UserEntity entity = new UserEntity();
        entity.setGender("test");
        Wrapper<UserEntity> wrapper = new Condition().like("name","xiaoming%");
        userMapper.update(entity,wrapper);
    }


    @Test
    public void testSelectPageUser(){
        Pagination pagination = new Pagination(1,10);
        List<User> users = userMapper.listPage(pagination, "yinchong");
        System.out.println(users);
    }


    @Test
    public void testUserService(){
        Page page = new Page(1,10);
        Wrapper<UserEntity> wrapper = new Condition().like("name","xiaoming%");
        userService.selectPage(page, wrapper);
        System.out.println(page);
    }
}
