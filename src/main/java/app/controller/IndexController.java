package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.conf.springMvc.ControllerBase;

@Controller
@RequestMapping(value = "/index")
public class IndexController extends ControllerBase {

	@RequestMapping(method = RequestMethod.GET)
	public String _jsp(Model model) {
		return "index";
	}
}
