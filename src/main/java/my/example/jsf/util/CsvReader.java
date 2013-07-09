package my.example.jsf.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author tsukagoshi
 *
 */
public final class CsvReader {

	private static final String DEFALT_SEPARATOR = ",";
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	/**
	 * CSVファイルを読み込みMapにして返す。<br/>
	 * 一行目がマップのキーになる。
	 * @param file
	 * @return
	 */
	public static List<Map<String, Object>> read(File file) {
		assert (file != null);

		// ファイルを文字列のリストとして読み込む
		List<String> data = null;
		try {
			data = Files.readAllLines(file.toPath(), DEFAULT_CHARSET);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<Map<String, Object>> result = new ArrayList<>();

		// リストが2行未満（ヘッダしかない）の場合は、空リストを返す。
		if (data.size() < 2) {
			return result;
		}

		// 一行目はヘッダ
		List<String> keys = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(data.remove(0), DEFALT_SEPARATOR);
		while(st.hasMoreTokens()) {
			keys.add(st.nextToken());
		}

		// 二行目以降はデータ
		for (String string : data) {
			Map<String,Object> rowdata = new HashMap<>();
			StringTokenizer st2 = new StringTokenizer(string, DEFALT_SEPARATOR);

			for (String key : keys) {
				if (!st2.hasMoreTokens()) {
					throw new RuntimeException("データ不正：" + string);
				}
				rowdata.put(key, st2.nextToken());
			}
			result.add(rowdata);
		}


		return result;
	}

}
