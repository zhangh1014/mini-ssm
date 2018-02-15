package app.mapper;

import java.util.List;

import app.model.LoginUserModel;

public interface LoginUserMapper {
    public int insLoginUser(LoginUserModel loginUserModel);
    public int delLoginUserByPk(LoginUserModel loginUserModel);
    public int updLoginUserByPk(LoginUserModel loginUserModel);
    
    public LoginUserModel selLoginUserByPk(LoginUserModel loginUserModel);
    public List<LoginUserModel> selLoginUserByCondition(LoginUserModel loginUserModel);
}
