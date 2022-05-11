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
}
