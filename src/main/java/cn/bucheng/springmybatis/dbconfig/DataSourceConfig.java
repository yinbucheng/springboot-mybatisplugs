package cn.bucheng.springmybatis.dbconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    public final static String WRITE_DATASOURCE_KEY = "master";
    public final static String READ_DATASOURCE_KEY = "slave";

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean(name = READ_DATASOURCE_KEY)
    public DataSource readDruidDataSource() {
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean(name = WRITE_DATASOURCE_KEY)
    @Primary
    public DataSource writeDruidDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="routingDataSource")
//    @Primary
    public DataSource routingDataSource(
            @Qualifier(READ_DATASOURCE_KEY) DataSource readDruidDataSource,
            @Qualifier(WRITE_DATASOURCE_KEY) DataSource writeDruidDataSource) throws Exception {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(WRITE_DATASOURCE_KEY, writeDruidDataSource);
        targetDataSources.put(READ_DATASOURCE_KEY, readDruidDataSource);
        dataSource.setTargetDataSources(targetDataSources);// 配置数据源
        dataSource.setDefaultTargetDataSource(writeDruidDataSource);// 默认为主库用于写数据
         logger.info("----------------------------->routingDataSource");
        return dataSource;

    }
    
}