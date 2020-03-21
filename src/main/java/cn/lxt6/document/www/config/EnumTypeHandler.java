package cn.lxt6.document.www.config;

import cn.lxt6.document.www.util.IEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzy
 * @description
 * @since 2020-03-21
 */
public class EnumTypeHandler<Enu extends IEnum<VType>, VType> extends BaseTypeHandler<Enu> {
    private Class<Enu> targetClass;

    private Map<VType, Enu> map = new HashMap<>();

    public EnumTypeHandler(Class<Enu> targetClass) {

        if (targetClass == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.targetClass = targetClass;
        Enu[] enums = targetClass.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(targetClass.getSimpleName() + " does not represent an enum type.");
        }
        for (Enu e : enums) {
            map.put(e.getValue(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Enu parameter, JdbcType jdbcType) throws SQLException {
        Object value =parameter.getValue();
        if (value instanceof Integer){
            ps.setInt(i, (Integer) value);
        }else if (value instanceof String) {
            ps.setString(i, parameter.getValue().toString());
        }
    }

    @Override
    public Enu getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object data = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    @Override
    public Enu getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object data = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    @Override
    public Enu getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object data = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    private Enu getEnum(Object value) {
        try {
            return map.get(value);
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Cannot convert " + value + " to " + targetClass.getSimpleName() + " by value.", ex);
        }
    }
}
