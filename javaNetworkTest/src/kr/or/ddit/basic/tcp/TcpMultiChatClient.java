package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

   public static void main(String[] args) {
      new TcpMultiChatClient().clientStart();
   }

   // 클라이언트가 시작하는 메서드
   private void clientStart() {
      Socket socket = null;
      try {
//         String serverIp = "localhost";
         String serverIp = "192.168.43.35";
         
         socket = new Socket(serverIp, 7777);
         
         System.out.println("서버에 연결되었습니다.");
         System.out.println();
         
         ClinetReceiver cr = new ClinetReceiver(socket);
         ClinetSender cs = new ClinetSender(socket);
         
         cr.start();
         cs.start();
         
      } catch (Exception e) {
         // TODO: handle exception
      }
   } // clinetStart() 끝
   
   
   // 메시지 전송용 쓰레드
   class ClinetSender extends Thread{
      private Socket socket;
      private DataOutputStream dos;
      private DataInputStream dis;
      private String name;
      private Scanner scan;
      
      //생성자
      public ClinetSender(Socket socket) {
         this.socket = socket;
         scan = new Scanner(System.in);
         
         try {
            //전송용 스트림
            dos = new DataOutputStream(socket.getOutputStream());
            
            
            //피드백 수신용 스트림
            dis = new DataInputStream(socket.getInputStream());
            
            if(dos!=null){
               //클라이언트 프로그램이처음 실행되면 자신의 대화명을 입력 받아 서버로 전송하고 
               // 대화명의 중복여부를 feedback 받는다.
               System.out.print("대화명 : ");
               String irum = scan.nextLine();
               
               while(true){
                  dos.writeUTF(irum); // 대화명을 서버로 전송...
                  
                  String feedback = dis.readUTF(); // 대화명의 중복여부를 서버로부터 확인
                  if("이름 중복".equals(feedback)){ // 대화명이 중복될때
                     System.out.println(irum + "은 이미 사용중인 이름입니다.");
                     System.out.println("다른 대화명을 입력하세요 : ");
                     System.out.print("대화명 : ");
                     irum = scan.nextLine();
                  }else{ // 대화명이 중복되지 않을때
                     name = irum;
                     System.out.println(irum + " 으로 대화방에 입장했습니다.");
                     break;
                  }
               } // while문
               
               
               
               
            }
            
            
            
         } catch (Exception e) {
            // TODO: handle exception
         }
         
      } // 생성자 끝
      
      @Override
      public void run() {
         
         try {
            while(dos!=null){
               // 대화내용 을 입력해서 서버로 전송한다.
               dos.writeUTF("[" + name +"] " +  scan.nextLine());
               
               
            }
         } catch (Exception e) {
            // TODO: handle exception
         }
      
      
      }
      
   }// 전송용 쓰레드 끝
   
   //수신용 쓰레드
   class ClinetReceiver extends Thread{
      private Socket socket;
      private DataInputStream dis;
      
      public ClinetReceiver(Socket socket) {
         this.socket = socket;
         try {
            dis = new DataInputStream(socket.getInputStream());
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
      }// 생성자 끝
      
      
      @Override
      public void run() {
         try {
            while(dis!=null){
               // 서버에서 받은ㅁ ㅔ세지를 화면에 출력한다
               System.out.println(dis.readUTF());
            }
            
         } catch (Exception e) {
            // TODO: handle exception
         }
         
         
      }
      
   }

   
}