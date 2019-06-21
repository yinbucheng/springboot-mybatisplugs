package cn.bucheng.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
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
    private MongoClient client;

    @Before
    public void init(){
        client =  new MongoClient("localhost",27017);
    }


    @Test
    public void testCreateDatabase(){
        client.getDatabase("test");
    }

    @Test
    public void testCreateCollection(){
        client.getDatabase("test").createCollection("book");
    }

    @Test
    public void testFindCollection(){
        FindIterable<Document> documents = client.getDatabase("test").getCollection("book").find();
    }

    @Test
    public void testSaveCollection(){
        Document document = new Document();
        document.put("name","yinbucheng");
        document.put("age",20);
        document.put("gender","nan");
        client.getDatabase("test").getCollection("book").insertOne(document);
    }

    @Test
    public void testUpdate(){
        Bson eq = Filters.eq("name", "yinchong");
        Document document = new Document("$set", new Document("age", 30));
        client.getDatabase("test").getCollection("book").updateMany(eq,document);
    }

    @Test
    public void testDelete(){
        client.getDatabase("test").getCollection("book").deleteMany(Filters.eq("name","yinchong"));
    }


}
