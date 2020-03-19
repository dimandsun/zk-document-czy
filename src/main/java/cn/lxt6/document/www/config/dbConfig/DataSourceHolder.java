package cn.lxt6.document.www.config.dbConfig;


/**
 * 设置当前线程的变量的工具类，用于设置对应的数据源名称
 * @author chenzy
 * @date 2019.12.25
 */
public class DataSourceHolder {

    private static final ThreadLocal<DataSourceEnum> curDataSource = new ThreadLocal<>();

    /**
     * @description 设置数据源
     */
    public static void set(DataSourceEnum dataSourceEnum) {
        curDataSource.set(dataSourceEnum);
    }

    /**
     * @description 获取数据源
     */
    public static DataSourceEnum get() {
        return curDataSource.get();
    }


    /**
     * @description 清除数据源
     */
    public static void clear() {
        curDataSource.remove();
    }
}
