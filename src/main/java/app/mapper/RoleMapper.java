package app.mapper;

import java.util.List;

import app.model.RoleModel;

public interface RoleMapper {
    public int insRole(RoleModel roleModel);
    public int delRoleByPk(RoleModel roleModel);
    public int updRoleByPk(RoleModel roleModel);
    
    public RoleModel selRoleByPk(RoleModel roleModel);
    public List<RoleModel> selRoleByCondition(RoleModel roleModel);
}
