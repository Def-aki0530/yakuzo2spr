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

		public boolean check(HatchuData hd) {
			//入荷数
			if(hd.getNyuka_su().equals("")) {
				hd.setNyuka_su("0");
			}
			//取引先
			if(hd.getTorihikisaki_name().equals("")) {
				hd.setMsg("取引先の選択は必須です。");
				return false;
			}
			//発注日
			if(hd.getHatchu_date().equals("")) {
				hd.setMsg("発注日は必須入力です。");
				return false;
			}
			//薬品
			if(hd.getHanbai_name().equals("")) {
				hd.setMsg("薬品の選択は必須です。");
				return false;
			}
			//発注数
			if(hd.getHatchu_su().equals("")) {
				hd.setMsg("発注数は必須入力です。");
				return false;
			}
			return true;
		}

		public void insertHatchuData(HatchuData hd) {
			hr.insertHatchuData(hd);
		}

		public void getHatchuData(HatchuData hd) {
			hr.getHatchuData(hd);
		}

		public void getTenpoName(HatchuData hd) {
			hd.setTenpo_name(tr.getTenpoName(hd.getTenpo_code()));
		}

		public void updHatchuData(HatchuData hd) {
			hr.updHatchuData(hd);
		}
}
