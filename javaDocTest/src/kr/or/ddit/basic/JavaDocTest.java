package kr.or.ddit.basic;

//JavaDoc 문서 만들기 예제를 위한 인터페이스

/**
 * 
 * @author 쩡이
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavadocTest.java<br>
 * 설명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * </p>
 * 
 * 수정이력<br>
 * ----------------------------<br>
 * 수정 일자 : 2020-09-07<br>
 * 작성자 : 쩡이<br>
 * 수정 내용 : 최초 생성<br>
 * ----------------------------<br>
 * 
 */
public interface JavaDocTest {
	/**
	 * 메서드명 : methodTest
	 * 설명 : 반환 값이 없는 메서드
	 * 
	 * @param a 첫번째 매개변수(정수형)
	 * @param b 두번째 매개변수(정수형)
	 */
	
	public void methodTest(int a, int b);
	
	/**
	 * 메서드 명 : methodAdd
	 * 설명 : 반환값이 있는 메서드
	 * 
	 * @param x 정수형 첫번째 매개변수
	 * @param y 정수형 두번째 매개변수
	 * @return 처리된 결과를 정수형으로 반환한다.
	 */
	public int methodAdd(int x, int y);
	
	/**
	 * 메서드명 : methodSub
	 * 설명 : 매개변수가 없는 메서드
	 * 
	 * @return 정수형으로 반환한다.
	 */
	public int methodSub();	
	
}
