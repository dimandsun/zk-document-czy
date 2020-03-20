package cn.lxt6.document.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//禁用数据源默认自动配置
public class DocumentCzyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentCzyApplication.class, args);
    }

}
