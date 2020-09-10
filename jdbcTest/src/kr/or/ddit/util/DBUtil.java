package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//이 클래스는 JDBC드라이버를 로딩하고 DB시스템에 접속하여 Connection 객체를 반환하는 역할을 수행하는 클래스
public class DBUtil {
//	static초기화 블럭 => 드라이버 로딩을 수행한다.
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}
	//DB시스템에 접속한 후 접속 정보를 갖는  Connection객체를 반환하는 메서드
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ANDANTE", "java");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패!");
			return null;
		}
	}
}
