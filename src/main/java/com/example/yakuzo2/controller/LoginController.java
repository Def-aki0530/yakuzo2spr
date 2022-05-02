package com.example.yakuzo2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.yakuzo2.data.LoginData;
import com.example.yakuzo2.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService ls;
	@Autowired
	HttpSession session;

	@GetMapping("/")
	public String index(@ModelAttribute("ld") LoginData ld,Model model) {
		ls.getTenpoList(ld);

		return "login";
	}

	@PostMapping("/put_login")
	public String putLogin(@ModelAttribute("ld") LoginData ld,Model model) {
		if(!ls.check(ld)) {

			return "login";
		}

		session.setMaxInactiveInterval(0);
		session.setAttribute("login_shain_code",ld.getShain_code());
		session.setAttribute("login_shain_name",ld.getShain_name());
		session.setAttribute("login_tenpo_code",ld.getTenpo_code());
		session.setAttribute("login_tenpo_name",ld.getTenpo_name());
		session.setAttribute("login_kengen_code",ld.getKengen_code());
		session.setAttribute("login_kengen_name",ld.getKengen_name());

		return "redirect:menu";
	}
}
