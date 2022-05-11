package com.example.yakuzo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yakuzo2.data.LoginData;
import com.example.yakuzo2.repository.LoginRepository;
import com.example.yakuzo2.repository.TorihikisakiRepository;

@Service
public class LoginService {

	@Autowired
	TorihikisakiRepository tr;

	@Autowired
	LoginRepository lr;

	public void getTenpoList(LoginData ld) {
		ld.setList(tr.getTenpoList());
	}

	public boolean check(LoginData ld) {
		//存在チェック
		if(!lr.existsCheck(ld)) {
			ld.setMsg("社員コード、またはパスワードが違います。");

			return false;
		}
		if(ld.getKengen_code().equals("002") && ld.getTenpo_code().equals("")) {
			ld.setMsg("店舗権限者は、店舗の選択が必須です。");
			return false;
		}

		//店舗情報
		if(ld.getKengen_code().equals("001")) {
			ld.setTenpo_code("");
			ld.setTenpo_name("");
		}
		else {
			tr.getTenpoName(ld);
		}

		return true;
	}

}
