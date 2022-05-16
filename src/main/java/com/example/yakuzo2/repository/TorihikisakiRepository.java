package com.example.yakuzo2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.yakuzo2.data.LoginData;

@Repository
public class TorihikisakiRepository {

		@Autowired
		JdbcTemplate jt;

		public  List<Map<String,Object>> getTenpoList() {
			//SQL
			String sql = "select torihikisaki_code, torihikisaki_name from mst_torihikisaki where torihikisaki_kbn = '2' and delete_flg = '0'";
			return jt.queryForList(sql);
		}

		public void getTenpoName(LoginData ld) {
			String sql ="select torihikisaki_name from mst_torihikisaki where torihikisaki_code = ?";
			Map<String,Object> map = jt.queryForMap(sql,ld.getTenpo_code());

			ld.setTenpo_name(map.get("torihikisaki_name").toString());
		}

		public List<Map<String,Object>> getSanshoData(String torihikisaki,String kbn,int page){
			StringBuilder sql = new StringBuilder();
			List<Object> param = new ArrayList();

			sql.append("select torihikisaki_code,torihikisaki_name ");
			sql.append("from mst_torihikisaki ");
			//torihikisaki
			if(!torihikisaki.equals("")) {
				sql.append("where (");
				sql.append("torihikisaki_code = ? ");
				sql.append("or torihikisaki_name like ? ) ");
				param.add(torihikisaki);
				param.add("%" + torihikisaki + "%");
			}
			//torihikisaki_kbn
			if(!kbn.equals("")) {
				if(param.size() == 0) {
					sql.append("where ");
				} else {
					sql.append("and ");
				}
				sql.append("torihikisaki_kbn = ? ");
				param.add(kbn);
			}
			//limit
			sql.append("limit ? , 25");
			param.add((page - 1) * 25);

			return jt.queryForList(sql.toString(),param.toArray());
		}
}
