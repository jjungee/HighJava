package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.View;

import kr.or.ddit.util.DBUtil;

//회원을 관리하는 프로그램
//(오라클 db의 mymember테이블 이용)

//아래 메뉴의 기능을 모두 구현하시오.(crud 구현 연습)
public class JdbcTest06 {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		while(true){
			System.out.println("===작업 선택===");
			System.out.println(" 1. 자료 추가");
			System.out.println(" 2. 자료 삭제");
			System.out.println(" 3. 자료 수정");
			System.out.println(" 4. 전체 자료 출력");
			System.out.println(" 0. 작업 끝");
			System.out.println("===작업 선택===");
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				formal();
				break;
			case 4:
				selectList();
				break;
			case 0:
				System.out.println("프로그램이 종료되었습니다");
				System.exit(0);
				break;
			}
		}
	}

	
	public static void insert(){
		List<Object> member = new ArrayList<>();
		
		System.out.print("아이디 입력 > ");
		String id = sc.nextLine();
		
		search(id);
		
		System.out.print("이름 입력 > ");
		String name = sc.nextLine();
		System.out.print("전화번호 입력 > ");
		String tel = sc.nextLine();
		System.out.print("주소 입력 > ");
		String addr = sc.nextLine();
		
		member.add(id);
		member.add(name);
		member.add(tel);
		member.add(addr);
		
		insertSql(member);
		
	}
	
	public static void delete(){
		List<Object> member = new ArrayList<>();
		
		System.out.print("삭제할 멤버의 아이디 입력 >");
		String id = sc.nextLine();
		
		member.add(id);
		
		deleteSql(member);
	}
	
	public static void formal(){
		List<Object> member = new ArrayList<>();
		
		System.out.print("수정할 멤버의 아이디 입력 > ");
		String id = sc.nextLine();
		
		System.out.println("1. 이름 수정\t2. 전화번호 수정\t3. 주소 수정\t4. 전체 수정");
		int input = Integer.parseInt(sc.nextLine());
		switch(input){
			case 1:
				System.out.print("수정할 이름 입력 > ");
				String name = sc.nextLine();
				member.add(name);
				break;
			case 2:
				System.out.print("수정할 전화번호 입력 > ");
				String tel = sc.nextLine();
				member.add(tel);
				break;
			case 3:
				System.out.print("수정할 주소 입력 > ");
				String addr = sc.nextLine();
				member.add(addr);
				break;
			case 4:
				System.out.print("수정할 이름 입력 > ");
				name = sc.nextLine();
				System.out.print("수정할 전화번호 입력 > ");
				tel = sc.nextLine();
				System.out.print("수정할 주소 입력 > ");
				addr = sc.nextLine();
				
				member.add(name);
				member.add(tel);
				member.add(addr);
				break;
		}
		member.add(id);
		formalSql(input, member);
	}
	

	public static int insertSql(List<Object> param){
		String sql = "insert into mymember values (?, ?, ?, ?)";
		return update(param, sql);
	}
	
	public static int deleteSql(List<Object> param){
		String sql = "delete mymember where mem_id = ?";
		return update(param, sql);
	}
	
	private static int formalSql(int input, List<Object> param) {
		String sql = "update mymember set";
		switch (input) {
		case 1:
			sql += " mem_name = ?";
			break;
		case 2:
			sql += " mem_tel = ?";
			break;
		case 3:
			sql += " mem_addr = ?";
			break;
		case 4:
			sql += " mem_name = ?, mem_tel = ?, mem_addr = ?";
			break;
		}
		sql += " where mem_id = ?";
		return update(param, sql);
	}
	
	public static int update(List<Object> param, String sql) {
		int result = 0;
			
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		try {
		    
		    con = DBUtil.getConnection();
		
		    ps = con.prepareStatement(sql);
		
		for (int i =0 ; i < param.size(); i++){
			ps.setObject(i+1, param.get(i));
		}
		
		result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (Exception e) {}
			if (ps != null) try {ps.close();} catch (Exception e) {}
			if (con != null) try {con.close();} catch (Exception e) {}
		}

		return result;
	}
	
	public static List<Map<String, Object>> selectList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ANDANTE", "java");;
		
	        String sql = "select * from mymember";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			System.out.println("아이디\t이름\t전화번호\t주소");
			while(rs.next()){
					System.out.println(rs.getString("mem_id") + "\t" + rs.getString("mem_name") + "\t" + 
					rs.getString("mem_tel") + "\t" + rs.getString("mem_addr"));
				}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();	} catch (Exception e) {}
			if (ps != null)try {ps.close();} catch (Exception e) {}
			if (con != null)try {con.close();} catch (Exception e) {}
		}

		return list;
	}
	
	public static boolean search(String id){
		
		return false;
	}
}
