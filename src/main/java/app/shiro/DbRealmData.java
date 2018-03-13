package app.shiro;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.lechisoft.minifw.log.MiniLog;
import org.lechisoft.minifw.security.MiniSecurity;
import org.lechisoft.minifw.security.RealmData;
import org.lechisoft.minifw.security.User;
import org.springframework.beans.factory.annotation.Autowired;

import app.conf.common.OrderProperties;
import app.mapper.AccountMapper;
import app.mapper.RoleMapper;
import app.model.AccountModel;
import app.model.RoleModel;

public class DbRealmData implements RealmData {

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public User getUser(String userName) {

		AccountModel account = new AccountModel();
		account.setAccount(userName);
		account = accountMapper.selAccountByPk(account);

		if (null == account) {
			return null;
		}

		User user = new User();
		user.setUserName(account.getAccount());
		user.setPassword(account.getPassword());
		user.setSalt(account.getSalt());
		return user;
	}

	@Override
	public List<String> getRoles(String userName) {
		List<String> roles = new ArrayList<String>();

		AccountModel account = new AccountModel();
		account.setAccount(userName);
		account = accountMapper.selAccountByPk(account);

		if (null != account) {
			// get roles string
			String strRoles = account.getRoles();
			if (null != strRoles && !"".equals(strRoles)) {
				roles.addAll(Arrays.asList(strRoles.split(",")));
			}
		}
		return roles;
	}

	@Override
	public List<String> getPermissions(String userName) {
		List<String> permissions = new ArrayList<String>();

		AccountModel account = new AccountModel();
		account.setAccount(userName);
		account = accountMapper.selAccountByPk(account);

		if (null != account) {
			// get roles string
			String strRoles = account.getRoles();
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

	@Override
	public Map<String, String> getFilterChainDefinitionMap() {
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		String path = "/shiroFilterChain.properties";
		InputStream is = MiniSecurity.class.getClassLoader().getResourceAsStream(path);

		try {
			Properties props = new OrderProperties();
			props.load(is);
			for (String key : props.stringPropertyNames()) {
				String val = props.getProperty(key);
				filterChainDefinitionMap.put(key, val);
				MiniLog.debug(key + "=" + val);
			}
		} catch (IOException e) {
			MiniLog.error("load " + path + " error.");
		}
		return filterChainDefinitionMap;
	}
}
