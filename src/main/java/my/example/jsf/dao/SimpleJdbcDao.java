package my.example.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
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
	 * 検索系のクエリを実行する。
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> query(String sql,
			Map<String, String> paramMap, Class elementType) {

		NamedParameterJdbcTemplate template1 = new NamedParameterJdbcTemplate(
				dataSource);
		List<Map<String, Object>> result = template1.queryForList(sql, paramMap, elementType);
		return result;
	}

}
