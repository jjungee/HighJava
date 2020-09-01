package kr.or.ddit.basic;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneBookTest {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
	 	문제) 이름, 주소, 나이, 전화번호를 멤버변수로 갖는 Phone클래스를 만들고
	 		 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
	 		 
	 		 - 이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
	 		 - 삭제와 검색 기능은 '이름'을 입력 받아 처리한다.
	 		 - (Map의 구조는 key값으로 그 사람의 '이름'을 사용하고,
	 		              value값으로는 'Phone클래스의 인스턴스'로 한다.)
	 		              
	 	-추가 조건)
	 		1. 6.전화번호 저장 메뉴를 추가하고 구현한다.
	 			(저장 파일 명 : phoneDate.dat로 한다.)
	 		2. 프로그램이 시작될 때 저장된 팡리이 있으면 그 데이터를 읽어와 Map에 셋팅한다.
	 		3. 프로그램을 종료할 때 Map의 데이터가 변경되었거나 추가 또는 삭제되면 새로 저장한 후 정료되도록 한다.
	 
	 	실행예시)
	 		다음 메뉴를 선택하세요.
	 		1. 전화번호 등록
	 		2. 전화번호 수정
	 		3. 전화번호 삭제
	 		4. 전화번호 검색
	 		5. 전화번호 전체출력
	 		0. 프로그램 종료
	 		---------------
	 		번호입력> 1
	 		
	 		새롭게 등록할 전화번호 정보를 입력하세요
	 		이름 >> 홍길동
	 		전화번호 >> 010-1111-1111
	 		나이 >> 30
	 		주소 >> 대전시 중구 대흥동
	 		
	 		'홍길동' 전화번호 등록 완료!!
	 */
		
		HashMap<String, Phone> phoneBook = new HashMap<>();
//		HashMap<String, Object> newNb = new HashMap<>();
		
		String name = "";
		String tel = "";
		int age = 0;
		String add = ""; 
		int input = 0;
		while(true){
			System.out.println("====================");
			System.out.println("  1. 전화번호 등록");
	 		System.out.println("  2. 전화번호 수정");
	 		System.out.println("  3. 전화번호 삭제");
	 		System.out.println("  4. 전화번호 검색");
	 		System.out.println("  5. 전화번호 전체출력");
	 		System.out.println("  0. 프로그램 종료");
	 		System.out.println("====================");
	 		System.out.print(" 입력 > ");
	 		input = Integer.parseInt(sc.nextLine());
	 		
	 		switch (input) {
				case 1:
					System.out.println("전화번호를 등록하시겠습니까?");
					System.out.println("y : 등록, n : 아니오");
					String yb = sc.nextLine();
					
					if(yb.equals("y")){
						System.out.print("이름 > ");
						name =  sc.nextLine();
						System.out.print("전화번호 > ");
						tel = sc.nextLine();
						System.out.print("나이 > ");
						age = Integer.parseInt(sc.nextLine());
						System.out.print("주소 > ");
						add = sc.nextLine();
						
						phoneBook.put(name, new Phone(name, tel, age, add));
					}
					break;
					
				case 2:
						System.out.println("수정할 번호의 이름 > ");	
						name = sc.nextLine();
						
						if(phoneBook.containsKey(name) == false){
							System.out.println("해당 이름이 존재하지 않습니다.");
						} else{
							System.out.print("수정할 전화번호 > ");
							tel = sc.nextLine();
							
							phoneBook.get(name).setTel(tel);
						}
					
					break;
				case 3:
					System.out.println("삭제할 번호의 이름 > ");	
					name = sc.nextLine();
					
					if(phoneBook.containsKey(name) == false){
						System.out.println("해당 이름이 존재하지 않습니다.");
					} else{
						phoneBook.remove(name);
					}
					break;
				case 4:
					System.out.println("검색할 번호의 이름 > ");	
					name = sc.nextLine();
					
					if(phoneBook.containsKey(name) == false){
						System.out.println("해당 이름이 존재하지 않습니다.");
					} else{
						name = phoneBook.get(name).getName();
						tel = phoneBook.get(name).getTel();
						age = phoneBook.get(name).getAge();
						add = phoneBook.get(name).getAdd();
						
						System.out.println("이름\t\t전화번호\t\t나이\t주소");
						System.out.println("=================================================");
						System.out.println(name + "\t\t" + tel + "\t\t" + age + "\t" + add );
						System.out.println("=================================================");
					}
					break;
				case 5:
					System.out.println("이름/t/t전화번호/t/t나이/t주소");
						for(String key : phoneBook.keySet()){
							Phone value = phoneBook.get(key);
							System.out.println(value.getName()  + "\t" + value.getTel() + "\t" + value.getAge() + "\t" + value.getAdd());
						}
					
					break;
				case 0:
					System.out.println("종료");
					System.exit(0);
					break;

			
			}//switch
		}//while
		
	}

	
	
}

class Phone{
	String name;
	String tel;
	int age;
	String add;
	
	public Phone(String name, String tel, int age, String add) {
		super();
		this.name = name;
		this.tel = tel;
		this.age = age;
		this.add = add;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}
}
