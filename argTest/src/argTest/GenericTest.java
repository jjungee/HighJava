package argTest;

//		제네릭을 사용하지 않는 클래스 작성
class NonGenericClass{
	private Object val;
	
	public void setVal(Object val){
		this.val = val;
	}
	
	public Object getVal(){
		return val;
	}
	
}

//제네릭을 사용하는 클래스 작성
class MyGeneric<T>{
	private T val;  //변수 선언
	
	public void setVal(T val) {
		this.val = val;
	}
	
	public T getVal() {			//메서드의 반환값 타입에 사용
		return val;
	}
}

//		제네릭 클래스 만들기
//		 -> class 클래스명<제내릭타입 글자>{
//			 제네릭 타입 글자 변수명; > 변수 선언에 제네릭을 사용할 경우
			
//			제네릭 타입 글자 메서드명(파라미터 변수) {반환 값이 있는 메서드에서 사용할 경우
			
//			return 값;
//			}
//			반환값타입 메서드명(제네릭타입 글자 변수명, ...){} 메서드의 파라미터 변수에 사용할 경우
//		 }
public class GenericTest{
	
	public static void main(String[] args){
		NonGenericClass ngc1 = new NonGenericClass();
		ngc1.setVal("아아아아");
		
		NonGenericClass ngc2 = new NonGenericClass();
		ngc2.setVal(718);
		
		String rtnStr = (String)ngc1.getVal();
		System.out.println("문자열 반환값 : " + rtnStr);
		int rtnInt = (int)ngc2.getVal();
		System.out.println("정수형 반환값 : " + rtnInt);
//-----------------------------------------------------------------------------------------------------------------------------------------------------
		
		MyGeneric<String> mgc1 = new MyGeneric<>();
		mgc1.setVal("진샤샤");
		
		MyGeneric<Integer> mgc2 = new MyGeneric<>();
		mgc2.setVal(718);
		
		rtnStr = mgc1.getVal();
		System.out.println("제네릭 문자열 반환 : " + rtnStr);
		
		rtnInt = mgc2.getVal();
		System.out.println("제네릭 정수형 반환 : " + rtnInt);
	}
	
}