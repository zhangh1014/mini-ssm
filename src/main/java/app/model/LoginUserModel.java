package app.model;

import org.lechisoft.minifw.log.MiniLog;
import org.lechisoft.minifw.mybaits.Paging;

    public class LoginUserModel implements Cloneable {
    
    public LoginUserModel clone() {
        LoginUserModel o = null;
        try {
            o = (LoginUserModel) super.clone();
        } catch (CloneNotSupportedException e) {
            MiniLog.error("clone loginUserModel failed.", e);
        }
        return o;
    }
    
    // 分页
    private Paging paging = new Paging();
    
    public Paging getPaging() {
        return paging;
    }
    public void setPaging(Paging paging) {
        this.paging = paging;
    }
    
    private String companyCd = ""; // 公司代码
    private String orgCd = ""; // 机构代码
    private String userCd = ""; // 用户代码
    private String loginName = ""; // 登录名称
    private String password = ""; // 登录密码
    private String salt = ""; // 盐值
    private String roles = ""; // 角色
    
    public String getCompanyCd() {
        return this.companyCd;
    }
    
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    public String getOrgCd() {
        return this.orgCd;
    }
    
    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }
    
    public String getUserCd() {
        return this.userCd;
    }
    
    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }
    
    public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt() {
        return this.salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getRoles() {
        return this.roles;
    }
    
    public void setRoles(String roles) {
        this.roles = roles;
    }
    
}
