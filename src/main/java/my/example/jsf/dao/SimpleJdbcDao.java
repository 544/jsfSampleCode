package my.example.jsf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * 汎用的なJdbc経由のデータアクセサ
 * @author masato
 *
 */
@Service
public class SimpleJdbcDao  {

	/**
	 * データソース
	 * 接続先はApplicationContextにて定義
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * 検索系のクエリを実行する。
	 * @param sql
	 * @return
	 */
	public List<Object> query(String sql) {
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<Object> a = template.query(sql,new RowMapper<Object>() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
					System.out.println(rs.getString(2));
				return null;
			}});
		
		return a;
	}
	
}
