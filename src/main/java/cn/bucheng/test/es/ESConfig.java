package cn.bucheng.test.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author buchengyin
 * @version v1.0.0
 * @create 2019/6/16 14:41
 * @describe es配置
 */
@Configuration
public class ESConfig {

    @Bean
    public TransportClient client() {
        TransportAddress node = new TransportAddress(
                new InetSocketAddress("localhost", 9300)
        );
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
