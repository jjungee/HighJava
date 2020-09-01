package kr.or.ddit.basic;

public class ThreadTest10 {
	
	public static void main(String[] args) {
//		쓰레드의 상태를 출력하는 프로그램
		StatePrintThread th = new StatePrintThread(new TargetThread());
		
		th.start();
		
	}
}

//쓰레드 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread{
	@Override
	public void run(){
		for (long i = 1L; i < 20000000000000L; i++);   //시간 지연 용
			
			try {
				Thread.sleep(1500);
			} catch (Exception e) {
			}
			
			for (long j = 1; j < 20000000000000L; j++); 
	}
}

class StatePrintThread extends Thread{
	private TargetThread target;
	
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run(){
		while (true) {
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			if(state == Thread.State.NEW){
				target.start();
			}
			
			if(state == Thread.State.TERMINATED){
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}
}