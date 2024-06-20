package banking.noco;

/*
예외처리 클래스를 만들때
	1.Exception 클래스를 상속받는다.
	2.출력하고자 하는 메시지를 super()를 통해 입력한다.
 */
public class MenuSelectException extends Exception
{
	public MenuSelectException() {
		super("메뉴 입력 예외발생됨.");
	}
}
