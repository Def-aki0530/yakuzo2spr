package com.example.yakuzo2.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.yakuzo2.data.LoginData;

@Repository
public class LoginRepository {

	@Autowired
	JdbcTemplate jt;

	public boolean existsCheck(LoginData ld) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("ms.shain_name,");
		sql.append("ms.kengen_code,");
		sql.append("mk.kengen_name ");
		sql.append("from ");
		sql.append("mst_shain as ms join mst_kengen as mk ");
		sql.append("on ms.kengen_code = mk.kengen_code ");
		sql.append("where ");
		sql.append("ms.shain_code = ? and ms.password = ? and ms.delete_flg = '0'");

		List<Map<String,Object>> list = jt.queryForList(sql.toString(),ld.getShain_code(),ld.getPassword());

		if(list.size() == 0){
			return false;
		}

		Map<String,Object> map = list.get(0);
		ld.setShain_name(map.get("shain_name").toString());
		ld.setKengen_code(map.get("kengen_code").toString());
		ld.setKengen_name(map.get("kengen_name").toString());

		return true;
	}
}
