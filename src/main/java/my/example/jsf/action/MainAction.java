package my.example.jsf.action;

import my.example.jsf.bean.SampleListBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class MainAction {
	
	@Autowired
	SampleListBean list;
	
	public String commit(){
		
		return "";
	}

}
