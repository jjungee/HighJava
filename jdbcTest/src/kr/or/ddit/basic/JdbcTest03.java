package kr.or.ddit.basic;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcTest03 {
	//문제 2) lprod_id값을 2개 입력받아서 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시요
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ANDANTE", "java");
	        
	        String sql = "SELECT * FROM lprod WHERE lprod_id > ? AND lprod_id < ?";
	        ps = conn.prepareStatement(sql);
	        
	        System.out.print("작은 수 입력 : ");
	        ps.setString(1, sc.nextLine());
	        System.out.print("큰 수 입력 : ");
	        ps.setString(2, sc.nextLine());
	        
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
	   		  if(rs != null) try { conn.close();} catch (SQLException e2){ }
	     }
	}

}
