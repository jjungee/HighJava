package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		byte[] temp = new byte[4]; //크기가 4인 byte배열 생성 => 입력 데이터를 저장할 용도의 배열
		
//		입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		
//		출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			while (input.available() > 0) {  //읽어올 데이터가 있는지 여부 확인
//				input.read(temp);
//				output.write(temp);
				
				int len = input.read(temp);		//read(배열)메서드의 반환값 : 실제 읽어온 데이터 수
				
//				temp배열의 내용 중 0번째부터 len개의 데이터를 출력
				output.write(temp, 0, len);
				
				System.out.println("반복문 안에서 temp => " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			
			System.out.println("----------------------------------------------");
			System.out.println("inSrc : " + Arrays.toString(inSrc));
			System.out.println("outSrc : " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
			
		} catch (Exception e) {
		}
	}

}
