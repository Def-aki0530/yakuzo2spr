package com.example.yakuzo2.data;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class HatchuData {
	//検索項目
	private String yakuhin_kbn ="";
	private String hatchu_kbn = "";
	private String shori_kbn = "";
	private String shogo_flg = "";
	private String suryo_kbn = "";
	private List<Map<String,Object>> tenpo_list;
	private String tenpo_code = "";
	private String date_from = "";
	private String date_to = "";
	private String yakuhin = "";
	private String torihikisaki = "";
	private int page = 0;
	private List<Map<String,Object>> list;
	//登録用
	private String hatchu_seq = "";
	private String nendo = "";
	private String tenpo_name = "";
	private String torihikisaki_code = "";
	private String torihikisaki_name = "";
	private String jan_code = "";
	private String hanbai_name = "";
	private String yj_code = "";
	private String hatchu_su = "";
	private String nyuka_su = "";
	private String hatchu_date = "";
	private String delete_flg = "";
	private String biko = "";
	private String created_on = "";
	private String created_by = "";
	private String updated_on = "";
	private String updated_by = "";
	//共通項目
	private String title = "";
	private String action = "";
	private String msg = "";
	private String login_shain_code = "";
	private String login_kengen_code = "";
	private String readonly = "";

	private String torihikisaki_kbn;
	private List<Map<String,Object>> torihikisaki_list;
	private List<Map<String,Object>> yakuhin_list;
}
