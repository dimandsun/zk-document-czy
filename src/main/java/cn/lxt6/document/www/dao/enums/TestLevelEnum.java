package cn.lxt6.document.www.dao.enums;

import cn.lxt6.document.www.util.IEnum;

/**
 * @author chenzy
 * @description
 * @since 2020-03-20
 */
public enum TestLevelEnum implements IEnum<Integer> {
    HighConcurrence(1,"高并发测试"),Normal(1,"普通业务测试");

    TestLevelEnum(Integer id, String msg) {
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
