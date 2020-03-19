package cn.lxt6.document.www.config.dbConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Copyright©2017-2018 中卡科技 版权所有. All rights reserved.
 * @description 动态切换数据库工具
 * @version V1.0.0
 * @className DynamicDataSource
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2018/3/14
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected DataSourceEnum determineCurrentLookupKey() {
        return DataSourceHolder.get();
    }
}
