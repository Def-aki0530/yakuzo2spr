package com.example.yakuzo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.yakuzo2.data.HatchuData;
import com.example.yakuzo2.service.HatchuService;

@Controller
public class HatchuController {

	@Autowired
	HatchuService hs;

	@GetMapping("/hatchu")
	public String index(@ModelAttribute("hd") HatchuData hd,Model model) {
		//hdに店舗リストとを持たせる
		hs.getTenpoList(hd);

		//画面表示
		return "hatchu";
	}

	@PostMapping("/hatchugetlist")
	public String getList(@ModelAttribute("hd") HatchuData hd,Model model) {
		//一覧作成に必要なリストを取得
		hs.getList(hd);
		//一覧用html呼び出し
		return "hatchuList";
	}
}