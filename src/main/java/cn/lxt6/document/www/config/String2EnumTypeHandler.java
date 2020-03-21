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
public class String2EnumTypeHandler<Enu extends IEnum<String>> extends BaseTypeHandler<Enu> {
    private Class<Enu> targetClass;

    private Map<String, Enu> map = new HashMap<>();

    public String2EnumTypeHandler(Class<Enu> targetClass) {

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
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Enu getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String data = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    @Override
    public Enu getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String data = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    @Override
    public Enu getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String data = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    private Enu getEnum(String value) {
        try {
            return map.get(value);
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Cannot convert " + value + " to " + targetClass.getSimpleName() + " by value.", ex);
        }
    }
}
