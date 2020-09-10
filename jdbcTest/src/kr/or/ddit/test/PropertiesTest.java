package kr.or.ddit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//파일로 저장된 properties 파일을 읽어와 Properties객체로 처리하기
public class PropertiesTest {

	public static void main(String[] args) {
//		읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		File file = new File("res/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
//			파일 내용을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			
//			입력용 스트림을 이용하여 파일 내용을 읽어와 Properties객체에 저장하기
			prop.load(fin);		//파일 내용을 읽어와 key값과 value값을 분류한 후 Properties객체에 추가한다.
			
//			읽어온 정보 출력해보기
			System.out.println("driver : " + prop.getProperty("driver"));
			System.out.println("url : " + prop.getProperty("url"));
			System.out.println("user : " + prop.getProperty("user"));
			System.out.println("pass : " + prop.getProperty("pass"));
			
			fin.close();
		} catch (IOException e) {
		}
	}

}
