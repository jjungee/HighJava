package kr.or.ddit.basic.tcp;

import java.net.Socket;

import javax.sound.sampled.Port;

public class TcpClient02 {

	public static void main(String[] args) {
//		Socket객체를 생성해서 서버에 접속 요청을 보내고 접속이 완료되면 생성된 Socket을 송신용 쓰레드와 수신용 쓰레드에 넘겨준다.
		
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			System.out.println("지금부터 메시지를 주고 받을 수 있습니다.");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
		} catch (Exception e) {
		}
		
	}

}
