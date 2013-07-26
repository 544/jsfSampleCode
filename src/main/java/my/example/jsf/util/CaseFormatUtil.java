package my.example.jsf.util;

/**
 * 文字列形式をキャメルにしたりスネークにしたりするUtilクラス
 * @author tsukagoshi
 *
 */
public class CaseFormatUtil {

	/**
	 * 引数の文字列をキャメルケースに変換する。<br/>
	 * HOGE_PIYO => hogePiyo
	 * @param param
	 * @return
	 */
	public static String toCamel(String param) {
		boolean head = true; // 先頭文字かどうかのフラグ（先頭文字は小文字)
		boolean isUpper = false; // 文字を大文字にするかどうかのフラグ

		char[] result = new char[param.length()];
		int resultIdx = 0;

		char[] chars = param.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];

			if (c == '_') {
				// アンスコの次は大文字にする。
				isUpper = true;
				continue;
			}

			if (head) {
				// 先頭１文字は問答無用で小文字
				result[resultIdx++] = Character.toLowerCase(c);
				head = isUpper = false;
				continue;
			}

			if (isUpper) {
				result[resultIdx++] = Character.toUpperCase(c);
				isUpper = false;
			} else {
				result[resultIdx++] = Character.toLowerCase(c);
			}

		}
		return String.valueOf(result, 0, resultIdx);
	}

	/**
	 * 引数の文字列をスネークケースに変換する。<br/>
	 * @param param
	 * @return
	 */
	public static String toSnake(String param) {

		char[] result = new char[param.length() + (param.length() / 2)]; // アンスコが入る分のバッファを確保
		int resultIdx = 0;

		// 直前文字が小文字だったかどうかのフラグ
		boolean beforeIsLower = false;

		char[] chars = param.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];

			boolean currentIsLower = Character.isLowerCase(c);

			// 小文字→大文字　：　アンスコ入れる
			// 小文字→小文字　：　そのまま
			// 大文字→小文字　：　そのまま
			// 大文字→大文字　：　そのまま
			// 先頭→小文字　　：　そのまま
			// 先頭→大文字　　：　そのまま

			if (beforeIsLower && !currentIsLower) {
				result[resultIdx++] = '_';
			}

			result[resultIdx++] = Character.toUpperCase(c);

			beforeIsLower = currentIsLower;
		}

		return String.valueOf(result, 0, resultIdx);

	}

}
