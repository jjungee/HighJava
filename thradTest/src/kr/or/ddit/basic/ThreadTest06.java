package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		DataInputThread th1 = new DataInputThread();
		CountDownThread th2 = new CountDownThread();
		
		th1.start();
		th2.start();
	}

}

// 데이터를 입력하는 쓰레드
class DataInputThread extends Thread{
	public static boolean inputCheck;  	//입력 여부를 확인하기 위한 변수 선언 > 쓰레드에서 공통으로 사용할 변수
	
	@Override
	public void run(){
		String str = JOptionPane.showInputDialog("아무거나");
		inputCheck = true; 	//입력이 완료되면 inputCheck를 true로 변경하기
		
		System.out.println("입력값 : " + str);
	}
	
}

class CountDownThread extends Thread{
	@Override
	public void run(){
		for (int i = 10; i > 0; i--) {
			
			//입력이 완료되었는지 여부를 검사해서 입력이 완료되면 쓰레드를 종료시킨다.
			if(DataInputThread.inputCheck == true){
				return; 	//run()메서드가 종료되면 쓰레드도 종료된다.
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000); //1초 동안 잠시 멈춘다.
			} catch (Exception e) {
			}
		}
		
		System.out.println("지정된 시간이 경과했습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
	
}