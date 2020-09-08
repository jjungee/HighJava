package kr.or.ddit.basic.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.basic.inf.RemoteInterface;
import kr.or.ddit.basic.vo.FileInfoVO;
import kr.or.ddit.basic.vo.TestVO;


public class RemoteClient {

   
      public static void main(String[] args) {
         try {
            //RMI용 객체를 서버에서 구해와서 사용하는 순서
            
            // 1. 서버에서 등록한 RMI용 객체를 찾기 위해 Registry객체를 생성한다.
            //      (서버의 IP주소와 제공하는 포트번호를 지정하여 생성한다)
            Registry reg = LocateRegistry.getRegistry("192.168.43.35", 9999);
            
            
            // 2. 서버에서 등록한 '객체의 Alias' 를 이용하여 객체를 불러온다.
            // 형식) Registry 객체변수.lookup("객체의Alias");
            RemoteInterface inf = (RemoteInterface) reg.lookup("rmiServer");
            
            // 3. 이제부터 불러온객체의 메서드를 호출해서 사용할 수 있다.
            
            int a = inf.doRemotePrint("안녕");
            System.out.println("서버의 반환 값 : " + a);
            System.out.println();
            
            List<String> list = new ArrayList<>();
            list.add("말차");
            list.add("초콜릿");
            list.add("복숭아");
            list.add("커피");
            
            inf.doPrintList(list);
            System.out.println("List전송 끝...");
            System.out.println();
            
            TestVO myVo = new TestVO();
            myVo.setNumber(718);
            myVo.setName("BC");
            
            inf.doPrintVo(myVo);
            System.out.println("VO객체 전송 끝");
            System.out.println();
            
            //파일 전송 처리 시작
            File file = new File("d:/d_other/강아지.jpg");
            if(!file.exists()){
            	System.out.println("복사할 원본 파일이 없습니다.");
            	return;
            }
            
            FileInfoVO fileInfo = new FileInfoVO();	//파일 정보 저장용 VO객체 생성
            
            //파일 용량을 구해서 FileInfoVO의 fileData배열의 크기를 결정한다.
            int len = (int)file.length();
            byte[]data = new byte[len];
            
            //파일을 읽어올 스트림 객체 생성
            FileInputStream fin = new FileInputStream(file);
            
            //파일의 내용을 읽어와 byte배열애 저장한다.
            fin.read(data);
            
           fileInfo.setFileName(file.getName());
           fileInfo.setFileData(data);
           
           //서버의 파일 전송용 메서드 호출
           inf.setFile(fileInfo);
           
           System.out.println("파일 전송 작업 끝...");
            
         } catch (RemoteException e) {
            
         } catch (NotBoundException e) {
        	 e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
      }
}