package app.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.lechisoft.minifw.security.MiniSecurity;

public class RolesOrAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String[] roles = (String[]) mappedValue;

		if (null == roles || roles.length == 0) {
			return true;
		}

		return MiniSecurity.hasAnyRole(roles);
	}

}
