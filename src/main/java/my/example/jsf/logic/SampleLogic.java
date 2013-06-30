package my.example.jsf.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.example.jsf.dao.SimpleJdbcDao;

@Service
public class SampleLogic {
	
	@Autowired
	private SimpleJdbcDao dao;
	
	public void doIt(){
		
		String query = "SELECT * FROM SAMPLE;";
		dao.query(query);
		
	}

}
