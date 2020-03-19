package cn.lxt6.document.www.config.dbConfig;

import java.lang.annotation.*;

/**
 * 注解式切换数据源，配合切面DataSourceAspect使用
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyDataSource {
    DataSourceEnum value() default DataSourceEnum.DEFAULT;
}
