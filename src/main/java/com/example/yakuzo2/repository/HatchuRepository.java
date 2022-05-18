package com.example.yakuzo2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.yakuzo2.data.HatchuData;

@Repository
public class HatchuRepository {

	@Autowired
	JdbcTemplate jt;

	public int getPages(HatchuData hd) {
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList();

		sql.append("select count(*) as cnt ");
		sql.append("from hatchu ");
		//薬品区分
		if(!hd.getYakuhin_kbn().equals("")) {
			sql.append("where yakuhin_kbn = ? ");
			param.add(hd.getYakuhin_kbn());
		}

		//削除フラグ
		if(!hd.getDelete_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			if(!hd.getDelete_flg().equals("")) {
				sql.append("delete_flg = ? ");
				param.add(hd.getDelete_flg());
			}
		}

		//data_from
		if(!hd.getDate_from().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("hatchu_date >= ? ");
			param.add(hd.getDate_from() + "00:00:00");
		}

		//data_to
		if(!hd.getDate_to().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("hatchu_date <= ? ");
			param.add(hd.getDate_from() + "23:59:59");
		}

		//取引先
		if(!hd.getTorihikisaki().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("(");
			sql.append("torihikisaki_code = ? ");
			sql.append("or torihikisaki_name like ? ");
			sql.append(") ");
			param.add(hd.getTorihikisaki());
			param.add("%" + hd.getTorihikisaki() + "%");
		}

		//薬品
		if(!hd.getYakuhin().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("(");
			sql.append("jan_code = ? ");
			sql.append("or hanbai_name like ? ");
			sql.append(") ");
			param.add(hd.getYakuhin());
			param.add("%" + hd.getYakuhin() + "%");
		}

		//店舗
		if(!hd.getTenpo_code().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("tenpo_code = ? ");
			param.add(hd.getTenpo_code());
		}

		//発注区分
		if(!hd.getHatchu_kbn().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("hatchu_kbn = ? ");
			param.add(hd.getHatchu_kbn());
		}

		//処理区分
		if(!hd.getShori_kbn().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("shori_kbn = ? ");
			param.add(hd.getShori_kbn());
		}

		//マスタ照合
		if(!hd.getShogo_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("shogo_flg = ? ");
			param.add(hd.getShogo_flg());
		}

		Map<String,Object> map = jt.queryForMap(sql.toString(),param.toArray());
		double records = Double.parseDouble(map.get("cnt").toString());

		return (int)(Math.ceil(records /25));
	}

	public void getList(HatchuData hd) {
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList();

		sql.append("select ");
		sql.append("h.hatchu_seq,");
		sql.append("mk1.torihikisaki_name tenpo_name,");
		sql.append("mk2.torihikisaki_name,");
		sql.append("h.hatchu_date,");
		sql.append("h.delete_flg,");
		sql.append("case h.yakuhin_kbn ");
		sql.append("when '1' then '薬品' ");
		sql.append("when '2' then 'OTC' ");
		sql.append("when '3' then '特材' ");
		sql.append("else '不明' end yakuhin_kbn, ");
		sql.append("ms.hanbai_name,");
		sql.append("ms.hoso_gryo,");
		sql.append("h.hatchu_su,");
		sql.append("case h.suryo_kbn ");
		sql.append("when '1' then '箱' ");
		sql.append("when '2' then 'バラ' ");
		sql.append("else '不明' end suryo_kbn, ");
		sql.append("case h.hatchu_kbn ");
		sql.append("when '1' then 'メディコード' ");
		sql.append("when '2' then 'その他' ");
		sql.append("when '3' then 'EPI' ");
		sql.append("else '不明' end hatchu_kbn, ");
		sql.append("case h.shogo_flg ");
		sql.append("when '0' then '未照合' ");
		sql.append("when '1' then '照合済み' ");
		sql.append("else '不明' end shogo_flg, ");
		sql.append("case h.shori_kbn ");
		sql.append("when '1' then '未納品' ");
		sql.append("when '2' then '納品中' ");
		sql.append("when '3' then '納品済' ");
		sql.append("else '不明' end shori_kbn ");
		sql.append("from hatchu h ");
		sql.append("join mst_torihikisaki mk1 ");
		sql.append("on h.tenpo_code = mk1.torihikisaki_code ");
		sql.append("join mst_torihikisaki mk2 ");
		sql.append("on h.torihikisaki_code = mk2.torihikisaki_code ");
		sql.append("join mst_shohin ms ");
		sql.append("on h.jan_code = ms.jan_code ");

		//薬品区分
		if(!hd.getYakuhin_kbn().equals("")) {
			sql.append("where h.yakuhin_kbn = ? ");
			param.add(hd.getYakuhin_kbn());
		}

		//削除フラグ
		if(!hd.getDelete_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			if(!hd.getDelete_flg().equals("")) {
				sql.append("h.delete_flg = ? ");
				param.add(hd.getDelete_flg());
			}
		}


		//data_from
		if(!hd.getDate_from().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("h.hatchu_date >= ? ");
			param.add(hd.getDate_from() + "00:00:00");
		}

		//data_to
		if(!hd.getDate_to().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("h.hatchu_date <= ? ");
			param.add(hd.getDate_from() + "23:59:59");
		}

		//取引先
		if(!hd.getTorihikisaki().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("(");
			sql.append("h.torihikisaki_code = ? ");
			sql.append("or mk2.torihikisaki_name like ? ");
			sql.append(") ");
			param.add(hd.getTorihikisaki());
			param.add("%" + hd.getTorihikisaki() + "%");
		}

		//薬品
		if(!hd.getYakuhin().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("(");
			sql.append("h.jan_code = ? ");
			sql.append("or ms.hanbai_name like ? ");
			sql.append(") ");
			param.add(hd.getYakuhin());
			param.add("%" + hd.getYakuhin() + "%");
		}

		//店舗
		if(!hd.getTenpo_code().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("h.tenpo_code = ? ");
			param.add(hd.getTenpo_code());
		}

		//発注区分
		if(!hd.getHatchu_kbn().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("h.hatchu_kbn = ? ");
			param.add(hd.getHatchu_kbn());
		}

		//処理区分
		if(!hd.getShori_kbn().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("h.shori_kbn = ? ");
			param.add(hd.getShori_kbn());
		}

		//マスタ照合
		if(!hd.getShogo_flg().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			}
			else {
				sql.append("and ");
			}
			sql.append("h.shogo_flg = ? ");
			param.add(hd.getShogo_flg());
		}

		//order by
		sql.append("order by h.hatchu_date ");
		//limit
		sql.append("limit ?,25");
		param.add((hd.getPage() - 1) * 25);

		hd.setList(jt.queryForList(sql.toString(),param.toArray()));
	}

	public int t_sansho_getPages(HatchuData hd) {
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList();

		sql.append("select count(*) as cnt ");
		sql.append("from mst_torihikisaki ");
		//torihikisaki
		if(!hd.getTorihikisaki().equals("")) {
			sql.append("where (");
			sql.append("torihikisaki_code = ? ");
			sql.append("or torihikisaki_name like ? ");
			sql.append(") ");
			param.add(hd.getTorihikisaki());
			param.add("%" + hd.getTorihikisaki() + "%");
		}
		//torihikisaki_kbn
		if(!hd.getTorihikisaki_kbn().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			} else {
				sql.append("and ");
			}
			sql.append("torihikisaki_kbn = ?");
			param.add(hd.getTorihikisaki_kbn());
		}

		Map<String,Object> map = jt.queryForMap(sql.toString(),param.toArray());

		double rec = Double.parseDouble(map.get("cnt").toString());
		return (int)(Math.ceil(rec / 25));

	}

	public int y_sansho_getPages(HatchuData hd) {
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList();

		sql.append("select count(*) as cnt ");
		sql.append("from mst_shohin ");
		//yakuhin
		if(!hd.getYakuhin().equals("")) {
			sql.append("where (");
			sql.append("jan_code = ? ");
			sql.append("or hanbai_name like ? ");
			sql.append(") ");
			param.add(hd.getYakuhin());
			param.add("%" + hd.getYakuhin() + "%");
		}
		//yakuhin_kbn
		if(!hd.getYakuhin_kbn().equals("")) {
			if(param.size() == 0) {
				sql.append("where ");
			} else {
				sql.append("and ");
			}
			sql.append("yakuhin_kbn = ?");
			param.add(hd.getYakuhin_kbn());
		}

		Map<String,Object> map = jt.queryForMap(sql.toString(),param.toArray());

		double rec = Double.parseDouble(map.get("cnt").toString());
		return (int)(Math.ceil(rec / 25));

	}

	public void insertHatchuData(HatchuData hd) {
		/*
		 * 新しい発注連番(hatchu_seq)の生成
		 * 方法：
		 * その年度の一番大きなhatchuseqを取得
		 * hatchu_seqの構造
		 * h + 年度 + 11桁の連番
		 * に合わせて生成する
		 */

		//入力された発注日の年度を取得(10月決算の会社の年度を計算する)
		String[] arrDay = hd.getHatchu_date().split("-");
		int nendo = Integer.parseInt(arrDay[0]);
		int month = Integer.parseInt(arrDay[1]);
		if(month <= 10) nendo--;
		//最新hatchu_seqを取得
		String sql  = "select max(hatchu_seq) as hatchu_seq from hatchu where nendo = '" + nendo + "'";
		Map<String,Object> map = jt.queryForMap(sql);

		String seq = "00000000001";

		if(!Objects.isNull(map.get("hatchu_seq"))) {
			String old_seq = map.get("hatchu_seq").toString();
			int int_seq = Integer.parseInt(old_seq.substring(5,16));
			int_seq++;
			seq = String.format("%011d", int_seq);
		}

		String new_seq = "h" + nendo + seq;

		//insert

		sql = "insert into hatchu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,now(),?)";
		List<Object> param = new ArrayList();

		param.add(new_seq);
		param.add(Integer.toString(nendo));
		param.add(hd.getTenpo_code());
		param.add(hd.getTorihikisaki_code());
		param.add(hd.getYakuhin_kbn());
		param.add(hd.getJan_code());
		param.add(hd.getYj_code());
		param.add("");
		param.add(hd.getHatchu_su());
		param.add(hd.getSuryo_kbn());
		param.add(hd.getNyuka_su());
		param.add(hd.getHatchu_kbn());
		param.add(hd.getHatchu_date());
		param.add(hd.getShori_kbn());
		param.add(hd.getShogo_flg());
		param.add("0");
		param.add(hd.getBiko());
		param.add(hd.getLogin_shain_code());
		param.add(hd.getLogin_shain_code());

		jt.update(sql,param.toArray());

	}

	public void getHatchuData(HatchuData hd) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("h.hatchu_seq,");
		sql.append("h.tenpo_code,");
		sql.append("h.torihikisaki_code,");
		sql.append("mt.torihikisaki_name,");
		sql.append("h.jan_code,");
		sql.append("h.yakuhin_kbn,");
		sql.append("h.yj_code,");
		sql.append("ms.hanbai_name,");
		sql.append("h.hatchu_su,");
		sql.append("h.suryo_kbn,");
		sql.append("h.nyuka_su,");
		sql.append("h.hatchu_kbn,");
		sql.append("h.hatchu_date,");
		sql.append("h.shori_kbn,");
		sql.append("shogo_flg,");
		sql.append("h.biko ");
		sql.append("from ");
		sql.append("hatchu h join mst_torihikisaki mt ");
		sql.append("on h.torihikisaki_code = mt.torihikisaki_code ");
		sql.append("join mst_shohin ms ");
		sql.append("on h.jan_code = ms.jan_code ");
		sql.append("where ");
		sql.append("hatchu_seq = ?");

		Map<String,Object> map = jt.queryForMap(sql.toString(),hd.getHatchu_seq());
		hd.setHatchu_seq(map.get("hatchu_seq").toString());
		hd.setTenpo_code(map.get("tenpo_code").toString());
		hd.setTorihikisaki_name(map.get("torihikisaki_name").toString());
		hd.setTorihikisaki_code(map.get("torihikisaki_code").toString());
		hd.setJan_code(map.get("jan_code").toString());
		hd.setYakuhin_kbn(map.get("yakuhin_kbn").toString());
		hd.setYj_code(map.get("yj_code").toString());
		hd.setHanbai_name(map.get("hanbai_name").toString());
		hd.setHatchu_su(map.get("hatchu_su").toString());
		hd.setSuryo_kbn(map.get("suryo_kbn").toString());
		hd.setNyuka_su(map.get("nyuka_su").toString());
		hd.setHatchu_kbn(map.get("hatchu_kbn").toString());
		hd.setHatchu_date(map.get("hatchu_date").toString().substring(0,10));
		hd.setShori_kbn(map.get("shori_kbn").toString());
		hd.setShogo_flg(map.get("shogo_flg").toString());
		hd.setBiko(map.get("biko").toString());
	}

	public void updHatchuData(HatchuData hd) {
		//発注日を変えられたかもしれないので、年度の再取得
		String[] arrDate = hd.getHatchu_date().split("-");
		int nendo = Integer.parseInt(arrDate[0]);
		int month = Integer.parseInt(arrDate[1]);
		if(month <= 10) nendo--;
		String strnendo = Integer.toString(nendo);

		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList();

		sql.append("update hatchu set ");
		sql.append("nendo = ?,");
		sql.append("tenpo_code = ?,");
		sql.append("torihikisaki_code = ?,");
		sql.append("yakuhin_kbn = ?,");
		sql.append("jan_code = ?,");
		sql.append("yj_code = ?,");
		sql.append("hatchu_su = ?,");
		sql.append("suryo_kbn = ?,");
		sql.append("nyuka_su = ?,");
		sql.append("hatchu_kbn = ?,");
		sql.append("hatchu_date = ?,");
		sql.append("shori_kbn = ?,");
		sql.append("shogo_flg = ?,");
		sql.append("biko = ?,");
		sql.append("updated_on = now(),");
		sql.append("updated_by = ? ");
		sql.append("where hatchu_seq = ?");

		param.add(strnendo);
		param.add(hd.getTenpo_code());
		param.add(hd.getTorihikisaki_code());
		param.add(hd.getYakuhin_kbn());
		param.add(hd.getJan_code());
		param.add(hd.getYj_code());
		param.add(hd.getHatchu_su());
		param.add(hd.getSuryo_kbn());
		param.add(hd.getNyuka_su());
		param.add(hd.getHatchu_kbn());
		param.add(hd.getHatchu_date());
		param.add(hd.getShori_kbn());
		param.add(hd.getShogo_flg());
		param.add(hd.getBiko());
		param.add(hd.getLogin_shain_code());
		param.add(hd.getHatchu_seq());

		jt.update(sql.toString(),param.toArray());
	}

	public void delHatchuData(HatchuData hd) {
		StringBuilder sql = new StringBuilder();
		List<Object> param = new ArrayList();

		sql.append("update hatchu set ");
		sql.append("delete_flg = '1',");
		sql.append("updated_on = now(),");
		sql.append("updated_by = ? ");
		sql.append("where hatchu_seq = ?");

		param.add(hd.getLogin_shain_code());
		param.add(hd.getHatchu_seq());

		jt.update(sql.toString(),param.toArray());
	}
}
