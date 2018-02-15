package app.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lechisoft.minifw.security.RealmData;
import org.lechisoft.minifw.security.User;
import org.springframework.beans.factory.annotation.Autowired;

import app.mapper.LoginUserMapper;
import app.mapper.RoleMapper;
import app.model.LoginUserModel;
import app.model.RoleModel;

public class DbRealmData implements RealmData {

	@Autowired
	private LoginUserMapper loginUserMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public User getUser(String userName) {

		// get login user by name
		LoginUserModel loginUser = new LoginUserModel();
		loginUser.setLoginName(userName);
		loginUser = loginUserMapper.selLoginUserByPk(loginUser);

		if (null == loginUser) {
			return null;
		}

		User user = new User();
		user.setUserName(loginUser.getLoginName());
		user.setPassword(loginUser.getPassword());
		user.setSalt(loginUser.getSalt());
		return user;
	}

	@Override
	public List<String> getRoles(String userName) {
		List<String> roles = new ArrayList<String>();

		// get login user by name
		LoginUserModel loginUser = new LoginUserModel();
		loginUser.setLoginName(userName);
		loginUser = loginUserMapper.selLoginUserByPk(loginUser);

		if (null != loginUser) {
			// get roles string
			String strRoles = loginUser.getRoles();
			if (null != strRoles && !"".equals(strRoles)) {
				roles.addAll(Arrays.asList(strRoles.split(",")));
			}
		}
		return roles;
	}

	@Override
	public List<String> getPermissions(String userName) {
		List<String> permissions = new ArrayList<String>();

		// get login user by name
		LoginUserModel loginUser = new LoginUserModel();
		loginUser.setLoginName(userName);
		loginUser = loginUserMapper.selLoginUserByPk(loginUser);

		if (null != loginUser) {
			// get roles string
			String strRoles = loginUser.getRoles();
			if (null != strRoles && !"".equals(strRoles)) {
				for (String strRole : strRoles.split(",")) {

					// get role model
					RoleModel role = new RoleModel();
					role.setRoleId(strRole);
					role = roleMapper.selRoleByPk(role);

					if (null != role) {
						// get permissions string
						String strPermissions = role.getPermissions();
						if (null != strPermissions && !"".equals(strPermissions)) {
							permissions.addAll(Arrays.asList(strPermissions.split(",")));
						}
					}
				}
			}
		}
		return permissions;
	}
}
