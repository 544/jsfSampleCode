package my.example.jsf.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 汎用的なJdbc経由のデータアクセサ
 *
 * @author masato
 *
 */
@Service
public class SimpleJdbcDao {

	private static final String DEFAULT_SQLFILE_PATH = "sql";

	/** 実行時に強制的にSQLファイルを読みなおすかフラグのデフォルト値*/
	private static final boolean FORCE_READ_SQL_FLG_DEFALUT = true; //TODO 変更があったらSQLファイルを再読み込むようにしたい。

	/**
	 * データソース 接続先はApplicationContextにて定義
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * SQL発行用Springのjdbctemplate
	 */
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * キャッシュされたSQLクエリ
	 */
	private Map<String, String> cachedQuery = new HashMap<>();

	/**
	 * SQLクエリキャッシュのロック。読込時に同期化は不要なのでReadWriteLock
	 */
	private ReadWriteLock cachedQuerylock = new ReentrantReadWriteLock();

	@PostConstruct
	private void init() {
		// jdbctemplateの初期化
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

		//TODO  SQLファイルの事前読み込み

	}

	// ===========  SQLファイル読み込み部分
	/**
	 * SQLクエリ文字列を取得する。<br/>
	 * 既に読み込み済みの場合は、キャッシュから取得する。
	 * @param filename
	 * @return
	 */
	private String readQuery(String filename) {
		return readQuery(filename, FORCE_READ_SQL_FLG_DEFALUT);
	}

	/**
	 * SQLクエリを取得する。
	 * @param filename
	 * @return
	 */
	private String readQuery(String filename, boolean forceLoad) {

		if (forceLoad) {
			return readQueryByFile(filename);
		}

		// キャッシュからクエリ文を取得
		String query = null;
		cachedQuerylock.readLock().lock();
		try {
			query = cachedQuery.get(filename);

			if (StringUtils.isNotEmpty(query)) {
				return query;
			}
		} finally {
			cachedQuerylock.readLock().unlock();
		}

		// ファイルからSQLクエリを読み込み、キャッシュに設定
		query = readQueryByFile(filename);
		cachedQuerylock.writeLock().lock();
		try {
			this.cachedQuery.put(filename, query);
			return query;
		} finally {
			cachedQuerylock.writeLock().unlock();
		}
	}

	/**
	 * SQLファイルからクエリ文字列を取得する。
	 * @param filename
	 * @return
	 */
	private String readQueryByFile(String filename) {

		// ファイルからSQLクエリ読み込み
		InputStream myStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(new File(DEFAULT_SQLFILE_PATH, filename).toString());
		if (myStream == null) {
			throw new RuntimeException("ファイルがないよ。" + filename);
		}

		System.out.println(String.format("SQLファイルを読み込み[%s]", filename));

		try (BufferedReader r = new BufferedReader(new InputStreamReader(myStream, "UTF-8"))) {

			StringBuilder sb = new StringBuilder();
			char[] b = new char[1024];
			int line;
			while (0 <= (line = r.read(b))) {
				sb.append(b, 0, line);
			}
			return sb.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// キャッシュ
	}

	// ============ 以下クエリ発行部分
	/**
	 * 検索系のクエリを実行する。
	 *
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> query(String filename, Map<String, Object> hashMap) {

		List<Map<String, Object>> result = jdbcTemplate.queryForList(readQuery(filename), hashMap);
		return result;
	}

	/**
	 * 更新系のクエリを実行する。
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String filename, Map<String, Object> params) {

		return jdbcTemplate.update(readQuery(filename), params);

	}

}
