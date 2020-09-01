package kr.or.ddit.basic;

import java.io.FileReader;

public class FileIOTest03 {

	public static void main(String[] args) {
//		문자 기반의 스트림을 이용한 파일 내용 읽어와 출력하기
		try {
//			문자 기반의 파일 입력 스트림 객체 생성
			FileReader fr = new FileReader("d:/d_other/out.txt");
			
			int c;
			
			while ((c = fr.read()) != -1) {
				System.out.print((char)c);
			}
			fr.close();
		} catch (Exception e) {
		}
		
	}

}
