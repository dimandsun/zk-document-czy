package cn.lxt6.document.www.config.dbConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * mybatis配置
 * @author chenzy
 * @date 2019.12.24
 */
@MapperScan(basePackages = "cn.lxt6.document.www.dao")//mapper所在文件夹
@Configuration
public class MybatisConfig {
    @Autowired
    private DynamicDataSource dataSource;

    @Bean(name="sessionFactory")
    public SqlSessionFactory sessionFactory() throws Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactoryBean.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapperLocations")));    //*Mapper.xml位置
        return sessionFactoryBean.getObject();
    }
}
