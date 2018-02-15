package app.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.lechisoft.minifw.log.MiniLog;
import org.lechisoft.minifw.security.MiniSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.common.ConstValue;
import app.common.Message.MessageType;
import app.conf.springMvc.ControllerBase;
import app.exception.ServiceException;
import app.service.ChangePwdService;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends ControllerBase {

	@Autowired
	ChangePwdService changePwdService;

	@RequestMapping(method = RequestMethod.GET)
	public String _jsp(Model model) {
		if (MiniSecurity.isAuthenticated()) {
			return "redirect:/index";
		} else {
			MiniLog.debug(MiniSecurity.getSessionId());
			return "login";
		}
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(String userName, String password, Model model) {

		if (null == userName || "".equals(userName) || null == password || "".equals(password)) {
			this.showMessage("账号或密码错误。", MessageType.Info, LoginController.class);
			return "redirect:/login";
		}

		try {
			MiniSecurity.signin(userName, password);
			MiniSecurity.setSessionAttribute(ConstValue.SESSION_LOGIN_OBJECT_KEY, userName);
			return "redirect:/index";
		} catch (UnknownAccountException e) {
			this.showMessage("账号或密码错误。", MessageType.Info, LoginController.class);
			return "redirect:/login";
		} catch (IncorrectCredentialsException e) {
			this.showMessage("账号或密码错误。", MessageType.Info, LoginController.class);
			return "redirect:/login";
		} catch (Exception e) {
			this.showMessage("登录异常。", MessageType.Error, LoginController.class);
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(Model model) {
		MiniSecurity.signout();
		return "redirect:/login";
	}

	@ResponseBody
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public String signout(String pwd, String rePwd) {
		if ("".equals(pwd)) {
			return "新密码不能为空。";
		}
		if ("".equals(rePwd)) {
			return "确认密码不能为空。";
		}
		if (!pwd.equals(rePwd)) {
			return "新密码与确认密码不一致。";
		}

		// change password
		String userName = MiniSecurity.getSessionAttribute(ConstValue.SESSION_LOGIN_OBJECT_KEY).toString();
		try {
			changePwdService.changePwd(userName, pwd);
		} catch (ServiceException e) {
			return e.getMessage();
		}
		return "";
	}

}
