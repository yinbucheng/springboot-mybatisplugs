package cn.bucheng.authmanager.dao;

import cn.bucheng.authmanager.model.po.UserPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author buchengyin
 * @Date 2019/5/20 9:10
 **/
public interface UserMapper extends BaseMapper<UserPO> {

    List<User> listPage(Pagination page, @Param("name")String name);
}
