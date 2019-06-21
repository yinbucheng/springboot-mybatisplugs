package cn.bucheng.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：yinchong
 * @create ：2019/6/18 19:16
 * @description：mongodb测试代码
 * @modified By：
 * @version:
 */
public class MongodbTest {
    public final static String DBNAME = "test";
    public final static String COLLECTION ="book";

    @Autowired
    private MongoClient client;

    @Before
    public void init(){
        client =  new MongoClient("localhost",27017);
    }


    @Test
    public void testCreateDatabase(){
        MongoDatabase database = client.getDatabase(DBNAME);
        System.out.println(database);
    }

    @Test
    public void testCreateCollection(){
        client.getDatabase(DBNAME).createCollection(COLLECTION);
    }

    @Test
    public void testListCollections(){
        MongoIterable<String> collections = client.getDatabase(DBNAME).listCollectionNames();
        MongoCursor<String> iterator = collections.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void testSaveCollectionData(){
        Document document = new Document();
        document.put("name","yinbucheng");
        document.put("age",20);
        document.put("gender","nan");
        client.getDatabase(DBNAME).getCollection(COLLECTION).insertOne(document);
    }

    @Test
    public void batchSaveCollectionData(){
        List<Document> datas = new LinkedList<>();
        for(int i=0;i<1000;i++){
            Document document = new Document();
            document.put("name","yinchong"+i);
            document.put("age",i);
            document.put("gender","nan");
            datas.add(document);
        }
        client.getDatabase(DBNAME).getCollection(COLLECTION).insertMany(datas);
    }



    @Test
    public void testListCollectionData(){
        FindIterable<Document> documents = client.getDatabase(DBNAME).getCollection(COLLECTION).find();
        MongoCursor<Document> iterator = documents.iterator();
        while(iterator.hasNext()){
            Document next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void testListEqCollectionData(){
        BasicDBObject query = new BasicDBObject();
        query.put("age",20);
        FindIterable<Document> documents = client.getDatabase(DBNAME).getCollection(COLLECTION).find(query);
        MongoCursor<Document> iterator = documents.iterator();
        while(iterator.hasNext()){
            Document next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void testListLikeCollectionData(){
        BasicDBObject query = new BasicDBObject();
        query.put("age",new BasicDBObject("$in", Arrays.asList(10,20,40)));
        FindIterable<Document> documents = client.getDatabase(DBNAME).getCollection(COLLECTION).find(query);
        MongoCursor<Document> iterator = documents.iterator();
        while(iterator.hasNext()){
            Document next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void testUpdate(){
        Bson eq = Filters.eq("name", "yinchong");
        Document document = new Document("$set", new Document("age", 30));
        client.getDatabase(DBNAME).getCollection(COLLECTION).updateMany(eq,document);
    }

    @Test
    public void testDelete(){
        client.getDatabase(DBNAME).getCollection(COLLECTION).deleteMany(Filters.eq("name","yinchong"));
    }


}
