package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileCopy {

	public static void main(String[] args) {
//		d:/d_other/호랑이.jpg파일을 'd:/d_other/연습용'폴더에 '호랑이_복사본.jpg'파일로 저장하시오.
		
		try {
			FileInputStream fin = new FileInputStream("d:/d_other/왕샤오.jpg");
			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/왕샤오_복사본.jpg");
			
			int c;
			
			while( (c=fin.read()) != -1 ){
				fout.write(c);
			}
			
//			for (int i = 1; (fout = fin.read()) != null; i++) {
//				System.out.printf("%4d : %s\n", i, fout);
//			}
			
			fin.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		-------------------------------------------------------------------------------------------------------
	}

}