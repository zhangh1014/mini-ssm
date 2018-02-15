package app.service;

import java.util.Random;

import org.lechisoft.minifw.security.MiniSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.conf.exception.ServiceException;
import app.mapper.LoginUserMapper;
import app.model.LoginUserModel;

@Component
@Transactional(rollbackFor = { ServiceException.class, Exception.class })
public class ChangePwdService {

	@Autowired
	private LoginUserMapper loginUserMapper;

	/**
	 * 更改密码
	 * 
	 * @param accountModel
	 * @return
	 * @throws ServiceException
	 */
	public boolean changePwd(String loginName, String newPwd) throws ServiceException {

		LoginUserModel loginUser = new LoginUserModel();
		loginUser.setLoginName(loginName);
		loginUser = loginUserMapper.selLoginUserByPk(loginUser);

		if (null == loginUser) {
			throw new ServiceException("更新密码错误：用户" + loginName + "不存在。");
		}

		// salt
		Random rdm = new Random();
		String salt = String.valueOf(rdm.nextInt(9) + 1) + String.valueOf(rdm.nextInt(10))
				+ String.valueOf(rdm.nextInt(10));
		newPwd = MiniSecurity.getHash("MD5", newPwd, salt, 1);

		loginUser.setPassword(newPwd);
		loginUser.setSalt(salt);

		int result = loginUserMapper.updLoginUserByPk(loginUser);
		if (result != 1) {
			throw new ServiceException("更新密码失败。");
		}
		
		return true;
	}
}
