package cn.lxt6.document.www.dao.enums;

import cn.lxt6.document.www.util.IEnum;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 参数类型：1 请求参数，2 请求返回数据，3 数据库表字段
 */
public enum ParTypeEnum implements IEnum<Integer> {
    QuestPar(1,"请求参数"),QuestReturn(2,"请求返回数据"),DBColumn(3,"数据库表字段");

    ParTypeEnum(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    private Integer id;
    private String msg;
    @Override
    public Integer getValue() {
        return id;
    }
}
