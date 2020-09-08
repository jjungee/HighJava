package kr.or.ddit.rmichat.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import kr.or.ddit.rmichat.inf.IClientChat;
import kr.or.ddit.rmichat.inf.IServerChat;

//클라이언트용 RMI인터페이스를 구현한 클래스
public class RmiChatClient extends UnicastRemoteObject implements IClientChat{
	//생성자
	
	
	protected RmiChatClient() throws RemoteException {
		
	}

	@Override
	public void displayMessage(String msg) throws RemoteException {
		System.out.println(msg);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			IClientChat client = new RmiChatClient();
			
			Registry reg = LocateRegistry.getRegistry("192.168.43.35", 1099);
			IServerChat server = (IServerChat)reg.lookup("rmiChat");
			
			//서버에 접속해서 현재 클라이언트 객체를 List에 추가하도록 한다.
			server.setClient(client);
			
			while (true) {
				//메시지를 입력받아서 서버에서 다른 클라이언트에게 메시지를 전달하는 메서드를 호출한다.
				String msg = scan.nextLine();
				if("/end".equals(msg)){
					break;
				}
				server.transMessage(msg);
			}
		} catch (Exception e) {
		}
	}

}
