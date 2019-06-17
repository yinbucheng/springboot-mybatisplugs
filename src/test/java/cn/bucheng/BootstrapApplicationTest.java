package cn.bucheng;

import cn.bucheng.authmanager.dao.UserMapper;
import cn.bucheng.authmanager.model.po.UserPO;
import cn.bucheng.authmanager.service.UserService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.catalina.User;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedList;
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
        UserPO entity = new UserPO();
        entity.setAge(100);
        entity.setGender("nan");
        entity.setName("yinchong");
        entity.setRemark("test");
        userMapper.insert(entity);
    }

    @Test
    public void testBatchSaveUser() {
        for (int i = 0; i < 100; i++) {
            UserPO entity = new UserPO();
            entity.setAge(i);
            entity.setGender("nan");
            entity.setName("xiaoming");
            entity.setRemark("test");
            userMapper.insert(entity);
        }
    }

    @Test
    public void testFindOneUser() {
        UserPO entity = userMapper.selectById(1);
        System.out.println(entity);
    }

    @Test
    public void testUpdateUser() {
        UserPO entity = new UserPO();
        entity.setId(1L);
        entity.setName("yinchong");
        entity.setGender("nv");
        entity.setAge(20);
        userMapper.updateById(entity);
    }

    @Test
    public void testUpdateWrapperUser() {
        UserPO entity = userMapper.selectById(1);
        entity.setName("xiaohong");
        Wrapper<UserPO> wrapper = new Condition().eq("id", 1L);
        userMapper.update(entity, wrapper);
    }

    @Test
    public void testUpdateLikeWrapperUser(){
        UserPO entity = new UserPO();
        entity.setGender("test");
        Wrapper<UserPO> wrapper = new Condition().like("name","xiaoming%");
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
        Wrapper<UserPO> wrapper = new Condition().like("name","xiaoming%");
        userService.selectPage(page, wrapper);
        System.out.println(page);
    }

    @Test
    public void testBatchSave(){
        List<UserPO> entities = new LinkedList<>();
        for(int i=0;i<100;i++){
            UserPO entity = new UserPO();
            entities.add(entity);
            entity.setGender("nv");
            entity.setName("xiaoqian"+i);
            entity.setAge(i);
        }
        userService.insertBatch(entities);
    }

    @Autowired
    private TransportClient client;

    @Test
    public void batchInsetEs() throws IOException {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for(int i=0;i<100;i++){
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("name", "test"+i)
                    .field("age", 20+i)
                    .field("date", "2016-6-23")
                    .field("country","wuhang")
                    .endObject();
            bulkRequestBuilder.add(client.prepareIndex("people","man").setSource(content));
        }
        bulkRequestBuilder.execute().actionGet();
    }


    @Test
    public void filterQuery(){

    }
}
