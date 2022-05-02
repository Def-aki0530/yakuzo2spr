package com.example.yakuzo2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.yakuzo2.data.ShainData;

@Repository
public class ShainRepository {

	@Autowired
	JdbcTemplate jt;

	public int getPages(ShainData sd) {
		//SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) as cnt ");
		sql.append("from mst_shain ");

		//param
		List<Object> param = new ArrayList();

		//shain
		if(!sd.getShain().equals("")) {
			sql.append("where ");
			sql.append("shain_code = ?  ");
			sql.append("or shain_name like ? ");
			sql.append("or shain_name_kana like ? ");
			param.add(sd.getShain());
			param.add("%" + sd.getShain() + "%" );
			param.add("%" + sd.getShain() + "%");
		}
		//login_flg
		if(!sd.getLogin_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("login_flg = ?");
			param.add(sd.getLogin_flg());
		}
		//kengen_code
		if(!sd.getKengen_code().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("kengen_code = ? ");
			param.add(sd.getKengen_code());
		}
		//削除フラグ
		if(!sd.getDelete_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("delete_flg = ? ");
			param.add(sd.getDelete_flg());
		}

		Map<String,Object> map = jt.queryForMap(sql.toString(),param.toArray());

		double records = Double.parseDouble(map.get("cnt").toString());

		return (int)(Math.ceil(records / 25));
	}

	public void getList(ShainData sd) {
		//SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("ms.shain_code,");
		sql.append("ms.shain_name,");
		sql.append("case ms.login_flg ");
		sql.append("when '0' then '可' ");
		sql.append("when '1' then '不可' ");
		sql.append("else '不明' end as login_flg,");
		sql.append("ms.mail_address,");
		sql.append("mk.kengen_name,");
		sql.append("case ms.delete_flg ");
		sql.append("when '0' then '' ");
		sql.append("when '1' then '削除済み' ");
		sql.append("else '不明' end as delete_flg ");
		sql.append("from mst_shain as ms join mst_kengen as mk ");
		sql.append("on ms.kengen_code = mk.kengen_code ");
		//param
		List<Object> param = new ArrayList();

		//shain
		if(!sd.getShain().equals("")) {
			sql.append("where ");
			sql.append("ms.shain_code = ?  ");
			sql.append("or ms.shain_name like ? ");
			sql.append("or ms.shain_name_kana like ? ");
			param.add(sd.getShain());
			param.add("%" + sd.getShain() + "%" );
			param.add("%" + sd.getShain() + "%");
		}
		//login_flg
		if(!sd.getLogin_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("ms.login_flg = ?");
			param.add(sd.getLogin_flg());
		}
		//kengen_code
		if(!sd.getKengen_code().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("ms.kengen_code = ? ");
			param.add(sd.getKengen_code());
		}
		//削除フラグ
		if(!sd.getDelete_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("ms.delete_flg = ? ");
			param.add(sd.getDelete_flg());
		}
		sql.append("order by ms.shain_code ");
		sql.append("limit ?,25");
		int rec = (sd.getPage() - 1) * 25;
		param.add(rec);

		sd.setList(jt.queryForList(sql.toString(),param.toArray()));
	}

	//存在チェック
	public boolean checkExists(ShainData sd) {
		//SQL
		String sql = "select count(*) as cnt from mst_shain where shain_code = ?";
		Map<String,Object> map = jt.queryForMap(sql, sd.getShain_code());

		int count = Integer.parseInt(map.get("cnt").toString());
		if(count != 0) {
			return false;
		}

		return true;
	}

	//登録処理
	public void exeInsert(ShainData sd) {
		String sql ="insert into mst_shain values(?,?,?,?,?,?,?,?,?,now(),?,now(),?)";
		List<Object> param = new ArrayList();

		param.add(sd.getShain_code());
		param.add(sd.getShain_name());
		param.add(sd.getShain_name_kana());
		param.add(sd.getPassword());
		param.add(sd.getLogin_flg());
		param.add(sd.getMail_address());
		param.add(sd.getKengen_code());
		param.add("");
		param.add(sd.getDelete_flg());
		param.add(sd.getRegist_shain_code());
		param.add(sd.getRegist_shain_code());

		jt.update(sql,param.toArray());

	}
}


