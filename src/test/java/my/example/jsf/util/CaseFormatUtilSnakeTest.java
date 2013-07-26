package my.example.jsf.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaseFormatUtilSnakeTest {

	/**
	 * HOGE_PIYO_FUGA => hogePiyoFuga
	 */
	@Test
	public void test1() {

		String param = "hogePiyo";
		Object result = CaseFormatUtil.toSnake(param);

		assertEquals("HOGE_PIYO", result);
	}


	@Test
	public void test2() {
		String param = "abc";
		Object result = CaseFormatUtil.toSnake(param);

		assertEquals("ABC", result);

	}


	@Test
	public void test3() {
		String param = "ABC";
		Object result = CaseFormatUtil.toSnake(param);

		assertEquals("ABC", result);

	}

	@Test
	public void test4() {
		String param = "aaaBBBccc";
		Object result = CaseFormatUtil.toSnake(param);

		assertEquals("AAA_BBBCCC", result);

	}

	@Test
	public void test5() {
		String param = "aBcD";
		Object result = CaseFormatUtil.toSnake(param);

		assertEquals("A_BC_D", result);

	}


}
