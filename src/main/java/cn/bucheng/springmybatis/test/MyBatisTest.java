package cn.bucheng.springmybatis.test;

import cn.bucheng.springmybatis.entity.User;
import cn.bucheng.springmybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) {
        String resource = "config/mybatis-config.xml";
        SqlSession sqlSession =null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<User> userList = userMapper.listAll(new User());
            System.out.println(userList);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if(sqlSession!=null)
            sqlSession.close();
        }
    }
}
