package kr.or.ddit.basic.inf;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.basic.vo.FileInfoVO;
import kr.or.ddit.basic.vo.TestVO;

//	원격지에서 호출할 수 있는 메서트를 선언하는 interface 즉, RMI용 interface는 Remote를 상속해서 작성한다.

public interface RemoteInterface extends Remote{
	
//	이 인터페이스에서 선언되는 모든 메서드들은 RemoteException을 throws하도록 작성해야한다.
	
//	이 인터페이스에서 선언된 메서드의 파라미터 변수는 클라이언트에서 서버쪽으로 보내는 데이터가 저장되는 것이고,
//	선언된 메서드의 반환값은 서버에서 처리한 결과를 클라이언트로 전달하는 데이터이다. 
	public int doRemotePrint(String str) throws RemoteException; 
	
	public void doPrintList(List<String> list) throws RemoteException;
	
	public void doPrintVo(TestVO vo) throws RemoteException;
	
	//파일 전송용 메서드 선언
	public void setFile(FileInfoVO fileVo) throws RemoteException;
	
}
