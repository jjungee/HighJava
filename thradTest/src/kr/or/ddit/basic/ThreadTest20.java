package kr.or.ddit.basic;


/*
 * wait(), notify() 메서드를 이용하여 두 쓰레드가 번갈아 한번씩 실행되는 예제
 * 
 * wait(), notify(), notifyAll() 메서드는 동기화 영역에서만 사용 가능하다.
 * 
 * 
 */

public class ThreadTest20 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		
	}
}

// 공통으로 사용할 객체 클래스 작성
class WorkObject{
	public synchronized void test1(){
		System.out.println("test1()메서드 실행중 ...");
		
		notify();
		
		try{
			wait();
		}catch(InterruptedException e){
			
		}
	}

	public synchronized void test2(){
		System.out.println("++++++++++test2()메서드 실행중 ...");
	
		notify();
		
		try{
			wait();
		}catch(InterruptedException e){
			
		}
	}
	



}


// WorkObject의 test1()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObject;

	public ThreadA(WorkObject workObject) {
		super();
		this.workObject = workObject;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObject.test1();
		}
		
		synchronized (workObject) {
			workObject.notify();
		}
		
	}
}
//WorkObject의 test1()메서드만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObject;

	public ThreadB(WorkObject workObject) {
		super();
		this.workObject = workObject;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObject.test2();
		}
		
		synchronized (workObject) {
			workObject.notify();
		}
	}
}
