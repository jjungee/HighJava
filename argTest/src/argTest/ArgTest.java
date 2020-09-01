package argTest;

public class ArgTest {

//	배열을 이용한 메서드
	public int sumArr(int[] data){
		
		int total = 0;
		for(int i = 0 ; i < data.length ; i++){
			total += data[i];
		}
		
		return total;
	}
	
//	가변형 인수 : 메서드의 매개변수의 개수가 호출될 때마다 다를 경우에 사용한다.
//			    가변형 인수는 메서드 내에서는 배열로 처리된다.
//			    가변형 인수는 하나의 메서드에 하나만 사용할 수 있다.
	
//	가변형 인수를 이용한 메서드
	public int subArg(int...data/*가변형 인수임*/){
		int total = 0;
		for(int i = 0 ; i < data.length ; i++){
			total += data[i];
		}
		return total;
	}
	
//	하나의 메서드에 2개 이상의 가변형 변수 사용 불가
//	public void argTest(int...t, float...k) {
//		
//	}
	
//	가변형 변수와 일반 변수를 같이 사용할 경우에는 가변형 변수를 제일 뒤에 배치해야한다.
	public void argTest(String name, int...data) {
		
	}
	
	public static void main(String[] args) {

	ArgTest test = new ArgTest();
	
	int[] nums = {1,2,3,4,5,6,7,8,9};	
	
	int result = test.sumArr(nums);
	System.out.println("result = " + result);
	
	System.out.println(test.sumArr(new int[]{10, 20, 30, 40}));
	System.out.println("---------------------------------------------------");
	
	System.out.println(test.sumArr(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
	System.out.println(test.sumArr(new int[]{10, 20, 30, 40}));
	}

}
