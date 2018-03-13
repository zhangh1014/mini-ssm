package app.model;

import org.lechisoft.minifw.log.MiniLog;
import org.lechisoft.minifw.mybaits.Paging;

    public class AccountModel implements Cloneable {
    
    public AccountModel clone() {
        AccountModel o = null;
        try {
            o = (AccountModel) super.clone();
        } catch (CloneNotSupportedException e) {
            MiniLog.error("clone accountModel failed.", e);
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
    
    private String userId = ""; // 用户编码
    private String account = ""; // 账户
    private String password = ""; // 登录密码
    private String salt = ""; // 盐值
    private String roles = ""; // 角色
    private String remarks = ""; // 备注
    private String field01 = ""; // 预留字段1
    private String field02 = ""; // 预留字段2
    private String field03 = ""; // 预留字段3
    private String field04 = ""; // 预留字段4
    private String field05 = ""; // 预留字段5
    private String createAccount = ""; // 创建者
    private String createDate = ""; // 创建日期
    private String createTime = ""; // 创建时间
    private String updateAccount = ""; // 更新者
    private String updateDate = ""; // 更新日期
    private String updateTime = ""; // 更新时间
    private String disabledFlg = ""; // 禁用标志
    private String deleteFlg = ""; // 删除标志
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
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
    
    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getField01() {
        return this.field01;
    }
    
    public void setField01(String field01) {
        this.field01 = field01;
    }
    
    public String getField02() {
        return this.field02;
    }
    
    public void setField02(String field02) {
        this.field02 = field02;
    }
    
    public String getField03() {
        return this.field03;
    }
    
    public void setField03(String field03) {
        this.field03 = field03;
    }
    
    public String getField04() {
        return this.field04;
    }
    
    public void setField04(String field04) {
        this.field04 = field04;
    }
    
    public String getField05() {
        return this.field05;
    }
    
    public void setField05(String field05) {
        this.field05 = field05;
    }
    
    public String getCreateAccount() {
        return this.createAccount;
    }
    
    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }
    
    public String getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getUpdateAccount() {
        return this.updateAccount;
    }
    
    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount;
    }
    
    public String getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    
    public String getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getDisabledFlg() {
        return this.disabledFlg;
    }
    
    public void setDisabledFlg(String disabledFlg) {
        this.disabledFlg = disabledFlg;
    }
    
    public String getDeleteFlg() {
        return this.deleteFlg;
    }
    
    public void setDeleteFlg(String deleteFlg) {
        this.deleteFlg = deleteFlg;
    }
    
}
