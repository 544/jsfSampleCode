package my.example.jsf.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaseFormatUtilCamelTest {

	/**
	 * HOGE_PIYO_FUGA => hogePiyoFuga
	 */
	@Test
	public void test1() {

		String param = "HOGE_piyo_FUGA";
		Object result = CaseFormatUtil.toCamel(param);

		assertEquals("hogePiyoFuga", result);
	}

	@Test
	public void test2() {

		String param = "___aaa";
		Object result = CaseFormatUtil.toCamel(param);

		assertEquals("aaa", result);
	}

	@Test
	public void test3() {

		String param = "___A_bc";
		Object result = CaseFormatUtil.toCamel(param);
		assertEquals("aBc", result);
	}

	@Test
	public void test4() {
		String param = "ABC";
		Object result = CaseFormatUtil.toCamel(param);
		assertEquals("abc", result);

	}



}
