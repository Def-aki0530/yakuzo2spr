package com.example.yakuzo2.controller;

import javax.servlet.http.HttpSession;

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

	@Autowired
	HttpSession session;

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
		hd.setMode("new");
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

	@PostMapping("/hatchuconfilm")
	public String hatchuConfilm(@ModelAttribute("hd") HatchuData hd,Model model) {
		if(hd.getMode().equals("new")) {
			hd.setTitle("発注データ登録確認");
			hd.setSubtitle("--この内容で登録しますか？よろしければ登録ボタンを押下してください。--");
			hd.setAction("inserthatchudata");
			hd.setButton_name("登録");
			hd.setCancel_action("hatchushinki");
			if(!hs.check(hd)) {
				hs.getTenpoList(hd);
				return "hatchuRegist";
			}
		}else if(hd.getMode().equals("edit")) {
			hd.setTitle("発注データ登録確認");
			hd.setSubtitle("--この内容で登録しますか？よろしければ登録ボタンを押下してください。--");
			hd.setAction("updhatchudata");
			hd.setButton_name("登録");
			hd.setCancel_action("hatchuedit");
			if(!hs.check(hd)) {
				hs.getTenpoList(hd);
				return "hatchuRegist";
			}
		} else {
			hd.setTitle("発注データ削除確認");
			hd.setSubtitle("--このデータを削除しますか？よろしければ削除ボタンを押下してください。--");
			hd.setAction("inserthatchudata");
			hd.setButton_name("削除");
			hd.setCancel_action("hatchu");
		}
		hs.getTenpoName(hd);  //ここ
		return "hatchuConfilm";
	}
	@PostMapping("/inserthatchudata")
	public String insertHatchuData(@ModelAttribute("hd") HatchuData hd,Model model) {
		hd.setLogin_shain_code(session.getAttribute("login_shain_code").toString());
		hd.setTxtComplete("--発注データの登録が完了しました。--");
		hs.insertHatchuData(hd);
		return "hatchuComplete";
	}

	@PostMapping("/hatchuedit")
	public String dispHatchuEdit(@ModelAttribute("hd") HatchuData hd,Model model) {
		hs.getTenpoList(hd);
		//初期表示のときだけ、データ取得
		if(hd.getMode().equals("")) {
			hs.getHatchuData(hd);
		}

		hd.setTitle("発注データ編集登録");
		hd.setAction("hatchuconfilm");
		hd.setMode("edit");

		return "hatchuRegist";
	}

	@PostMapping("updhatchudata")
	public String updHatchuData(@ModelAttribute("hd") HatchuData hd,Model model) {
		hd.setLogin_shain_code(session.getAttribute("login_shain_code").toString());
		hs.updHatchuData(hd);

		hd.setTxtComplete("--発注データの編集登録が完了しました。--");

		return "hatchuComplete";
	}
}
