package cn.lxt6.document.www.config.jsonConfig;


import cn.lxt6.document.www.util.IEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

/**
 * 序列化，将enum枚举转为json
 * @author chenzy
 * @date 2019.12.19
 */
public class EnumSerializer extends JsonSerializer<IEnum> {
    @Override
    public void serialize(IEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Optional<IEnum> data = Optional.of(value);
        if (data.isPresent()) {//非空
            gen.writeObject(data.get().getValue());
        } else {
//            gen.writeString("");
        }
    }

}
