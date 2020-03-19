package cn.lxt6.document.www.config.dbConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;

import static cn.lxt6.document.www.config.dbConfig.DataSourceEnum.*;

/**
 * 数据源配置
 * @author chenzy
 * @date 2019.12.24
 */
@Configuration
public class DataSourceConfig implements WebMvcConfigurer {



    @ConfigurationProperties(prefix = "spring.datasource.mysql-czy-test")
    @Bean
    public DataSource mysqlCZYTest() {
        return new DruidDataSource();
    }

    /*@ConfigurationProperties(prefix = "spring.datasource.pgsql-order-data")
    @Bean
    public DataSource pgsqlOrderData() {
        return new DruidDataSource();
    }

    @ConfigurationProperties(prefix = "spring.datasource.pgsql-isv-order-data")
    @Bean
    public DataSource pgsqlIsOrderData() {
        return new DruidDataSource();
    }
    @ConfigurationProperties(prefix = "spring.datasource.pgsql-channel")
    @Bean
    public DataSource pgsqlChannel() {
        return new DruidDataSource();
    }
    @ConfigurationProperties(prefix = "spring.datasource.pgsql-device")
    @Bean
    public DataSource pgsqlDevice() {
        return new DruidDataSource();
    }*/

   /* @Autowired
    private DataSource pgsqlDevice;
    @Autowired
    private DataSource pgsqlChannel;
    @Autowired
    private DataSource pgsqlIsOrderData;
    @Autowired
    private DataSource pgsqlOrderData;*/
    @Autowired
    private DataSource mysqlCZYTest;

    @Bean
    public DynamicDataSource dataSource(){
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(new HashMap<Object,Object>(){{
//            put(ORDER,pgsqlOrderData);
//            put(ISV_ORDER,pgsqlIsOrderData);
//            put(CHANNEL,pgsqlChannel);
            put(CZY,mysqlCZYTest);
//            put(DEFAULT,pgsqlDevice);
        }});
        dataSource.setDefaultTargetDataSource(mysqlCZYTest);
        return dataSource;
    }

    /**
     * 事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}
