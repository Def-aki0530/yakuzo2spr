package com.example.yakuzo2.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.yakuzo2.data.ShainData;

@Repository
public class KengenRepository {
	@Autowired
	JdbcTemplate jt;

	public void makeKengenList(ShainData sd) {
		String sql = "select kengen_code,kengen_name from mst_kengen where delete_flg = '0'";

		sd.setKengen_list(jt.queryForList(sql));
	}

	public void getKengenName(ShainData sd) {
		String sql = "select kengen_name from mst_kengen where kengen_code = ?";
		Map<String,Object> map = jt.queryForMap(sql,sd.getKengen_code());
		sd.setKengen_name(map.get("kengen_name").toString());
	}
}
