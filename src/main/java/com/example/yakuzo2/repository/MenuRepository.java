package com.example.yakuzo2.repository;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.yakuzo2.data.MenuData;

@Repository
public class MenuRepository {
	@Autowired
	HttpSession session;
	@Autowired
	JdbcTemplate jt;

	public void makeMenuList(MenuData md) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("mm.menu_name,");
		sql.append("mm.menu_uri ");
		sql.append("from ");
		sql.append("mst_menu_kengen as mmk join mst_menu as mm ");
		sql.append("on mmk.menu_code = mm.menu_code ");
		sql.append("where ");
		sql.append("mmk.kengen_code = ? and mm.menu_kbn = ? and mmk.delete_flg ='0' ");
		sql.append("order by mmk.hyoji_jun");

		md.setKengen_code(session.getAttribute("login_kengen_code").toString());

		//業務メニュー
		md.setDutiesList(jt.queryForList(sql.toString(),md.getKengen_code(),"01"));
		//在庫管理メニュー
		md.setStockList(jt.queryForList(sql.toString(),md.getKengen_code(),"02"));
		//システムメニュー
		md.setSystemList(jt.queryForList(sql.toString(),md.getKengen_code(),"03"));

	}
}
