package kr.or.ddit.mvc.controller;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import oracle.security.o5logon.a;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.AES256Util;
import kr.or.ddit.util.CryptoUtil;

public class MemberController {
	
	private Scanner scan;
	private IMemberService service;	// Service객체 변수 선언
	private AES256Util aes256;
	
	public MemberController() throws UnsupportedEncodingException {
		service = MemberServiceImpl.getInstance();
		scan = new Scanner(System.in);
		aes256 = new AES256Util();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		new MemberController().startMember();
	}
	
	public void startMember() throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException{
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 :			// 추가
					insertMember();
					break;
				case 2 :			// 삭제
					deleteMember();
					break;
				case 3 :			// 수정
					updateMember();
					break;
				case 4 :			// 전체 출력
					displayMember();
					break;
				case 5 :			// 수정2
					updateMember2();
					break;
				case 0 :			// 종료
					System.out.println("작업을 마칩니다.");
					return;
				default : 
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}
	
	// 자료 삭제
	private void deleteMember(){
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
//		int count = service.getMemberCount(memId);
//		
//		if(count==0){  // 없는 회원이면...
//			System.out.println(memId + "은(는) 없은 회원ID입니다.");
//			System.out.println("삭제 작업을 종료합니다.");
//			return;
//		}
		
		int cnt = service.deleteMember(memId);	//Service의 삭제용 메서드 호출
		
		if(cnt>0){
			System.out.println(memId + "회원의 정보가 삭제되었습니다.");
		}else{
			System.out.println(memId + "회원 정보 삭제 실패~~");
		}
		
	}
	
	// 자료 수정 ==> 원하는 컬럼만 선택해서 수정
	private void updateMember2(){
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "은(는) 없은 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		// 수정할 항목 선택하기
		int num;
		String updateField = null;  // 수정 작업을 진행할 '컬럼명'이 저장될 변수
		String updateTitle = null;
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("  1. 회원이름     2. 전화번호    3. 회원주소");
			System.out.println("-----------------------------------");
			System.out.print("수정할 항목 >> ");
			num = scan.nextInt();
			
			
			switch(num){
				case 1 : 
					updateField = "MEM_NAME";
					updateTitle = "회원이름"; break;
				case 2 : 
					updateField = "MEM_TEL";
					updateTitle = "전화번호"; break;
				case 3 : 
					updateField = "MEM_ADDR";
					updateTitle = "회원주소"; break;
				default : 
					System.out.println("수정 항목을 잘못 선택했습니다.");
					System.out.println("다시 선택하세요.");
			}
			
		}while(num<1 || num>3);
		
		
		// 수정할 데이터 입력 받기
		System.out.println();
		scan.nextLine();  // 버퍼비우기
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();  
		
		// 회원ID, 수정할컬럼명, 수정할데이터를 저장할 Map객체 생성
		Map<String, String> paramMap = new HashMap<>();
		
		paramMap.put("memid", memId);	//입력받은 회원ID를 Map에 추가
		paramMap.put("field", updateField);		//수정할 컬럼명 추가
		paramMap.put("data", updateData);		//수정할 데이터 추가
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0){
			System.out.println("Update 성공!!");
		}else{
			System.out.println("Update 실패~~~");
		}
			
	}
	
	// 자료 수정  ==> 전체 컬럼 수정(id제외)
	private void updateMember() throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException{
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(aes256.encrypt(memId));
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "은(는) 없은 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		System.out.print("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(aes256.encrypt(memId));
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.updateMember(memVo);
		
		if(cnt>0){
			System.out.println(memId + "회원 정보 수정 완료!!!");
		}else{
			System.out.println(memId + "회원 정보 수정 작업 실패~~~");
		}
	}
	
	// 전체 자료 출력
	private void displayMember() throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException{
		// 전체 회원 정보를 가져오는 메서드를 호출하여 List객체 변수에 저장한다.
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println(" ID   이름         전화번호        주소");
		System.out.println("---------------------------------");
		
		if(memList == null || memList.size() == 0){
			System.out.println("회원 정보가 없습니다!!!");
		}else{
			// List의 데이터 개수만큼 반복 처리
			for(MemberVO memVo : memList){
				System.out.print(aes256.decrypt(memVo.getMem_id()) + "\t");
				System.out.print(memVo.getMem_name() + "\t");
				System.out.print(memVo.getMem_tel() + "\t");
				System.out.println(memVo.getMem_addr() + "\t");
			}
		}
		
		System.out.println("---------------------------------");
		System.out.println("출력 작업 끝...");
	}
	
	// 추가 메서드
	private void insertMember() throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println();
		System.out.println("새로운 회원 정보 추가하기");
		
		int cnt = 0;
		String memId;
//		do{
//			System.out.print("회원 ID >> ");
//			memId = scan.next();
//			
//			cnt = service.getMemberCount(memId);
//			if(cnt>0){
//				System.out.println(memId + "은 이미 있는 ID입니다.");
//				System.out.println("다른 회원ID로 다시 입력하세요.");
//			}
//		}
//	while(cnt>0);
		
		System.out.print("회원 ID >> ");
		memId = scan.next();
		String memId256 = aes256.encrypt(memId);	
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("주     소 >> ");
		String memAddr = scan.nextLine();
		
		//insert할 데이터(입력받은 데이터)를 MemberVO객체에 담는다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId256);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int count = service.insertMember(memVo);
			
		if(count > 0){
			System.out.println(memId + "회원 정보 추가 성공!!!");
		}else{
			System.out.println(memId + "회원 정보 추가 실패~~~");
		}
	}
	
	// 메뉴 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println(" -- 작업 선택 --");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2");
		System.out.println(" 0. 작업 끝.");
		System.out.println("---------------");
		System.out.print(" 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}

	
}
