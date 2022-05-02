package com.example.yakuzo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.yakuzo2.data.ShainData;
import com.example.yakuzo2.service.ShainService;

@Controller
public class ShainController {

	@Autowired
	ShainService ss;

	@GetMapping("/mst_shain")
	public String index(@ModelAttribute("sd")ShainData sd,Model model) {
		ss.makeKengenList(sd);

		return "shain";
	}

	@PostMapping("/shaingetlist")
	public String getList(@ModelAttribute("sd") ShainData sd,Model model) {
		ss.getList(sd);

		return "shainList";
	}

	@GetMapping("/shainregist")
	public String dispShinkit(@ModelAttribute("sd") ShainData sd,Model model) {
		ss.makeKengenList(sd);

		return "shainRegist";
	}

	public String insertShain(@ModelAttribute("sd") ShainData sd,Model model) {

		if(!ss.check(sd)) {
			ss.makeKengenList(sd);
			sd.setTitle("社員データの新規登録");
			sd.setAction("insertshaindata");
			return "shainRegist";
		}

		return "shainConfirm";
	}
}