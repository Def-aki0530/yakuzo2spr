package com.example.yakuzo2.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.yakuzo2.data.LoginData;

@Repository
public class TorihikisakiRepository {

		@Autowired
		JdbcTemplate jt;

		public void getTenpoList(LoginData ld) {
			//SQL
			String sql = "select torihikisaki_code, torihikisaki_name from mst_torihikisaki where torihikisaki_kbn = '2' and delete_flg = '0'";
			ld.setList(jt.queryForList(sql));
		}

		public void getTenpoName(LoginData ld) {
			String sql ="select torihikisaki_name from mst_torihikisaki where torihikisaki_code = ?";
			Map<String,Object> map = jt.queryForMap(sql,ld.getTenpo_code());

			ld.setTenpo_name(map.get("torihikisaki_name").toString());
		}
}
