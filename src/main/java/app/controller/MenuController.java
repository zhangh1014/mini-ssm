package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.conf.springMvc.ControllerBase;
import app.model.MenuModel;
import app.service.MenuService;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends ControllerBase {

	@Autowired
	private MenuService menuService;

	@ResponseBody
	@RequestMapping(value = "/mymenu", method = RequestMethod.GET)
	public List<MenuModel> getMyMenu() {
		return menuService.getMyMenu();
	}
}
