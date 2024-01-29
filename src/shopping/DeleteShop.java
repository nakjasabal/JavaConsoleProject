package shopping;

import java.sql.Types;

import shopping.connect.IConnectImpl;

public class DeleteShop extends IConnectImpl{

	public DeleteShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {
			//삭제 프로시저 호출
			csmt = con.prepareCall("{call ShopDeleteGoods(?,?)}");
			
			//인파라미터설정
			csmt.setString(1, scanValue("상품일련번호"));
			//아웃파라미터설정
			csmt.registerOutParameter(2, Types.NUMERIC);
			
			//프로시저 실행
			csmt.execute();
			
			//아웃파라미터를 통해 결과값 반환
			int affected = csmt.getInt(2);
			if(affected==0){				
				System.out.println("해당상품이 없습니다.");	
			}
			else{
				System.out.println(affected +"개의 제품이 삭제되었습니다.");	
			}					
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}		
	}
	
	public static void main(String[] args) {
		 new DeleteShop().execute();
	}

}
