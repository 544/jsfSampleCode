package my.example.jsf.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		// リストが2行未満（ヘッダしかない）の場合は、空リストを返す。
		if (data.size() < 2) {
			return result;
		}

		// 一行目はヘッダ
		List<String> keys = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(data.remove(0), DEFALT_SEPARATOR);
		while(st.hasMoreTokens()) {
			keys.add(st.nextToken());
		}

		// 二行目以降はデータ
		for (String rowStr : data) {
			Map<String,Object> rowdata = new HashMap<String,Object>();

			String[] splitedRowStr = rowStr.split(DEFALT_SEPARATOR);
			if (splitedRowStr.length != keys.size() ){
				throw new RuntimeException("データ不正：" + rowStr);
			}

			Iterator<String> it = keys.iterator();
			for (int i = 0; i < splitedRowStr.length; i++) {
				String string = splitedRowStr[i];

				rowdata.put(it.next(), string);

			}
			result.add(rowdata);
		}


		return result;
	}

}
