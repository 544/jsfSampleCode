package my.example.jsf.util;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class CsvReaderTest {

	@Test
	public void test2() {

		List<Map<String, Object>> result = CsvReader.read(new File("poke.csv"));

		for (Map<String, Object> map : result) {
			Set<Entry<String, Object>> set = map.entrySet();
			for (Entry<String, Object> entry : set) {
				System.out.print(entry.getKey() + ":" + entry.getValue() + ",") ;
			}
			System.out.println("");
		}
	}


	@Test
	public void test() {

		// ダミーのファイルを作成
		createDummyFIle(Arrays.asList("id,name,info", "1,ほげ,ぴよ", "2,aaa,あー"), "hoge.txt");

		// テスト実行
		List<Map<String, Object>> result = CsvReader.read(new File("hoge.txt"));

		// 検証
		Map<String, Object> rec1 = result.get(0);
		assertEquals("1", rec1.get("id"));
		assertEquals("ほげ", rec1.get("name"));
		assertEquals("ぴよ", rec1.get("info"));

		Map<String, Object> rec2 = result.get(1);
		assertEquals("2", rec2.get("id"));
		assertEquals("aaa", rec2.get("name"));
		assertEquals("あー", rec2.get("info"));

	}

	@Test
	public void test3() {

		// ダミーのファイルを作成
		createDummyFIle(Arrays.asList("id,name,info", "1,,ぴよ"), "hoge.txt");

		// テスト実行
		List<Map<String, Object>> result = CsvReader.read(new File("hoge.txt"));

		// 検証
		Map<String, Object> rec1 = result.get(0);
		assertEquals("1", rec1.get("id"));
		assertEquals("", rec1.get("name"));
		assertEquals("ぴよ", rec1.get("info"));

	}

	/**
	 * テスト用のダミーファイルを作成する。
	 * @param data
	 * @param filePath
	 */
	private void createDummyFIle(List<String> data, String filePath) {
		FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath(filePath);
		try (BufferedWriter bw = Files.newBufferedWriter(//
				path, Charset.forName("UTF-8"), StandardOpenOption.TRUNCATE_EXISTING)) {

			for (String str : data) {
				bw.write(str);
				bw.write("\n");
			}

			bw.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
