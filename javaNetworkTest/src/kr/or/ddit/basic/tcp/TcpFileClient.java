package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.nio.BufferOverflowException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

public class TcpFileClient {
	private Socket socket;
	private BufferedOutputStream bos;	//소켓 전송용
	private DataOutputStream dos;		//문자 전송용
	
	private BufferedInputStream bis;	//파일 읽기용
	
	public void cliectStart(){
		
//		전송할 파일을 이용한 file객체 생성
		/*File file = new File("d:/d_other/왕샤오.jpg");
		String fileName = file.getName();
		if(!file.exists()){
			System.out.println(fileName + "파일이 없습니다.");
			return;
		}*/
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("d:/d_other"));
		
		int result = fileChooser.showOpenDialog(new JPanel());
		String fileName = null;
		File file = null;
		
		if(result == JFileChooser.APPROVE_OPTION){
			File sendFile = fileChooser.getSelectedFile();
			fileName = sendFile.getName();
		} else {
			System.out.println("파일 전송을 취소합니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작");
			
//			처음 접속되면 파일이름을 전송한다.
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(fileName);
			
//			파일 읽기용 스트림 객체 생성
			bis = new BufferedInputStream(new FileInputStream(file));
			
//			서버로 전송할 스트림 객체 생성
			bos = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int len = 0;
			
//			파일 내용을 읽어와 소켓을 통해서 전송한다.
			while((len = bis.read(temp)) > 0){
				bos.write(temp, 0, len);
			}
			bos.flush();	//마지막에 버퍼 비우기 
			
			System.out.println("파일 전송 완료");
		} catch (Exception e) {
			System.out.println("파일 전송 실패");
			e.printStackTrace();
		} finally{
//			사용한 스트림 닫기
			if(dos!=null) try {dos.close();} catch (Exception e2) {}
			if(bis!=null) try {bis.close();} catch (Exception e2) {}
			if(bos!=null) try {bos.close();} catch (Exception e2) {}
			if(socket!=null) try {socket.close();} catch (Exception e2) {}
		}
	}
	
	
	public static void main(String[] args) {
		new TcpFileClient().cliectStart();
		
		/*
		 try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			System.out.println("파일을 가져옵니다.");
			
			DataInputStream fin = new DataInputStream(socket.getInputStream());
			FileOutputStream fout = new FileOutputStream("d:/d_other/download.jpg");
			
			int c;
			
			while( (c=fin.read()) != -1 ){
				fout.write(c);
			}
			
			fin.close();
			fout.close();
			
		} catch (Exception e) {
		}
		*/
	}
}
