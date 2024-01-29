package threeby3;

//x의 위치를 표현하는 객체
public class Xposition {
	//2차원배열의 세로,가로 인덱스
	int horizontal;
	int vertical;
	
	public Xposition(int vertical, int horizontal) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}
	
	public void showXPosition() {
		System.out.println("현재Index:"+ vertical +","+ horizontal);
	}
}