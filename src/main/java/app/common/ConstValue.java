package app.common;

public class ConstValue {
    public static final String REQUEST_MESSAGE_KEY = "message";
    public static final String SESSION_MESSAGE_MAP_KEY = "messageMap";
    
    public static final String REQUEST_ERROR_CODE_KEY = "errorCode";
    
    public static final String EXCEPTION_MESSAGE_KEY = "exceptionMessage";
    public static final String EXCEPTION_STACK_TRACE_KEY = "exceptionStackTrace";
    
    public static final String SESSION_LOGIN_OBJECT_KEY = "loginObject";
    
    public static final String ORG_CD = "wdm";
    
 // 订单状态
    public static class OrderStatus {
        public static final String UNDONE = "01"; // 未完成
        public static final String COMPLETE = "09"; // 已完成
    }
    // 删除标志
    public static class DelectFlg {
        public static final String NORMAL = "0"; // 未删除
        public static final String DELETE = "1"; // 删除
    }

    // 机构类型
    public static class OrgType {
        public static final String COMPANY = "01"; // 自公司
        public static final String SCHOOL = "02"; // 学校
        public static final String HOSPITAL = "03"; // 医院
        public static final String KF_CENTER = "04"; // 康复中心
        public static final String ZJ_CENTER = "05"; // 支具中心
    }

    // 人员类型
    public static class RelativeType {
        public static final String SALES = "01"; // 销售
        public static final String SERVICE = "02"; // 客服
    }

    // 预约状态
    public static class ReserveStatus {
        public static final String CONFIRM_NO = "01"; // 未确认
        public static final String CONFIRM_OK = "02"; // 已确认
        public static final String ACCEPT_OK = "03"; // 已接收
        public static final String CHECK_OK = "04"; // 已检查
    }

    // 性别
    public static class Sex {
        public static final String MALE = "01"; // 男
        public static final String FEMALE = "02"; // 女
       
    }

    // 使用标志
    public static class UseFlag {
        public static final String DISABLE = "0"; // 禁用
        public static final String USING = "1"; // 启用
    }
    
}


