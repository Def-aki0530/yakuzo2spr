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

	@PostMapping("/hatchushinki")
	public String hatchuShinki(@ModelAttribute("hd") HatchuData hd,Model model) {
		//HatchuServiceを通じて、必要なデータをDataクラスに持たせる
		hs.getTenpoList(hd);
		//動的項目の設定
		hd.setTitle("発注データ新規登録");
		hd.setAction("hatchuconfilm");
		//画面表示
		return "hatchuRegist";
	}

	@GetMapping("/torihikisakisansho")
	public String disptorihikisakimodal() {

		return "torihikisakiSansho";
	}
	@PostMapping("/t-sansho-getlist")
	public String t_sansho_getList(@ModelAttribute("hd") HatchuData hd,Model model) {
		hs.getTorihikisakiList(hd);
		return "torihikisakiSanshoList";
	}

	@GetMapping("/yakuhinsansho")
	public String dispyakuhinmodal() {
		return "yakuhinSansho";
	}

	@PostMapping("/y-sansho-getlist")
	public String y_sansho_getList(@ModelAttribute("hd") HatchuData hd,Model model) {
		hs.getYakuhinList(hd);
		System.out.println(model.toString());
		return "yakuhinSanshoList";
	}
}
