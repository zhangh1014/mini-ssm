package app.model;

import org.lechisoft.minifw.log.MiniLog;
import org.lechisoft.minifw.mybaits.Paging;

    public class MenuModel implements Cloneable {
    
    public MenuModel clone() {
        MenuModel o = null;
        try {
            o = (MenuModel) super.clone();
        } catch (CloneNotSupportedException e) {
            MiniLog.error("clone menuModel failed.", e);
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
    
    private String parentMenuId = ""; // 父菜单编号
    private String menuId = ""; // 菜单编号
    private String menuName = ""; // 菜单名称
    private String menuLevel = ""; // 层级
    private String url = ""; // 链接地址
    private String icon = ""; // 图标名
    private int sort = 0; // 排序
    private String remarks = ""; // 备注
    private String roles = ""; // 角色
    private String permissions = ""; // 权限
    public String getParentMenuId() {
        return this.parentMenuId;
    }
    
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
    
    public String getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    public String getMenuName() {
        return this.menuName;
    }
    
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    public String getMenuLevel() {
        return this.menuLevel;
    }
    
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public int getSort() {
        return this.sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getRoles() {
        return this.roles;
    }
    
    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    public String getPermissions() {
        return this.permissions;
    }
    
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    
}
