package cn.bucheng.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：yinchong
 * @create ：2019/6/18 19:16
 * @description：mongodb测试代码
 * @modified By：
 * @version:
 */
public class MongodbTest {

    @Autowired
    private MongoClient mongo;

    @Before
    public void init(){
        mongo =  new MongoClient("localhost",27017);
    }


    @Test
    public void testCreateDatabase(){
        mongo.getDatabase("test");
    }

    @Test
    public void testCreateCollection(){
        mongo.getDatabase("test").createCollection("book");
    }

    @Test
    public void testFindCollection(){
        FindIterable<Document> documents = mongo.getDatabase("test").getCollection("book").find();
    }

    @Test
    public void testSaveCollection(){
        Document document = new Document();
        document.put("name","yinbucheng");
        document.put("age",20);
        document.put("gender","nan");
        mongo.getDatabase("test").getCollection("book").insertOne(document);
    }
}
