package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제 1) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오.

public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ANDANTE", "java");
	        
	        String sql = "SELECT * FROM lprod WHERE lprod_id > ?";
	        ps = con.prepareStatement(sql);
	        
	        System.out.print("번호 입력 : ");
	        ps.setString(1, sc.nextLine());
	        
	        rs = ps.executeQuery();
	        
	        while(rs.next()){
	        	 System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
	        	 System.out.println("Lprod_gu : " + rs.getString(2));
	        	 System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
	        	 System.out.println("----------------------------------------");
	        	 
	         }
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
	    	  if(rs != null) try { rs.close();} catch (SQLException e2){ }
	      	  if(rs != null) try { ps.close();} catch (SQLException e2){ }
	   		  if(rs != null) try { con.close();} catch (SQLException e2){ }
	     }
		
	}

}
