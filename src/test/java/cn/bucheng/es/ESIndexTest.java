package cn.bucheng.es;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author ：yinchong
 * @create ：2019/6/18 19:19
 * @description：索引常用操作
 * @modified By：
 * @version:
 */
public class ESIndexTest {

    private TransportClient client;

    @Before
    public void before() {
        TransportAddress node = new TransportAddress(
                new InetSocketAddress("localhost", 9300)
        );
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        this.client = client.addTransportAddress(node);
    }

    //这里有点问题 https://blog.csdn.net/u014039577/article/details/52439766
    @Test
    public void createIndex() throws IOException {

        IndicesAdminClient indices = client.admin().indices();
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject().field("name", "{\"type\":\"text\"}").endObject();
        CreateIndexResponse createIndexResponse = indices.prepareCreate("ifaas-cloud").setSettings(Settings.builder().put("index.number_of_replicas", 2).put("index.number_of_shards", 2))
                .addMapping("cmp", contentBuilder).get();
        System.out.println(createIndexResponse);
    }

    @Test
    public void testAliasIndex(){
        client.admin().indices().prepareAliases().addAlias("people","test_people");
    }

    @Test
    public void testRemoveAliases(){
        client.admin().indices().prepareAliases().removeAlias("people","test_people");
    }
}
