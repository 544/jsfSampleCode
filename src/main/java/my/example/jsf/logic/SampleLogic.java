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

	public List<Map<String, Object>> selectAll() {

		List<Map<String, Object>> result = dao.query("SEL_SAMPLE.sql", new HashMap<String, Object>());

		for (Object object : result) {
			System.out.println(object.toString());
		}

		return result;
	}

	public int delete(Map<String, Object> param) {
		int rowcnt = dao.update("DEL_SAMPLE.sql", param);
		System.out.println(rowcnt);
		return rowcnt;
	}

	public int insert(Map<String, Object> param) {
		int rowcnt = dao.update("INS_SAMPLE.sql", param);
		System.out.println(rowcnt);
		return rowcnt;
	}

	public int update(Map<String, Object> param) {
		int rowcnt = dao.update("UPD_SAMPLE.sql", param);
		System.out.println(rowcnt);
		return rowcnt;
	}

}
