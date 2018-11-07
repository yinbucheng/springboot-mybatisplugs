package cn.bucheng.springmybatis.mapper;

import cn.bucheng.springmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int save(User user);
    List<User> listAll();
}
