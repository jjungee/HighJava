package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
//		입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		
//		출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data;  //읽어온 데이터가 저장될 변수
		
//		read()메서드로 자료 읽기 => 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while ( (data = input.read()) != -1) {
			output.write(data);
		}
		
//		출력된 스트림의 데이터를 배열로 변환해서 저장하기
		outSrc = output.toByteArray();
		
		try {
			input.close();
			output.close();
		} catch (Exception e) {
		}
		
		System.out.println("insrc : " + Arrays.toString(inSrc));
		System.out.println("outsrc : " + Arrays.toString(outSrc));
	}

}
