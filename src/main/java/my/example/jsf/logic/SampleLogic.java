package my.example.jsf.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.example.jsf.bean.SampleBean;
import my.example.jsf.dao.SimpleJdbcDao;

@Service
public class SampleLogic {
	
	@Autowired
	private SimpleJdbcDao dao;
	
	public void doIt(){
		
		String query = "SELECT * FROM SAMPLE;";
		List<Map<String, Object>> result = dao.query(query, new HashMap<String, String>(), SampleBean.class);
		
		for (Object object : result) {
			System.out.println(object.toString());
		}
	}

}
