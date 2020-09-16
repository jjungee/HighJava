package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kr.or.ddit.board.vo.BoardVO;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BoardDaoImpl implements IBoardDao{
	
	private static BoardDaoImpl dao;
	private SqlMapClient smc; //ibatis용 SqlMapClient
	
	public static BoardDaoImpl getInstance(){
		if(dao == null){
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	private BoardDaoImpl(){
		try {
		//1. iBatis설정 파일을 읽어와서 실행한다. (sqlMapConfig.xml파일)
		// 1-1. 문자인코딩 케릭터 셋 설정
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		
		// 1-2. 환경 설정파일 읽어오기
		Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
		
		// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을 완성한 후 
		// 		SQL문을 호출해서 실행할 수 있는 객체를 생성한다.
		smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		
		rd.close(); // 스트림 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int insertBoard(BoardVO boardVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("board.insertBoard", boardVo);
			if(obj == null) cnt++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int updateBoard(BoardVO boardVo) {
		int cnt = 0;
		try {
			cnt = smc.update("board.updateBoard", boardVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int updateBoard2(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("board.getAllBoard");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int getBoardCount(int boardNo) {
		int cnt = 0;
		try {
			cnt = (int)smc.queryForObject("board.getboardCount", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}



}