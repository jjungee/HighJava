package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");
			
//			자료형 단위로 출력할 보조 스트림 객체 생성(DataOutputStream객체)
			DataOutputStream dout = new DataOutputStream(fout);
			
			dout.writeInt(718);
			dout.writeFloat(94.718f);
			dout.writeBoolean(false);
			dout.writeUTF("속 안좋다...");
			
			System.out.println("출력 완료");
			
			dout.close();
			
//			---------------------------------------------------------------------------------
//			위에서 출력한 자료 읽어오기
			
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			DataInputStream din = new DataInputStream(fin);
			
//			DataInputStream으로 자료를 읽어올 때는 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			System.out.println("정수형 : " + din.readInt());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("논리형 : " + din.readBoolean());
			System.out.println("문자열 : " + din.readUTF());
			
			System.out.println("읽기 작업 완료...");
			
			din.close();
			
		} catch (Exception e) {
		}
	}

}
