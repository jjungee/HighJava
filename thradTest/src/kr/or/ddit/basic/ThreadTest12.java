package kr.or.ddit.basic;

public class ThreadTest12 {

	public static void main(String[] args) {
//		3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
//		출력을 끝낸 순서대로 결과를 나타내는 프로그램
		
		DisplayChar[] disp = new DisplayChar[]{
			new DisplayChar("진성호"),
			new DisplayChar("이루빈"),
			new DisplayChar("문제현")
			
		};
		
		for (DisplayChar dc : disp) {
			dc.start();
		}
		
		for (DisplayChar dc : disp) {
			try {
				dc.join();
			} catch (Exception e) {
			}
		}
		
		System.out.println();
		System.out.println("경기 결과 : " + DisplayChar.setRank);
		
	}

}

// A~Z까치 출력하는 메서드
class DisplayChar extends Thread{
	public static String setRank = "";
	private String name;
	
	public DisplayChar(String name) {
		this.name = name;
	}
	
	@Override
	public void run(){
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + "의 출력 문자 : " + c);
			try {
//				201~500사이의 난수 값을 이용하여 일시정지 시키기
				Thread.sleep((int)(Math.random() * 300 + 201));
			} catch (Exception e) {
			}
		}
		System.out.println(name + " 출력 끝...");
		
		DisplayChar.setRank += name + "  ";
	}
	
}