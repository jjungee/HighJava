package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


//
public class DBUtil3 {

   private static Logger logger = Logger.getLogger(DBUtil3.class);
   private static ResourceBundle bundle;
   
   
   
   
   static{
      bundle = ResourceBundle.getBundle("dbinfo");
      logger.info("ResourceBundle 객체 생성 - dbinfo.properties 파일 읽기");
      try {
         
         
         Class.forName(bundle.getString("driver"));
         logger.info("DB 드라이버 로딩 성공!!!");
         
      } catch (ClassNotFoundException e) {
         logger.error("드라이버 로딩 실패~ ==> " + e.getMessage());
      }
   }
   
   
   public static Connection getConnection(){
      
      
      try {
         logger.info("DB에 연결하기 ==> Connection객체 생성!!");
               
         return DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),bundle.getString("pass"));
      } catch (SQLException e) {
         logger.error("DB에 연결 실패  - > " + e.getMessage());
//         e.printStackTrace();
         return null;
      }
      
   }
   
}