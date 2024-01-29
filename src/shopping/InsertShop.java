package shopping;

import java.sql.SQLException;

import shopping.connect.IConnectImpl;

public class InsertShop extends IConnectImpl{
	
	public InsertShop() {	
		super("education", "1234");
	}
	
	/*
	쇼핑몰 과제에서 "상품입력" 프로그램 작성
	 	: PreparedStatement 객체를 사용한다.
	 */
	@Override
	public void execute() {
		//1.쿼리문작성
		String sql = "INSERT INTO sh_goods VALUES "
				+ "(goods_seq.NEXTVAL, ?, ?, SYSDATE, ?)";
		
		try {
			//2.prepared 객체 생성
			psmt = con.prepareStatement(sql);
					
			//3.인파라미터 설정
			psmt.setString(1, scanValue("상품명"));//상품명
			psmt.setString(2, scanValue("상품가격"));//상품가격
			psmt.setString(3, scanValue("상품코드"));//상품코드
			
			//4.쿼리실행
			int row = psmt.executeUpdate();
			
			//5.결과확인
			System.out.println(row +"행이 입력되었습니다.");
		}
		catch(SQLException e) {
			System.out.println("상품입력시 오류발생됨");
			e.printStackTrace();
		}
		finally {
			//6.자원반납
			close();
		}
	}
	
	public static void main(String[] args) {		
		new InsertShop().execute();
	}

}





