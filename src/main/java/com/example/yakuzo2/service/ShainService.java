package com.example.yakuzo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yakuzo2.data.ShainData;
import com.example.yakuzo2.repository.KengenRepository;
import com.example.yakuzo2.repository.ShainRepository;

@Service
public class ShainService {

	@Autowired
	KengenRepository kr;

	@Autowired
	ShainRepository sr;

	public void makeKengenList(ShainData sd) {
		kr.makeKengenList(sd);
	}

	public void getList(ShainData sd) {
		sr.getList(sd);
	}

	public boolean check(ShainData sd) {
		if(!sr.checkExists(sd)) {
			sd.setMsg("社員コードは既に使用されています。");
			return false;
		}

		if(!sd.getPassword().equals(sd.getPassword2())) {
			return false;
		}
		return true;
	}

}
