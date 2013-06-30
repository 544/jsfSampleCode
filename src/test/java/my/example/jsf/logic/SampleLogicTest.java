package my.example.jsf.logic;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SampleLogicTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SampleLogic target = ctx.getBean(SampleLogic.class);
		target.doIt();
//		fail("まだ実装されていません");
	}

}
