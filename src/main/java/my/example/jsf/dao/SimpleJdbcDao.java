package my.example.jsf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 汎用的なJdbc経由のデータアクセサ
 *
 * @author masato
 *
 */
@Service
public class SimpleJdbcDao {

	/**
	 * データソース 接続先はApplicationContextにて定義
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * SQL発行用のtemplate
	 */
	private NamedParameterJdbcTemplate jdbcTemplate;

	@PostConstruct
	private void init() {
		// jdbctemplateの初期化
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * 検索系のクエリを実行する。
	 *
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> query(String sql, Map<String, Object> hashMap) {

		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, hashMap);
		return result;
	}

	/**
	 * 更新系のクエリを実行する。
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, HashMap<String, Object> params) {

		return jdbcTemplate.update(sql, params);

	}

}
