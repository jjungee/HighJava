package kr.or.ddit.basic;

import java.sql.Blob;

public class ThreadTest17 {
	private int balance;
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	//입금을 처리하는 메서드
	public void deposit(int moeny){
		balance += moeny;
	}
	
	//출금을 처리하는 메서드
	//출금이 성공하면 true, 실패하면 false로 반환
	//동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 안전하다.
	public synchronized boolean withdraw(int money){
//	public boolean withdraw(int money) {
		if(balance >= money){ //잔액확인
			for (int i = 1; i <= 10000000; i++) {} //시간 지연용
			
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		//은행의 입출금 작업을 쓰레드로 처리하는 예제
		final ThreadTest17 acount = new ThreadTest17();
		
		acount.setBalance(10000); //잔액 10000원으로 설정
		
		
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);
				System.out.println("쓰레드에서 result = " + result + ", balance = " + acount.getBalance() + "\n");
			}
		};
		
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		
		th1.start();
		th2.start();
	}

}
