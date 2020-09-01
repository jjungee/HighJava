package kr.or.ddit.basic;

public class ThreadTest21 {

	public static void main(String[] args) {

		DataBox dbox = new DataBox();
		
		InputThread inTh = new InputThread(dbox);
		OutputThread outTh = new OutputThread(dbox);
		
		inTh.start();
		outTh.start();
		
		
		
		
	}

}


class DataBox{
	private String data;
	
	// data값이 null이면 data에 문자열이 채워질때까지 기달니다.
	// data에 값ㅇ ㅣ있ㅇ므ㅕ 해당문자열 반환
	// 반환후엔 data를 null로 만든다.
	
	public synchronized String getData(){
		if(data == null){
			try{
				wait();
			}catch(InterruptedException e){
				
			}
		}
		
		String returnData = data;
		
		System.out.println("쓰레드가 읽어온 데이터 : " + returnData);
		
		data = null;
		
		notify();
		
		return returnData;
		
	}
	
	//data 변수에 값이 있으면 data가 null이 될때까지 기다린다.
	//data가 null이 되면 새로운 data를 저장한다
	
	public synchronized void setData(String data){
		if (this.data != null){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		this.data = data;
		System.out.println("쓰레드에서 새로 저장한 data = " + data);
		
		notify();
		
	}
	
}


class InputThread extends Thread{
	private DataBox dbox;

	public InputThread(DataBox dbox) {
		super();
		this.dbox = dbox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
			dbox.setData("공급데이터 : "+  i );
		}
		
	}
	
}

class OutputThread extends Thread{
	private DataBox dbox;

	public OutputThread(DataBox dbox) {
		super();
		this.dbox = dbox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
			String data = dbox.getData();
		}
		
	}
	
}