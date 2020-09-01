package kr.or.ddit.basic;
// lock 객체를 통한 동기화 처리 예제
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest18 {

	
	private int balance; // 잔액
	
	
	// lock 객체 생성 => 되도록이면 private final로 만든다
	private final Lock lock = new ReentrantLock();
	
	
	public int getBalance(){
		return balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public void deposit(int money){
		// Lock 객체는 lock()메서드로 락을 설정하고 
		// 반드시 unlock()메소드로 락을 해제해 주어야 한다.
		
		
		lock.lock(); // 락 설정 시작 ...
		
		balance += money;
		
		lock.unlock(); // 락을 해제
		
	}
	
	public boolean withdraw(int money){
		
		//만약 try-catch 블럭이 사용되는 부분에서
		//unlock()메서드를 호출할 때는 finally 영역에서 호출하도록 한다.
		
		
		
		boolean chk = false;
		try{
			
		
		
		lock.lock(); // 락 설정 시작 ...
		
		if(balance >=  money){  // 잔액 확인
			for(int i=1; i<= 100000000; i++){} // 시간지연용
			
			
			balance-=money;
			System.out.println("메서드 안에서 balance = "+ balance);
//			return true;
			
			chk = true;
		}else{
//			return false;
			chk = false;
		}
		}catch(Exception e){
			
		}finally{
			lock.unlock(); // 락을 해제
		}
		
		
		
		return chk;
	}
	
	
	
	public static void main(String[] args) {
		final ThreadTest18 account = new ThreadTest18();
		account.setBalance(10000);
		
		Runnable r1 = new Runnable(){
			public void run() {
				boolean result = account.withdraw(6000);
				System.out.println("쓰레드에서 result = " + result + ", balance = "+ account.getBalance() );
			}
		};
		
		
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		
		th1.start();
		th2.start();
		
	}

}