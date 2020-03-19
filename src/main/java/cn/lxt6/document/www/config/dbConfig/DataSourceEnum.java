package cn.lxt6.document.www.config.dbConfig;

/**
 * 数据源枚举
 * @author chenzy
 * @date 2019.12.25
 */
public enum DataSourceEnum {

    CZY("mysqlCZYTest",""),
    ORDER("pgsqlOrderData",""),
    ISV_ORDER("pgsqlIsOrderData",""),
    CHANNEL("pgsqlChannel",""),
    DEFAULT("pgsqlDevice",""),;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    DataSourceEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
