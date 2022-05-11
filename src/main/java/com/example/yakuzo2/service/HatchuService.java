package com.example.yakuzo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yakuzo2.data.HatchuData;
import com.example.yakuzo2.repository.HatchuRepository;
import com.example.yakuzo2.repository.TorihikisakiRepository;

@Service
public class HatchuService {

		@Autowired
		TorihikisakiRepository tr;

		@Autowired
		HatchuRepository hr;

		public void getTenpoList(HatchuData hd) {
			hd.setTenpo_list(tr.getTenpoList());
		}

		public void getList(HatchuData hd) {
			hr.getList(hd);
		}
}
