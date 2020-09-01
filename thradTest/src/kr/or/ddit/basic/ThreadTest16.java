package kr.or.ddit.basic;

public class ThreadTest16 {

	public static void main(String[] args) {
		ShareObject sobj = new ShareObject();
		
		TestThread th1 = new TestThread("Test1", sobj);
		TestThread th2 = new TestThread("Test2", sobj);
		
		th1.start();
		th2.start();
	}

}

class TestThread extends Thread{
	private ShareObject sobj;
	
	public TestThread(String name, ShareObject sobj) {
		super(name);
		this.sobj = sobj;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sobj.add();
		}
	}
}

class ShareObject{
	private int sum = 0;
	
	//동기화 처리하기 : synchronized
	public synchronized void add() { //1. 메서드 자체에 동기화 설정하기
		
		synchronized (this) { //동기화 방법2 : 동기화 블럭으로 설정한다.
			int n = sum;
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
		
	}
}