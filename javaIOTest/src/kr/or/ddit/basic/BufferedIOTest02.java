package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedIOTest02 {

	public static void main(String[] args) {
//		문자 기반의 Biffered스트림 사용 연습
		
		try {
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);		//버퍼의 기본 크기(8192byte)로 객체 생성
			
			String temp = "";
			
			//readLine()메서드 => 한 줄씩 데이터를 읽어오는데 읽어올 데이터가 없으면 null을 반환한다.
			for (int i = 1; (temp = br.readLine()) != null; i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
			
			br.close();
		} catch (Exception e) {
		}
		
	}

}
