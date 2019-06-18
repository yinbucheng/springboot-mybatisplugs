package cn.bucheng.es;

import org.apache.lucene.search.Query;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author ：yinchong
 * @create ：2019/6/17 9:32
 * @description：e测试
 * @modified By：
 * @version:
 */
public class ESTest {
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


    @Test
    public void batchInsetEs() throws IOException {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject().field("name", "redis设计和实现").field("author", "尹步程")
                .field("push_date", "2018-6-13").endObject();
        bulkRequestBuilder.add(client.prepareIndex("book", "english").setSource(contentBuilder));

        XContentBuilder contentBuilder2 = XContentFactory.jsonBuilder().startObject().field("name", "mysql高级开发").field("name", "大神")
                .field("push_date", "2017-6-23").endObject();
        bulkRequestBuilder.add(client.prepareIndex("book", "english").setSource(contentBuilder2));
        bulkRequestBuilder.execute().actionGet();
    }

    @Test
    public void update() throws IOException, ExecutionException, InterruptedException {
        UpdateRequest updateRequest = new UpdateRequest("book","english","6_z0Z2sBfpw9UQlyqWSL");
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("name", "kafka深入架构详解").endObject();
        updateRequest.doc(builder);
        UpdateResponse updateResponse = client.update(updateRequest).get();
        System.out.println(updateRequest);
    }

    @Test
    public void delete() {
        DeleteResponse deleteResponse = client.prepareDelete("book", "english", "8_xAamsBfpw9UQlyi2Rp").get();
        System.out.println(deleteResponse);
    }

    @Test
    public void add() throws IOException {
        XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject().field("name", "solar入门到精通").field("author", "大神")
                .field("push_date", "2018-4-25").endObject();
        IndexResponse indexResponse = client.prepareIndex("book", "english").setSource(contentBuilder).get();
        System.out.println(indexResponse);
    }


    @Test
    public void query() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchPhraseQuery("name", "kafka"));
        System.out.println(boolQuery);
        SearchRequestBuilder builder = this.client.prepareSearch("book")
                .setTypes("english")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);

        SearchResponse response = builder.get();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : response.getHits()) {
            result.add(hit.getSourceAsMap());
        }
        System.out.println(result);
    }


    @Test
    public void listShould() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.should(QueryBuilders.matchQuery("name", "kafka"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("author", "尹冲"));
        System.out.println(boolQueryBuilder);
        SearchRequestBuilder builders = client.prepareSearch("book").setTypes("english").setQuery(boolQueryBuilder).setFrom(1).setSize(10);
        System.out.println("========================");
        System.out.println(builders.get());
    }

    @Test
    public void listMust() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", "kafka"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("author", "尹冲"));
        System.out.println(boolQueryBuilder);
        SearchRequestBuilder builder = client.prepareSearch("book").setTypes("english").setQuery(boolQueryBuilder);
        System.out.println(builder.get());
    }

    @Test
    public void listMatch() {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("author", "尹冲");
        System.out.println(matchQueryBuilder);
        SearchRequestBuilder builder = client.prepareSearch("book").setTypes("english").setQuery(matchQueryBuilder);
        System.out.println(builder.get());
    }

    @Test
    public void listMultiMatch() {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("尹冲", "name", "author");
        System.out.println(multiMatchQueryBuilder);
        SearchRequestBuilder builder = client.prepareSearch("book").setTypes("english").setQuery(multiMatchQueryBuilder);
        System.out.println(builder.get());
    }

    @Test
    public void listAll() {
        SearchRequestBuilder builder = client.prepareSearch("book").setTypes("english").setQuery(QueryBuilders.matchAllQuery());
        System.out.println(builder.get());
    }

    @Test
    public void range(){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("push_date").gte("2013-4-21").lte("2019-5-20"));
        SearchResponse searchResponse = client.prepareSearch("book").setTypes("english").setQuery(boolQueryBuilder).get();
        System.out.println(searchResponse);
    }
}
