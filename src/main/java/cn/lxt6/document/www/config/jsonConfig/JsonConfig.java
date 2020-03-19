package cn.lxt6.document.www.config.jsonConfig;


import cn.lxt6.document.www.util.IEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

/**
 * 配置 使用springboot的json-model转换功能
 *
 * @author chenzy
 * @2019.12.25
 */
@Configuration
public class JsonConfig implements WebMvcConfigurer {
    /***
     * springboot自动转换时使用。
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConvertFactory());
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomizer() {
        return m -> {
            // 序列化 对象-》jsonString
            m.serializerByType(IEnum.class, new EnumSerializer());
            m.serializerByType(Date.class, new DateSerializer());
            m.serializerByType(Boolean.class, new BooleanSerializer());
            m.serializationInclusion(JsonInclude.Include.NON_NULL);//过滤null
            //反序列化 jsonString-》对象
            m.deserializerByType(Enum.class, new EnumDeserializer());//没有作用也加上
            m.deserializerByType(Date.class, new DateDeserializer());
            m.deserializerByType(Boolean.class, new BooleanDeserializer());
//            m.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        };
    }
}
