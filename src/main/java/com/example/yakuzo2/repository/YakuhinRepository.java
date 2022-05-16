package com.example.yakuzo2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class YakuhinRepository {

	@Autowired
	JdbcTemplate jt;

	public List<Map<String,Object>> getSanshoData(String yakuhin,String kbn,int page){
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList();

		sql.append("select jan_code,yj_code,yakuhin_kbn,hanbai_name ");
		sql.append("from mst_shohin ");
		//yakuhin
		if(!yakuhin.equals("")) {
			sql.append("where (");
			sql.append("jan_code = ? ");
			sql.append("or hanbai_name like ? ) ");
			param.add(yakuhin);
			param.add("%" + yakuhin + "%");
		}
		//yakuhin_kbn
		if(!kbn.equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			} else {
				sql.append("or ");
			}
			sql.append("yakuhin_kbn = ? ");
			param.add(kbn);
		}
		//limit
		sql.append("limit ? , 25");
		param.add((page - 1) * 25);

		return jt.queryForList(sql.toString(),param.toArray());
	}
}