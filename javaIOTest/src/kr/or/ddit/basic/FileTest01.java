package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		//File 객체 만들기 연습
		
		// 형식1) new File(String 파일 또는 경로)
		
		
		File file1 = new File("D:/D_Other/test.txt");
//		File file1 = new File("D:\\D_Other\\test.txt");
		
		System.out.println("파일명 : " + file1.getName());
		System.out.println("path : " + file1.getPath());
		System.out.println("디렉토리일까? " + file1.isDirectory());
		System.out.println("파일일까? " + file1.isFile());
		
		System.out.println("---------------");
		
		File file2 = new File("D:/D_Other");
		System.out.println("파일명 : " + file2.getName());
		System.out.println("path : " + file2.getPath());
		System.out.println("디렉토리일까? " + file2.isDirectory());
		System.out.println("파일일까? " + file2.isFile());
		
		
		// 형식 2)new File(File parent, String child)
//		==> 2. new File(File parent, String child)
		System.out.println("---------------");
		
		File file3 = new File(file2, "test.txt");
		System.out.println("파일명 : " + file3.getName());
		System.out.println("path : " + file3.getPath());
		System.out.println("디렉토리일까? " + file3.isDirectory());
		System.out.println("파일일까? " + file3.isFile());
		
		System.out.println("---------------");
		
		// 형식 3) new File(String parent, String child)
		
		File file4 = new File("D:/D_Other", "test.txt");
		System.out.println("파일명 : " + file3.getName());
		System.out.println("path : " + file3.getPath());
		System.out.println("디렉토리일까? " + file3.isDirectory());
		System.out.println("파일일까? " + file3.isFile());
		
		System.out.println("---------------");
		// 디렉토리 (폴더) 만들기
		// -mkdir()
		File file5 = new File("D:/D_Other/연습용");
		System.out.println(file5.getName() + "의 존재여부 : " + file5.exists());
		if(!file5.exists()){
			if(file5.mkdir()){
				System.out.println(file5.getName() + " 폴더 만들기 성공");
			}else{
				System.out.println(file5.getName() + " 폴더 만들기 실패~");
			}
		}
		
		System.out.println("---------------");
		
		
		File file6 = new File("D:/D_Other/test/java/src");
		if(!file6.exists()){
			if(file6.mkdirs()){
				System.out.println(file6.getName() + " 폴더 만들기 성공");
			}else{
				System.out.println(file6.getName() + " 폴더 만들기 실패~");
			}
		}
		
		
		
	}

}
