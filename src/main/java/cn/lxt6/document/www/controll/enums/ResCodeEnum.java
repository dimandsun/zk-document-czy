package cn.lxt6.document.www.controll.enums;


import cn.lxt6.document.www.util.IEnum;

/**
 * 响应码
 * <p>
 * -1到-100 系统后台分配值
 * -101到-200 APP/小程序使用值
 * -301到-400 公用CODE值
 * CODE=0时，正常操作
 * CODE>0时，控制流程值
 * <p>
 * 新增 SecretKeyError，UnknownExce
 * @author chenzy
 * @date 2019.12.16
 */
public enum ResCodeEnum implements IEnum<String> {
    /*************************系统后台code*******************************************/
    DBError("-1", ResCodeLevelEnum.Lev1, "数据库系统错误"), EncrySerError("-2", ResCodeLevelEnum.Lev1, "加密授权服务错误")
    , UserSerError("-3", ResCodeLevelEnum.Lev1, "用户管理中心错误"), DeviceSerError("-4", ResCodeLevelEnum.Lev1, "设备管理中心错误")
    , NBSerError("-5", ResCodeLevelEnum.Lev1, "NB服务中心错误"), LoginError("-6", ResCodeLevelEnum.Lev1, "用户登录接口错误")
    , PartnerError("-7", ResCodeLevelEnum.Lev1, "合作方系统错误"), ForceLogout1("-44", ResCodeLevelEnum.SysProtect, "强制下线")//系统保护，需要客户端强制用户下线，重新登录
    , ForceLogout2("1001", ResCodeLevelEnum.SysProtect, "强制下线")//系统保护，需要客户端强制用户下线，重新登录
    , ForceLogout3("9999", ResCodeLevelEnum.SysProtect, "强制下线")//系统保护，需要客户端强制用户下线，重新登录

    , BusInfo("-45", ResCodeLevelEnum.Info, "业务提示"), ManageDeviceWarn("-46", ResCodeLevelEnum.Warn, "提醒：非正常管理设备")
    , SysDataExce("-47", ResCodeLevelEnum.Lev3, "系统数据异常"), OfflineUse("-48", ResCodeLevelEnum.SysProtect, "脱机使用")//系统保护，需脱机使用设备

    , SecretKeyError("-49", ResCodeLevelEnum.Lev3, "密钥错误")//数据验证时加密信息错误

    /*********************app或小程序code**********************************************************/
    , NOPackBackendData("-101", ResCodeLevelEnum.Lev2, "不能封装后台数据"), NoCorrectBackendData("-102", ResCodeLevelEnum.Lev3, "后端数据不正确")
    , APPGrantWarn("-103", ResCodeLevelEnum.Warn, "应用授权提醒"), BluetoothMismatch("-104", ResCodeLevelEnum.Warn, "蓝牙不适配")
    , BluetoothSignalWeak("-105", ResCodeLevelEnum.Warn, "蓝牙讯号弱"), BluetoothSignaExce("-106", ResCodeLevelEnum.Warn, "蓝牙通信异常")


    /*********************************各系统公用code**********************************************************/
    , ArgAnalyExce("-301", ResCodeLevelEnum.Debug, "参数解析异常"), ArgEmpty("-302", ResCodeLevelEnum.Debug, "参数为空")
    , ArgNoCorrect("-303", ResCodeLevelEnum.Debug, "参数值不正确"), BusInExce("-304", ResCodeLevelEnum.Debug, "内部业务异常")
    , UnknownExce("-305", ResCodeLevelEnum.Debug, "未知异常")
    , UnknownCodeExce("-306", ResCodeLevelEnum.Debug, "未知响应码")
    , Normal("0", ResCodeLevelEnum.Normal, "成功")//正常，不过是返回成功两个汉字而已
    , NotFound("404",ResCodeLevelEnum.Lev3,"找不到相关资源")
    , DBExce("500",ResCodeLevelEnum.Lev3,"数据库异常")
    , NetExce("501",ResCodeLevelEnum.Lev3,".net错误")
    , CashExce("502",ResCodeLevelEnum.Lev3,"缓存出错")
    , DuplicateInsert("503",ResCodeLevelEnum.Lev3,"重复添加")
    ,PayUserExce("7501",ResCodeLevelEnum.Info,"判断账单用户信息失败")
    , AlipaySigned("10001", ResCodeLevelEnum.Process, "支付宝已签约")//不同业务含义不一样，AlipayIsvTradePay时表示调起免密失败
    , PayFail("10002",ResCodeLevelEnum.Info,"支付失败")
    , AlipayUnsigned("10003", ResCodeLevelEnum.Process, "支付宝未签约")
    , PayFailNoBill("10004",ResCodeLevelEnum.Info,"账单不存在,支付失败")
    , FuncNoOpen("4000",ResCodeLevelEnum.Info,"功能暂未开放")
    , AlipayStatus("4115", ResCodeLevelEnum.Process, "订单支付状态")//PayType 1 代扣，2 钱包，3 单笔
    , NoReserve("4133",ResCodeLevelEnum.Process,"无法预约信息")
    , BathhouseBindStatus("4005", ResCodeLevelEnum.Process, "用户已绑定澡堂状态")//ISV对接澡堂
    , NoFoundUser("101", ResCodeLevelEnum.Process, "找不到用户信息")//用户管理中心缓存中心使用
    , NoCardInfo("102", ResCodeLevelEnum.Process, "找不到卡片信息")//卡片管理中心缓存中心使用
    ,
    ;

    public boolean isNotNormal() {
        return !isNormal();
    }
    public boolean isNormal() {
        return this.codeLevelEnum.equals(ResCodeLevelEnum.Normal);
    }

    ResCodeEnum(String code, ResCodeLevelEnum codeLevelEnum, String msg) {
        this.code = code;
        this.codeLevelEnum = codeLevelEnum;
        this.msg = msg;
    }

    private String code;
    private String msg;
    private ResCodeLevelEnum codeLevelEnum;

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public String getValue() {
        return this.code;
    }

    public String getMSG() {
        return this.msg;
    }
}

