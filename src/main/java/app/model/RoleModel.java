package app.model;

import org.lechisoft.minifw.log.MiniLog;
import org.lechisoft.minifw.mybaits.Paging;

    public class RoleModel implements Cloneable {
    
    public RoleModel clone() {
        RoleModel o = null;
        try {
            o = (RoleModel) super.clone();
        } catch (CloneNotSupportedException e) {
            MiniLog.error("clone roleModel failed.", e);
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
    
    private String roleId = ""; // 角色编号
    private String roleName = ""; // 角色名称
    private int sort = 0; // 排序
    private String permissions = ""; // 权限
    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public int getSort() {
        return this.sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    public String getPermissions() {
        return this.permissions;
    }
    
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    
}
