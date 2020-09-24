package kr.or.ddit.basic;

import org.apache.log4j.Logger;

public class LogTest {
	//Logger클래스의 인스턴스 생성하기
	static Logger logger = Logger.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		//메시지 출력 방법
		// 형식1) Logger객체 변수.log레벨이름("출력할 메시지");
		logger.trace("[trace] trace레벨의 로그 메시지입니다.");
		logger.debug("[debug] debug레벨의 로그 메시지입니다.");
		logger.info("[info] trace레벨의 로그 메시지입니다.");
		logger.warn("[warn] warnning레벨의 로그 메시지입니다.");
		logger.error("[error] error레벨의 로그 메시지입니다.");
		logger.fatal("[fatal] fatal레벨의 로그 메시지입니다.");
		
		// 형식2) Logger객체 변수.log(Level.log레벨이름), "출력할 메시지");
	}

}
