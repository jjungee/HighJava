package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileIOTest06 {

   public static void main(String[] args) {
      try{
         
         InputStreamReader isr = new InputStreamReader(System.in);
         
         FileOutputStream fout = new FileOutputStream("d:/d_other/outTest.txt");
         
         //기본 인코딩 방식
//         OutputStreamWriter osw = new OutputStreamWriter(fout);
         //인코딩 방식 정의
         OutputStreamWriter osw = new OutputStreamWriter(fout, "ms949");
      
         
         System.out.println("파일에 저장할 내용을 입력하세요.");
         
         int c;
         
         while( (c = isr.read()) != -1 ){
            osw.write(c);
         }
         
         osw.close();
         isr.close();
         
      }catch(IOException e){
         
      }
   }

}