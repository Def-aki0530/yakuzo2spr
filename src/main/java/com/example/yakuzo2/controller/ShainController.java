package com.example.yakuzo2.controller;

import javax.servlet.http.HttpSession;

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

	@Autowired
	HttpSession session;

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
	public String dispShinki(@ModelAttribute("sd") ShainData sd,Model model) {
		ss.makeKengenList(sd);

		sd.setTitle("社員データの新規登録");
		sd.setAction("insertshaindata");

		return "shainRegist";
	}

	@PostMapping("/insertshaindata")
	public String insertShain(@ModelAttribute("sd") ShainData sd,Model model) {

		if(!ss.check(sd)) {
			ss.makeKengenList(sd);
			sd.setTitle("社員データの新規登録");
			sd.setAction("insertshaindata");
			return "shainRegist";
		}

		sd.setTitle("社員データ登録確認");
		sd.setAction("exeinsertshain");
		ss.getKengenName(sd);

		return "shainConfirm";
	}

	@PostMapping("/exeinsertshain")
	public String exeInsert(@ModelAttribute("sd") ShainData sd,Model model) {
		sd.setRegist_shain_code(session.getAttribute("login_shain_code").toString());
		ss.exeInsert(sd);

		//完了画面
		return "shainComplete";
	}


}