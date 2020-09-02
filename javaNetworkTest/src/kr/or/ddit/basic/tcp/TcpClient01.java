package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
//		현재 자신의 컴퓨터를 나타내는 방법들
//		1. 원래의 IP주소 : ex) 192.168.43.5
//		2. 고정된 IP주소 : 127.0.0.1 (자신의 컴퓨터를 가리키는 고유한 IP주소)
//		3. 원래의 컴퓨터 이름 : ex) SEM-PC
//		4. 지정된 컴퓨터 이름 : localhost
		
		String serverInfo = "localhost";
		
		System.out.println(serverInfo + " 서버에 연결 중입니다...");
		
//		서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
//		Socket객체가 생성이 완료되면 해당 서버에 요청 신호를 보낸다.
		Socket socket = new Socket(serverInfo, 7777);
		
//		이 부분은 서버와 연결이 완료되어야만 실행되는 곳이다.
		System.out.println("서버에 연결되었습니다.");
		System.out.println();
		
		System.out.println("연결된 서버 정보... (상대편 컴퓨터 정보)");
		System.out.println("IP 주소 " + socket.getInetAddress().getHostAddress());
		System.out.println("Port 번호 " + socket.getPort());
		System.out.println("--------------------------------------");
		System.out.println();
		
		System.out.println("접속한 클라이언트 정보...(내 컴퓨터 정보)");
		System.out.println("IP 주소 " + socket.getLocalAddress());
		System.out.println("Port 번호 " + socket.getLocalPort());
		System.out.println();
		
//		서버에서 보내온 메시지 받아서 화면에 출력하기
//		InputStream객체 생성	=> Socket의 getInputStream()메서드를 이용한다.
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
//		메시지 받아서 출력하기
		System.out.println("서버에서 온 메시지 : " + dis.readUTF());
		
		System.out.println("작업을 종료합니다...");
//		소켓과 스트림 닫기
		dis.close();
		socket.close();
	}

}
