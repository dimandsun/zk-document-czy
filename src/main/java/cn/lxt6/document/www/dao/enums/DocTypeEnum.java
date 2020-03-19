package cn.lxt6.document.www.dao.enums;

import cn.lxt6.document.www.util.IEnum;
import cn.lxt6.document.www.util.StringUtil;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 文档类型
 */
public enum DocTypeEnum implements IEnum<Integer> {
    TableStructure(1,"数据库表结构"),Post(2,"post请求接口"),Get(3,"get请求接口"),Delete(4,"delete请求接口"),Put(5,"put请求接口");

    private Integer id;
    private String msg;

    public static DocTypeEnum getDocType(String msg){
        if (StringUtil.isBlank(msg)){
            return null;
        }
        for (DocTypeEnum docTypeEnum:DocTypeEnum.values()){
            if (docTypeEnum.msg.contains(msg)){
                return docTypeEnum;
            }
        }
        return null;
    }
    DocTypeEnum(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    @Override
    public Integer getValue() {
        return id;
    }
}
