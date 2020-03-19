package cn.lxt6.document.www.config.jsonConfig;


import cn.lxt6.document.www.util.EnumUtil;
import cn.lxt6.document.www.util.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

/**
 * 反序列化
 * json->枚举
 * @author chenzy
 * @date 2019.12.18
 */
public class EnumConvertFactory  implements ConverterFactory<String, IEnum> {

    @Override
    public <EN extends IEnum> Converter<String, EN> getConverter(Class<EN> targetType) {
        return new StringToIEum<>(targetType);
    }
    private class StringToIEum<EN extends IEnum> implements Converter<String, EN> {
        Class<EN> targerType;
        StringToIEum(Class<EN> targerType) {
            this.targerType = targerType;
        }

        @Override
        public EN convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return EnumUtil.getIEnum(this.targerType, source);
        }
    }

}
