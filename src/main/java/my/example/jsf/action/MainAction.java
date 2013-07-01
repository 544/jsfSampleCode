package my.example.jsf.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import my.example.jsf.logic.SampleLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class MainAction {

	@Autowired
	private SampleLogic logic;

	private List<Map<String, Object>> list;

	private Map<String, Object> newData;

	@PostConstruct
	public void init() {
		// Reuquestスコープの場合は、変更・削除ボタン桜花で再検索が発生する。
		// 避けるためにはSessionにする または、userListをページに埋め込み復活させる必要がある
		list = logic.selectAll();
		newData = new HashMap<String, Object>();
	}

	/**
	 * 編集
	 * @param source
	 */
	public void edit(Map<String, Object> source) {
		logic.update(source);
	}

	/**
	 * 削除
	 * @param source
	 */
	public void remove(Map<String, Object> source) {
		logic.delete(source);
		list.remove(source);
	}

	/**
	 * 追加
	 */
	public void add() {
		logic.insert(newData);
		list = logic.selectAll();
		newData.clear();
	}

	/**
	 * @return logic
	 */
	public SampleLogic getLogic() {
		return logic;
	}

	/**
	 * @param logic セットする logic
	 */
	public void setLogic(SampleLogic logic) {
		this.logic = logic;
	}

	/**
	 * @return list
	 */
	public List<Map<String, Object>> getList() {
		return list;
	}

	/**
	 * @param list セットする list
	 */
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	/**
	 * @return newData
	 */
	public Map<String, Object> getNewData() {
		return newData;
	}

	/**
	 * @param newData セットする newData
	 */
	public void setNewData(Map<String, Object> newData) {
		this.newData = newData;
	}

}
