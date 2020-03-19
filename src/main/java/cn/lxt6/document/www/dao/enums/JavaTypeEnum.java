package cn.lxt6.document.www.dao.enums;

import cn.lxt6.document.www.util.IEnum;
import cn.lxt6.document.www.util.StringUtil;


/**
 * @author chenzy
 * @description
 * @since 2020-03-18
 */
public enum JavaTypeEnum implements IEnum<String> {
    STRING("String", "char", null), INTEGER("Integer", "int", null)
    , DATE("Date", "date", "import java.util.Date;\n")
    , DOUBLE("Double", "double", null)
    , LONG("Long", "double", null);


    public static JavaTypeEnum getTypeByColumnType(String columnType) {
        if (StringUtil.isBlank(columnType)) {
            return null;
        }
        for (JavaTypeEnum typeEnum : JavaTypeEnum.values()) {
            if (columnType.contains(typeEnum.columnType)) {
                return typeEnum;
            }
        }
        return null;
    }
    public static JavaTypeEnum getTypeByJavaType(String javaType) {
        if (StringUtil.isBlank(javaType)) {
            return null;
        }
        for (JavaTypeEnum typeEnum : JavaTypeEnum.values()) {
            if (typeEnum.javaType.contains(javaType)) {
                return typeEnum;
            }
        }
        return null;
    }
    JavaTypeEnum(String javaType, String columnType, String importContent) {
        this.javaType = javaType;
        this.columnType = columnType;
        this.importContent = importContent;
    }

    private String javaType;
    private String columnType;
    private String importContent;

    public String getImportContent() {
        return importContent;
    }

    @Override
    public String getValue() {
        return this.javaType;
    }
}
