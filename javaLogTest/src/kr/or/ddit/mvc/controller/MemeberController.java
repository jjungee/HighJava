package kr.or.ddit.mvc.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemeberController {
	private IMemberService service;
	private Scanner scan;

	public MemeberController() {
		service = MemberServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}
		
		public static void main(String[] args) {
			new MemeberController().start();
		}

		private void start() {
		 
			int input = 0;
			
			while(true){
			System.out.println("--- 작 업 선 택 ---");
			System.out.println("1. 자료 추가");
			System.out.println("2. 자료 삭제");
			System.out.println("3. 자료 수정");
			System.out.println("4. 전체 자료 출력");
			System.out.println("0. 종료");
			System.out.println("---------------");
			System.out.print("입력 >>");
			input = Integer.parseInt(scan.nextLine());
			
			switch (input) {
			case 1:
				dataInsert();
				break;
			case 2:
				dataDelete();
				break;
			case 3:
				dataUpdate();
				break;
			case 4:
				showData();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				
				break;

			
			
				}
			}
			
			
		}

		private void showData() {
			
			List<MemberVO> memList = service.getAllMember();
			System.out.println("----------전체데이터 -------");
			System.out.println("ID\tName\tTel\tAddr");
			for(MemberVO mem : memList){
				System.out.println(mem.getMem_id() + "\t"+mem.getMem_name() + "\t"+
						mem.getMem_tel() + "\t"+mem.getMem_addr());
			}
			System.out.println("------------------------");
			
			
		}



		private void dataUpdate() {
			MemberVO memVo = new MemberVO();
			System.out.println("자료를 수정합니다.");
			System.out.print("아이디 : ");
			String id = scan.nextLine();
			
			if(service.getMemberCount(id) == 0){
				while(true){
				System.out.println("해당 아이디가 존재하지 않습니다.");
				System.out.print("아이디 : ");
				id = scan.nextLine();
				if(service.getMemberCount(id) ==1) break;
				}
			}
			
			System.out.print("이름 : ");
			String name = scan.nextLine();
			System.out.print("연락처 : ");
			String tel = scan.nextLine();
			System.out.print("주소 : ");
			String addr = scan.nextLine();
			
			memVo.setMem_id(id);
			memVo.setMem_name(name);
			memVo.setMem_tel(tel);
			memVo.setMem_addr(addr);
			
			
			int result = service.updateMember(memVo);
			
			System.out.println(result + "건 수정 성공하였습니다.");
			
			return;
		}

		

		private void dataDelete() {
			System.out.println("자료를 삭제합니다.");
			System.out.print("삭제할 아이디 : ");
			String id = scan.nextLine();
			
			if(service.getMemberCount(id) == 0){
				while(true){
				System.out.println("해당 아이디가 존재하지 않습니다.");
				System.out.print("아이디 : ");
				id = scan.nextLine();
				if(service.getMemberCount(id) ==1) break;
				}
			}
			
			int result = service.deleteMember(id);
			
			System.out.println(result + "건 삭제 성공하였습니다.");
			
			return;
		}

		private void dataInsert() {
			MemberVO memVo = new MemberVO();
			System.out.println("자료를 입력합니다.");
			System.out.print("아이디 : ");
			String id = scan.nextLine();
			
			// 아이디 중복확인
			if(service.getMemberCount(id) == 1){
				while(true){
				System.out.println("해당 아이디가 존재합니다 다른 아이디를 입력해주세요");
				System.out.print("아이디 : ");
				id = scan.nextLine();
				if(service.getMemberCount(id) ==0) break;
				}
			}
			
			System.out.print("이름 : ");
			String name = scan.nextLine();
			System.out.print("연락처 : ");
			String tel = scan.nextLine();
			System.out.print("주소 : ");
			String addr = scan.nextLine();
			
			memVo.setMem_id(id);
			memVo.setMem_name(name);
			memVo.setMem_tel(tel);
			memVo.setMem_addr(addr);
			
			int result = service.insertMember(memVo) ;
			
			System.out.println(result + "건 등록 성공하였습니다.");
			
			return;
		}

}
	