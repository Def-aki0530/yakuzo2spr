package com.example.yakuzo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yakuzo2.data.HatchuData;
import com.example.yakuzo2.repository.HatchuRepository;
import com.example.yakuzo2.repository.TorihikisakiRepository;
import com.example.yakuzo2.repository.YakuhinRepository;

@Service
public class HatchuService {

		@Autowired
		TorihikisakiRepository tr;

		@Autowired
		HatchuRepository hr;

		@Autowired
		YakuhinRepository yr;

		public void getTenpoList(HatchuData hd) {
			hd.setTenpo_list(tr.getTenpoList());
		}

		public void getList(HatchuData hd) {
			hr.getList(hd);
		}

		public void getTorihikisakiList(HatchuData hd) {
			hd.setTorihikisaki_list(tr.getSanshoData(hd.getTorihikisaki(),hd.getTorihikisaki_kbn() , hd.getPage()));
		}

		public void getYakuhinList(HatchuData hd) {
			hd.setYakuhin_list(yr.getSanshoData(hd.getYakuhin(), hd.getYakuhin_kbn(), hd.getPage()));
		}
}
