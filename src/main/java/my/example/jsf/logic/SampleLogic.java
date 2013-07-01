package my.example.jsf.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.example.jsf.dao.SimpleJdbcDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleLogic {

	@Autowired
	private SimpleJdbcDao dao;

	public List<Map<String, Object>> select(){

		String sql = "SEL_SAMPLE.sql";
		List<Map<String, Object>> result = dao.query(sql, new HashMap<String, Object>());

		for (Object object : result) {
			System.out.println(object.toString());
		}

		return result;
	}

	public int delete() {
		String sql = "DEL_SAMPLE.sql";
		int rowcnt = dao.update(sql, new HashMap<String, Object>());
		System.out.println(rowcnt);
		return rowcnt;
	}

	public int insert() {
		String sql = "INS_SAMPLE.sql";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		params.put("name", "山田");
		params.put("info", "太郎");


		int rowcnt = dao.update(sql, params );
		System.out.println(rowcnt);
		return rowcnt;


	}


}
