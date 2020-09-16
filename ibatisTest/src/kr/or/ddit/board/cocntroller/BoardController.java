package kr.or.ddit.board.cocntroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class BoardController {
    private IBoardService service;
    private Scanner scan;

    public BoardController() {
        service = new BoardServiceImpl();
        scan = new Scanner(System.in);

    }

    public static void main(String[] args) { new BoardController().start();  }

    void start(){

        int input = 0;


        while(true){

            List<BoardVO> list = new ArrayList<>();
            list = service.getAllBoard();



            System.out.println("---------------------------------------");
            System.out.println("NO\t제목\t작성자\t날짜\t");
            System.out.println("---------------------------------------");
            for(BoardVO post : list){
                System.out.println(post.getBoard_no() + "\t" + post.getTitle() + "\t" +
                        post.getUser_id()  + "\t" +  post.getReg_date());
            }


            System.out.println("---------------------------------------");
            System.out.println("1.새글작성\t2.게시글보기\t3.검색\t0.작업끝");
            System.out.print("입력 >>");
            input = Integer.parseInt(scan.nextLine());

            switch (input) {
                case 1:
                	insertPost();
                    break;
                case 2:
                    showPost();

                    break;
                case 3:
                	searchPost();
                    break;

                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);

                    break;



            }
        }

    }

    private void searchPost() {

    	System.out.println("검색할  제  목 : ");
    	String query = scan.nextLine();

    	 List<BoardVO> list = new ArrayList<>();
         list =	service.getSelectBoard(query);

         System.out.println("---------------------------------------");
         System.out.println("NO\t제목\t작성자\t날짜\t");
         System.out.println("---------------------------------------");
         for(BoardVO post : list){
             System.out.println(post.getBoard_no() + "\t" + post.getTitle() + "\t" +
                     post.getUser_id()  + "\t" +  post.getReg_date());
         }


         System.out.println("---------------------------------------");


	}

	private void insertPost() {
    	 BoardVO boardVO = new BoardVO();

    	 System.out.println("- 제  목 : ");
    	 boardVO.setTitle(scan.nextLine());
         System.out.println("- 작성자 : " );
         boardVO.setUser_id(scan.nextLine());
         System.out.println("- 내  용 : ");
         boardVO.setContent(scan.nextLine());



         int result = service.insertBoard(boardVO);

         System.out.println(result + "건 등록 성공헀습니다.");


	}

	private void showPost() {
        System.out.println("보고싶은 게시판 번호를 입력해 주세요");
        int input = Integer.parseInt(scan.nextLine());

        BoardVO post = service.getBoardPost(input);
        System.out.println(input+"번 글 내용");
        System.out.println("--------------------------------");
        System.out.println("- 제  목 : " + post.getTitle());
        System.out.println("- 작성자 : " + post.getUser_id());
        System.out.println("- 내  용 : " + post.getContent());
        System.out.println("- 작성일 : " + post.getReg_date());
        System.out.println("--------------------------------");
        System.out.print("메뉴 : 1.수정\t2.삭제\t3.리스트로가기");
        input = input = Integer.parseInt(scan.nextLine());

        switch (input) {
            case 1:
                updatePost(post);

                break;
            case 2:
                deletePost(post.getBoard_no());
                break;
            case 3:

                return;

        }

    }

    private void deletePost(int board_no) {
        if(service.deleteBoard(board_no) == 1) {


            System.out.println(board_no + "글이 삭제되었습니다");
        }
        return;
    }

    private void updatePost(BoardVO post) {
        System.out.println("수정 작업하기");
        System.out.println("--------------------------------");
        System.out.print("- 제  목 : ");
        String title = scan.nextLine();
        System.out.print("- 내  용 : ");
        String content = scan.nextLine();

        post.setTitle(title);
        post.setContent(content);

        if(service.updateBoard(post) == 1){
            System.out.println(post.getBoard_no()+ "번글이 수정 되었습니다");
        }
        return;

    }

}