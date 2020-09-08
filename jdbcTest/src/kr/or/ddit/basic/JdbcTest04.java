package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ANDANTE", "java");
	        
	        System.out.println("계좌 정보 추가하기");
	        System.out.print("계좌번호 : ");
	        String bankNo = sc.nextLine();
	        
	        System.out.print("은행명 : ");
	        String bankName = sc.nextLine();
	        
	        System.out.print("예금주명 : ");
	        String bankUser = sc.nextLine();
	        
	        //statement객체를 이용하여 데이터 추가하기.
	        /*
	         String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date)"
	        		+ " values ('" + bankNo + "' , '" + bankName + "' , '" + bankUser + "', sysdate)";
	        
	        stmt = con.createStatement();
	        */
	        
	        //실행할 SQL문이 select문일 경우에는 executeQuery()메서드를 사용하고,
	        //실행할 SQL문이 select문이 아닐 경우에는 executeUpdate()메서드를 사용한다.
	        //executeUpdate()메서드의 반환값(작업에 성공한 레코드 수)
//	        int cnt = stmt.executeUpdate(sql);
	        
//	        PreparedStatement객체를 이용하여 처리하기
//	        sql문에서 데이터가 들어갈 자리에 ?로 표시
	        
	        String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date)"
	        		+ " values (?, ?, ?, sysdate)";
	        
	        //PreparedStatement객체 생성
//	        	=> 이 때 처리할 SQL문을 매개변수에 넘겨준다.
	        ps = con.prepareStatement(sql);
	        
	        //SQL문의 물음표 자리에 들어갈 데이터를 셋팅.
	        //형식) ps.set자료형 이름(물음표 번호, 데이터)
	        ps.setString(1, bankNo);
	        ps.setString(2, bankName);
	        ps.setString(3, bankUser);
	        
	        //데이터 셋팅이 완료되면 SQL문을 실행한다.
	        int cnt = ps.executeUpdate();
	        
	        System.out.println("반환값 : " + cnt);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
	    	  if(stmt != null) try { stmt.close();} catch (SQLException e2){ }
	      	  if(ps != null) try { ps.close();} catch (SQLException e2){ }
	   		  if(con != null) try { con.close();} catch (SQLException e2){ }
	     }
				
	}

}
