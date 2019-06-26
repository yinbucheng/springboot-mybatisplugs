package cn.bucheng.persister.dao;

import cn.bucheng.persister.po.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ：yinchong
 * @create ：2019/6/26 13:52
 * @description：持久层
 * @modified By：
 * @version:
 */
public interface AddressDao extends MongoRepository<Address,String> {
}
