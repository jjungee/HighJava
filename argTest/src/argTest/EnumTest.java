package argTest;

import java.sql.ClientInfoStatus;

import org.omg.CORBA.PUBLIC_MEMBER;

 public class EnumTest {




	
//		enum(열거형) => 서로 관련 있는 상수들의 집합을 나타낸다
//				   => 클래스처럼 보이게 하는 상수
//				   => 열거형은 클래스처럼 독립된 java파일에 만들 수 있고
//					  하나의 java 파일에 클래스와 같이 만들 수 있고,
//					  클래스 안에 내부 클래스처럼 만들 수 있다
	
//			-열거형의 속성 및 메서드
//				name() => 열거형 상수의 이름을 문자열로 반환한다.
//				ordinal() => 열거형의 상수가 정의된 순서 값(index값)을 반환한다. (index는 0부터 시작)
//				valueOf("열거형 상수명") => 지정된 열거형에서 '열거형 상수명'과 일치하는 열거형 상수를 반환한다.
//				열거형 이름.상수명 => valueOf()메서드와 같다.
	
//			-열거형 선언하기
//				1. enum열거형 이름{상수명1, 상수명2, 상수명3...};
//				2. enum 열거형 이름{
//					상수명1(값들...), 상수명2(값들...)...상수명n(값들...) 
//				}
	
//			'값들'이 저장된 변수들 선언
//				private 자료형이 붙은 변수명 1;
				
//			열거형의 생성자를 만든다
//			열거형의 생성자는 '열거형 상수'에 '값들'을 셋팅하는 역할을 수행한다.
//			열거형 생성자는 묵시적으로 private이다.
//			
//			변수명은 'rkqtemf'과 개수가 같고 각각의 '값들'과 자료형이 맞아야된다.
//			private 열거형 이름(자료형 변수명1, 자료형 변수명2,...)type name = new type(arguments);
//				위에 선언된 변수들을 초기화하는 작업을 수행한다.
//			
//			구성된 '값들'을 외부에서 불러온 같들을
	
	
	 public enum Color{RED, GREEN, BLUE};
		public enum Count{ONE, TWO, THREE};
		
		public enum Season{
			봄("3월부터 5월까지",10),
			여름("6월부터 8월까지",20),
			가을("9월부터 11월까지",30),
			겨울("12월부터 2월까지",40);
			
			private String span;
			private int num;
			
			private Season(String months, int num){
				span = months;
				this.num = num;
			}
			
			
			public String getSpan(){
				return span;
			}
			
			public int getNum(){
				return num;
			}
			
			
		}
		
		
		public static void main(String[] args) {
//			System.out.println("RED : " + ConstTest.RED);
//			System.out.println("THREE : " + ConstTest.THREE);
	//
//			if(ConstTest.RED == ConstTest.TWO){
//				System.out.println("같다 .");
//			}else{
//				System.out.println("같지않다");
//			}
			
			
			Color mycol = Color.BLUE;
			Count mycnt = Count.valueOf("TWO");
			
			System.out.println("mycol : "+ mycol.name());
			System.out.println("mycnt : " +mycnt.name());
			
			System.out.println("mycol ordinal :" + mycol.ordinal());
			System.out.println("mycnt ordinal :" + mycnt.ordinal());
		
			if(mycol == Color.valueOf("BLUE")){
				System.out.println("같다 .");
				}else{
					System.out.println("같지않다");
				}
				
			Season ss = Season.valueOf("봄");
			System.out.println("name : " + ss.name());
			System.out.println("ordinal : " + ss.ordinal());
			System.out.println(ss.getSpan() + ss.getNum());
		
		
			for(Season time : Season.values()){
				System.out.println(time + " == " + time.name() + "==> " +time.getSpan() + ", "+ time.getNum());
				
			}
			
			for(Color col : Color.values()){
				System.out.println(col + " => " + col.ordinal());
			}
			
		}
}
