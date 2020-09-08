package kr.or.ddit.rmichat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
//	클라이언트용 인터페이스  => 서버가 각각의 클라이언트에 메시지를 전송할 때 사용하는 인터페이스

public interface IClientChat extends Remote{
//	서버가 보내온 메시지를 화면에 출력하는 메서드
	public void displayMessage(String msg) throws RemoteException;
	
	
}
