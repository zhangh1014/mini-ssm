package app.service;

import java.util.ArrayList;
import java.util.List;

import org.lechisoft.minifw.security.MiniSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.conf.exception.ServiceException;
import app.mapper.MenuMapper;
import app.model.MenuModel;

@Component
@Transactional(rollbackFor = { ServiceException.class, Exception.class })
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	public List<MenuModel> getMyMenu() {
		List<MenuModel> rtnMenus = new ArrayList<MenuModel>();

		List<MenuModel> menus = menuMapper.selAllMenu();
		for (MenuModel menu : menus) {

			// has role
			if (!"".equals(menu.getRoles())) {
				if (MiniSecurity.hasRole("admin")) {
					rtnMenus.add(menu);
					continue;
				}
			}

			// has permission
			if (!"".equals(menu.getPermissions())) {
				if (MiniSecurity.isPermittedAny(menu.getPermissions().split(","))) {
					rtnMenus.add(menu);
					continue;
				}
			}
		}

		return rtnMenus;
	}
}
