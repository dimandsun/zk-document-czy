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
public class Integer2EnumTypeHandler<Enu extends IEnum<Integer>> extends BaseTypeHandler<Enu> {
    private Class<Enu> targetClass;

    private Map<Integer, Enu> map = new HashMap<>();

    public Integer2EnumTypeHandler(Class<Enu> targetClass) {

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
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public Enu getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer data = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    @Override
    public Enu getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer data = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    @Override
    public Enu getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer data = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getEnum(data);
        }
    }

    private Enu getEnum(Integer value) {
        try {
            return map.get(value);
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Cannot convert " + value + " to " + targetClass.getSimpleName() + " by value.", ex);
        }
    }
}
