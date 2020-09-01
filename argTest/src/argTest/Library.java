package argTest;

import java.util.HashMap;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Library {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int maxBookNum = 0;
		HashMap<Integer, LibraryMg> book = new HashMap<>();
		
		int bookNo = 0;
		String title = null;
		String writer = null;
		String genre = null;
		boolean loan = true;
		
		while(true){
			System.out.println("사용할 메뉴를 선택하세요.");
			System.out.println("1. 도서 정보 등록");
			System.out.println("2. 도서 정보 수정");
			System.out.println("3. 도서 정보 삭제");
			System.out.println("4. 도서 정보 검색");
			System.out.println("5. 전체 도서 정보 출력");
			System.out.println("6. 도서 반납 및 대여(x)");
			System.out.println("0. 프로그램 종료");
			System.out.println("--------------------------");
			System.out.print("번호 선택 > ");
			
			int input = Integer.parseInt(sc.nextLine());
		
			switch (input) {
			case 1:
//				등록
//				도서번호, 제목, 지은이, 장르, 대출 가능 여부
				bookNo = maxBookNum + 1;
				System.out.print("제목 입력 > ");
				title = sc.nextLine();
				System.out.print("지은이 입력 > ");
				writer = sc.nextLine();
				System.out.print("장르 입력 > ");
				genre = sc.nextLine();
				loan = true;
				
				maxBookNum++;
				
				book.put(bookNo, new LibraryMg(bookNo, title, writer, genre, loan));
				System.out.println(bookNo + "\t" + title + "\t" + writer + "\t" + genre + "\t" + loan);
				
				break;
			case 2:
//				수정
				
				break;
			case 3:
//				삭제
				break;
			case 4:
//				검색
				break;
			case 5:
//				전체 출력
				break;
			case 6:
//				대여, 반납
				break;
			case 0:
//				종료
				break;

			}
		}
		
		
		
	}
	
	public Object modi(int input){
		Scanner sc = new Scanner(System.in);
		switch (input) {
		case 1:
			String title = sc.nextLine();
			return title;

		case 2:
			
			break;
			
		case 3:
			
			break;
			
		case 4:
			
			break;
		}
		
		return null;
	}
}

class LibraryMg{
	int bookNo;
	String title;
	String writer;
	String genre;
	boolean loan;
	
	public LibraryMg(int bookNo, String title, String writer, String genre,
			boolean loan) {
		this.bookNo = bookNo;
		this.title = title;
		this.writer = writer;
		this.genre = genre;
		this.loan = loan;
	}
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public boolean isLoan() {
		return loan;
	}
	public void setLoan(boolean loan) {
		this.loan = loan;
	}
}