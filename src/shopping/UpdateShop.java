package shopping;

import java.sql.SQLException;
import java.sql.Types;

import shopping.connect.IConnectImpl;

public class UpdateShop extends IConnectImpl{

	public UpdateShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {
			csmt = con.prepareCall("{call ShopUpdateGoods(?,?,?,?,?)}");
			
			csmt.setString(1, scanValue("상품명"));
			csmt.setString(2, scanValue("상품가격"));
			csmt.setString(3, scanValue("상품코드"));
			csmt.setString(4, scanValue("상품일련번호"));
			csmt.registerOutParameter(5, Types.NUMERIC);
			
			csmt.execute();
			
			int row = csmt.getInt(5);
			if(row==0) {
				System.out.println("해당상품이 없습니다.");
			}
			else {
				System.out.println(row +"개의 상품이 수정되었습니다.");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		 new UpdateShop().execute();
	}

}





