package banking8.threeby3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDAO {
	
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private Statement stmt;
	
	public AccountDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager
					.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
							"education", "1234");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		}
	}
	public void close() {
		
		try {
			if(rs!=null) rs.close();
			if(con!= null) con.close();
			if(cstmt!= null) cstmt.close();
			if(stmt!= null) stmt.close();
			if(pstmt!=null) pstmt.close();
			//System.out.println("자원 반납 완료");
		}
		catch(Exception e) {
			System.out.println("자원 반납시 오류 발생");
			//e.printStackTrace();
		}		
	}
	
	public void makeAccount(Account a) {
		
		try {
			String sql = "INSERT INTO banking_tb "
					+ " (idx, accNumber, name, balance) "
					+ " VALUES ( "
					+ " seq_banking.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAccountID());
			pstmt.setString(2, a.getCustomName());
			pstmt.setInt(3, a.getAccMoney());
			
			int hang = pstmt.executeUpdate();
			System.out.println(hang + "개의 계좌를 생성하였습니다");
		}
		catch(Exception e) {
			System.out.println("makeAccount() 에러발생");
			//e.printStackTrace();
		}
	}
	public int depositMoney(String accID, int money) {
		
		int hang = 0;
		try {
			String sql = "UPDATE banking_tb SET balance = balance + ? "
					+ " WHERE accNumber = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, accID);
			pstmt.setInt(1, money);
			hang = pstmt.executeUpdate();
			if(hang>0)
				System.out.println(accID+ " 계좌에 " + money + " 원이 입급되었습니다.");
		}
		catch(Exception e) {
			System.out.println("depositMoney() 에러 발생");
			//e.printStackTrace();
		}
		
		return hang;
	}
	public int withdrawMoney(String accID, int money) {
		
		int hang = 0;
		try {
			String sql = "UPDATE banking_tb SET balance = balance - ? "
					+ " WHERE accNumber = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, accID);
			pstmt.setInt(1, money);
			hang = pstmt.executeUpdate();
			if(hang>0)
				System.out.println(accID+ " 계좌에서 " + money + " 원이 출금되었습니다.");
		}
		catch(Exception e) {
			System.out.println("depositMoney() 에러 발생");
			//e.printStackTrace();
		}
		
		return hang;
		
	}
	public void showAccInfo() {
		
		try {
			String sql = "SELECT * FROM banking_tb ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("------------");
				System.out.println("계좌번호 : " + rs.getString("accNumber"));
				System.out.println("고객 이름 : " + rs.getString("name"));
				System.out.println("잔고 : " + rs.getInt("balance"));
				System.out.println("------------");
			}
		}
		catch (Exception e) {
			System.out.println("showAccInfo() 에러발생");
			//e.printStackTrace();
		}
	}	
}
