package app.mapper;

import java.util.List;

import app.model.MenuModel;

public interface MenuMapper {
    public int insMenu(MenuModel menuModel);
    public int delMenuByPk(MenuModel menuModel);
    public int updMenuByPk(MenuModel menuModel);
    
    public MenuModel selMenuByPk(MenuModel menuModel);
    public List<MenuModel> selMenuByCondition(MenuModel menuModel);
    
    public List<MenuModel> selAllMenu();
}
