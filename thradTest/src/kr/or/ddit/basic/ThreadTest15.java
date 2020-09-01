package kr.or.ddit.basic;

//쓰레드에서 객체를 공통으로 사용하는 예제 연습


/*
 * 원주율을 계산만하는 쓰레드와 원주율을 출력만 하는쓰레드가 있다.
 * 원주율을 저장하는 객체가 필요
 * 이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.
 * 
 */

public class ThreadTest15 {

	public static void main(String[] args) {

		
		ShareData sd = new ShareData();
		
		CalcPIThread calc= new CalcPIThread(sd);
		
		PrintPIThread print = new PrintPIThread(sd);
		
		calc.start();
		print.start();
		
		
		
		
	}

}


// 원주율을 관리하는 클래스 작성(공통으로 사용할클래스)

class ShareData{
	public double result; //계산된 원주율이 저장 될 변수
	public volatile boolean isOk; // 계산이 완료되었는지 여부를 나타내는 변수(계산완료되면 true가 된다)
	
}


// 원주율을 계산만 하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;

	public CalcPIThread(ShareData sd) {
		super();
		this.sd = sd;
	}
	@Override
	public void run() {

	double sum = 0.0;
	
	for (int i = 1; i<=100000000; i=i+2){
		if( (i / 2) % 2 ==0 ){
			sum+=(1.0/i);
		}else{
			sum-=(1.0/i);
		}
	}
	
	sd.result = sum *4;
	sd.isOk = true;
	System.out.println("계산완료");
	
	}
	
}


//원주율 계산이 완료되면 출력만 하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;

	public PrintPIThread(ShareData sd) {
		super();
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true){
			if(sd.isOk == true){
				break;
			}
		}
	
		// 계산된 원주율 출력
		System.out.println();
		System.out.println("결과 : " + sd.result);
	}
	
}