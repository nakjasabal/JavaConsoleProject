package project1a.ver09;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PhoneBookDAO {
	
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private Statement stmt;
	
	public PhoneBookDAO() {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager
					.getConnection("jdbc:oracle:thin://@localhost:1521:orcl",
							"kosmo", "1234");
		}
		catch (Exception e) {
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
		}		
	}
	
	public void dataInput(PhoneInfo p) {
		
		try {			
			String sql = "INSERT INTO phonebook_tb "
					+ " (idx, name, mobile, birthday)"
					+ " VALUES "
					+ " (seq_phonebook.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getPhoneNumber());
			pstmt.setString(3, p.getBirthday());
			
			int hang = pstmt.executeUpdate();
			System.out.println(hang + " 행이 입력되었습니다.");
			//close();			
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("dataInput() 에러발생");
		}
	}
	
	public boolean dataSearch(String sName) {
		
		boolean isSearch = false;
		
		try {			
			String sql = "SELECT * FROM phonebook_tb "
					+ " WHERE name LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sName);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				isSearch = true;
				String pStr = String.format("%-10s %-10s %-10s", 
						rs.getString("name"),
						rs.getString("mobile"), 
						rs.getString("birthday"));
				System.out.println(pStr);
			}
			
			//System.out.println("데이터 검색이 완료되었습니다.");			
			//close();
		}
		catch(Exception e) {
			//e.printStackTrace();
			System.out.println("dataSearch() 에러발생");
		}
		
		return isSearch;
	}
	public int dataDelete(String dName) {
		
		int hang = 0;
		
		try {
			String sql = "DELETE FROM phonebook_tb WHERE name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dName);
			hang = pstmt.executeUpdate();
			if(hang>0)
				System.out.println(dName + "님이 삭제되었습니다.");
			
			//close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("dataDelete() 에러 발생");
		}
		
		return hang;
	}
	public void dataAllShow() {
		
		try {
			String sql = "SELECT * FROM phonebook_tb";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("이름 : " + rs.getString(1));
				System.out.println("핸드폰 번호 : " + rs.getString(2));
				System.out.println("생년월일 : " + rs.getString(3));
				System.out.println("---------------------------");
			}
			
			//close();			
		}
		catch(Exception e) {
			//e.printStackTrace();
			System.out.println("dataAllShow() 에러발생");
		}
	}
}
