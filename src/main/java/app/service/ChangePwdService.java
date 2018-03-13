package app.service;

import java.util.Random;

import org.lechisoft.minifw.security.MiniSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.conf.exception.ServiceException;
import app.mapper.AccountMapper;
import app.model.AccountModel;

@Component
@Transactional(rollbackFor = { ServiceException.class, Exception.class })
public class ChangePwdService {

	@Autowired
	private AccountMapper accountMapper;

	/**
	 * 更改密码
	 * 
	 * @param accountModel
	 * @return
	 * @throws ServiceException
	 */
	public boolean changePwd(String account, String newPwd) throws ServiceException {

		AccountModel accountModel = new AccountModel();
		accountModel.setAccount(account);
		accountModel = accountMapper.selAccountByPk(accountModel);

		if (null == accountModel) {
			throw new ServiceException("更新密码错误：用户" + account + "不存在。");
		}

		// salt
		Random rdm = new Random();
		String salt = String.valueOf(rdm.nextInt(9) + 1) + String.valueOf(rdm.nextInt(10))
				+ String.valueOf(rdm.nextInt(10));
		newPwd = MiniSecurity.getHash("MD5", newPwd, salt, 1);

		accountModel.setPassword(newPwd);
		accountModel.setSalt(salt);

		int result = accountMapper.updAccountByPk(accountModel);
		if (result != 1) {
			throw new ServiceException("更新密码失败。");
		}

		return true;
	}
}
