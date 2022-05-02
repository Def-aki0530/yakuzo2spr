package com.example.yakuzo2.data;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ShainData {
	private String shain = "";
	private String login_flg = "";
	private List<Map<String,Object>> kengen_list;
	private String kengen_code = "";
	private String delete_flg = "";
	private List<Map<String,Object>> list;
	private int page = 0;
	//登録用
	private String shain_code = "";
	private String shain_name = "";
	private String shain_name_kana = "";
	private String kengen_name = "";
	private String password = "";
	private String password2 = "";
	private String mail_address = "";
	private String regist_shain_code = "";
	//動的項目
	private String title = "";
	private String action= "";
	private String msg = "";
}
