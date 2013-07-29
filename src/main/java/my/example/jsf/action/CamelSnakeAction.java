package my.example.jsf.action;

import my.example.jsf.util.CaseFormatUtil;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CamelSnakeAction {

	private String input;
	private String camel;
	private String snake;

	public String submit() {

		this.camel = CaseFormatUtil.toCamel(input);
		this.snake = CaseFormatUtil.toSnake(input);

		return "";
	}

	/**
	 * @return input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input セットする input
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return camel
	 */
	public String getCamel() {
		return camel;
	}

	/**
	 * @param camel セットする camel
	 */
	public void setCamel(String camel) {
		this.camel = camel;
	}

	/**
	 * @return snake
	 */
	public String getSnake() {
		return snake;
	}

	/**
	 * @param snake セットする snake
	 */
	public void setSnake(String snake) {
		this.snake = snake;
	}


}
