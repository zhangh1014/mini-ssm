package app.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.lechisoft.minifw.log.MiniLog;
import org.lechisoft.minifw.security.MiniSecurity;
import org.lechisoft.minifw.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.conf.common.ConstValue;
import app.conf.common.ReturnMessage;
import app.conf.common.ReturnMessage.MessageType;
import app.conf.exception.ServiceException;
import app.conf.springMvc.ControllerBase;
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
			return "login";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ReturnMessage signin(@RequestBody User user, Model model) {
		ReturnMessage returnMessage = new ReturnMessage();
		try {
			MiniSecurity.signin(user.getUserName(), user.getPassword());
			MiniSecurity.setSessionAttribute(ConstValue.SESSION_LOGIN_OBJECT_KEY, user.getUserName());
			this.showMessage("你好，"+user.getUserName(), MessageType.Info, IndexController.class);
			
			return returnMessage;
		} catch (UnknownAccountException e) {
			returnMessage.setTitle("账号或密码错误");
			returnMessage.setMessage("未知账户");
			returnMessage.setType(MessageType.Error);
			return returnMessage;
		} catch (IncorrectCredentialsException e) {
			returnMessage.setTitle("账号或密码错误");
			returnMessage.setMessage("密码不正确");
			returnMessage.setType(MessageType.Error);
			return returnMessage;
		} catch (Exception e) {
			returnMessage.setTitle("账号或密码错误");
			returnMessage.setMessage("登录异常");
			returnMessage.setType(MessageType.Error);
			return returnMessage;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(Model model) {
		MiniSecurity.signout();
		return "";
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
