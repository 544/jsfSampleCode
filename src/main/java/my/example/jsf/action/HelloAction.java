package my.example.jsf.action;

import my.example.jsf.bean.HelloBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class HelloAction {

	@Autowired
	private HelloBean helloBean;


	public String submit() {

		helloBean.setName("["
				+ helloBean.getName() + "]");

		return "./welcome.xhtml";
	}

}
