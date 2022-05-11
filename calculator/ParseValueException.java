package calculator;

public class ParseValueException extends Exception {
	public ParseValueException() {
		super("Неверный формат строки");
	}
}
