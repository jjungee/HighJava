package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

//lprod테이블에 새로운 데이터 추가하기

//lprod_gu와 lprod_nm은 직접 입력받아서 처리, lprod_id는 현제의 lprod_id 중 제일 큰 값보다 1크게 한다.
//입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받는다.
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
//	    	Class.forName("oracle.jdbc.driver.OracleDriver");
//	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ANDANTE", "java");
	        
	    	con = DBUtil.getConnection();
	    			
	        System.out.print("gu입력 : ");
	        String gu = sc.nextLine();
	        System.out.print("상품명 입력 : ");
	        String name = sc.nextLine();
	        
	    	String sql = "select * from lprod where lprod_gu = ?";
	    	ps = con.prepareStatement(sql);
	    	ps.setString(1, gu);
	    	rs = ps.executeQuery();
	    	
	    	String a = null;
	    	while(rs.next()){
	        	a = rs.getString("lprod_gu");
	        }
	    	
	    	int yb = 0;
	    	
	    	if(a != null){
	    		System.out.println("gu 중복");
	    	}else{
	    		ps = null;
	    		
	    		sql = "INSERT INTO lprod VALUES ((select NVL(MAX(lprod_id), 0) +1 from lprod), ?, ?)";
		    	ps = con.prepareStatement(sql);
		    	ps.setString(1, gu);
		    	ps.setString(2, name);
		    	yb = ps.executeUpdate();
	    	}
	    	
	    	System.out.println("업데이트 : " + yb);
	    			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			  if(rs != null) try { rs.close();} catch (SQLException e2){ }
	      	  if(ps != null) try { ps.close();} catch (SQLException e2){ }
	   		  if(con != null) try { con.close();} catch (SQLException e2){ }
	     }
	    
	    
	}

}
