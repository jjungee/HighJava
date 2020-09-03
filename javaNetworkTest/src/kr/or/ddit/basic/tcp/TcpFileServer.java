package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	
	private DataInputStream dis;		//문자 수신용
	private BufferedInputStream bis;	//데이터 수신용	
	private BufferedOutputStream bos;	//파일 저장용
	
	public void serverStart(){
		File saveDir = new File("d:/d_other/download");
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept();
			
			System.out.println("파일 다운로드 시작...");
			
//			클라이언트가 처음으로 보내는 데이터 받기 => 파일 이름이 처음에 수신된다.
			dis = new DataInputStream(socket.getInputStream());
			String fileName = dis.readUTF();
			
//			저장할 파일 위치와 파일 이름을 지정하여 File객체 생성
			File saveFile = new File(saveDir, fileName);
			
			//수신용 스트림 객체 생성
			bis = new BufferedInputStream(socket.getInputStream());
			
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			byte[] temp = new byte[1024];
			int len = 0;
			
//			소켓으로 수신된 데이터를 파일로 저장한다.
			while ((len = bis.read(temp)) > 0) {
				bos.write(temp, 0, len);
			}
			bos.flush();
			
			System.out.println("파일 다운로드 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패!!!");
			e.printStackTrace();
		} finally {
			if(dis!=null) try {dis.close();} catch (Exception e2) {}
			if(bis!=null) try {bis.close();} catch (Exception e2) {}
			if(bos!=null) try {bos.close();} catch (Exception e2) {}
			if(socket!=null) try {socket.close();} catch (Exception e2) {}
			if(server!=null) try {server.close();} catch (Exception e2) {}
		}
		
	}

	public static void main(String[] args) {
		new TcpFileServer().serverStart();
		
		/*
		try {
			ServerSocket serverSocket = new ServerSocket(7777);
			System.out.println("서버가 준비 중 입니다...");
			
//			접속 요청을 기다린다.
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트가 접속했습니다.");
			System.out.println("파일을 전송합니다.");
			
			FileInputStream fin = new FileInputStream("d:/d_other/왕샤오.jpg");
			DataOutputStream fout = new DataOutputStream(socket.getOutputStream());
			
			int c;
			while( (c=fin.read()) != -1 ){
				fout.write(c);
			}
			
			fin.close();
			fout.close();
						
		} catch (IOException e) {
		}
		*/
	}

}
