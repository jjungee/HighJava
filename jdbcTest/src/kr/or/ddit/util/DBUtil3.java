package kr.or.ddit.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


//
public class DBUtil3 {

   private static ResourceBundle bundle;
   
   
   
   
   static{
      bundle = ResourceBundle.getBundle("dbinfo");
      
      try {
         
         
         Class.forName(bundle.getString("driver"));
         
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버 로딩 실패");
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   
   public static Connection getConnection(){
      
      
      try {
         return DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),bundle.getString("pass"));
      } catch (SQLException e) {
         System.out.println("오라클 연결 실패");
//         e.printStackTrace();
         return null;
      }
      
   }
   
}