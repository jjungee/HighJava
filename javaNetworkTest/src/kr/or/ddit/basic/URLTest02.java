package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
//		URLConnection => 애플리케이션과 URL간의 통신 연결을 위한 클래스
//		특정 서버의 정보와 파일 내용을 가져올 수 있다.
		
		URL url = new URL("https://www.naver.com/index.html");
		
//		URLConnectin객체 구하기 => url객체의 openConnection()메서드를 이용한다.
		URLConnection urlCon = url.openConnection();
		
//		Header정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		for(String HeaderKey : headerMap.keySet()){
			System.out.println(HeaderKey + " : " + headerMap.get(HeaderKey));
		}
		System.out.println("===============================================");
		
//		해당 문서의 내용 가져오기(예제에서는 index.html문서 내용 가져오기)
		
//		방법1 : URLConnection객체 이용
//			파일의 내용을 가져오기 위한 스트림 객체 생성
		
/*		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
//		자료를 읽어와 출력하기
		while (true) {
			String str = br.readLine();
			if(str == null){
				break;
			}
			System.out.println(str);
		}
//		스트림 닫기
		br.close();*/
		
//		방법 2 : URL객체의 openStream()을 이용한 방법
//			      입력용 스트림 객체 구하기
		InputStream is2 = url.openStream();
		InputStreamReader isr2 = new InputStreamReader(is2, "utf-8");
		BufferedReader br2 = new BufferedReader(isr2);
		
		while (true) {
			String str = br2.readLine();
			if(str == null){
				break;
			}
			System.out.println(str);
		}
//		스트림 닫기
		br2.close();
	}

}
