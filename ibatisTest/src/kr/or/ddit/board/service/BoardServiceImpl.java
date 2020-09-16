package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao; // dao 객체 변수 생성
	
	private static BoardServiceImpl boardServiceImpl;

	public BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}

	public static BoardServiceImpl getInstance() {
		if(boardServiceImpl == null) boardServiceImpl = new BoardServiceImpl();
		
		return boardServiceImpl;
	}
	
	@Override
	public int insertBoard(BoardVO boardVO) {
		return dao.insertBoard(boardVO);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return dao.updateBoard(boardVO);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public List<BoardVO> getSelectBoard(String query) {
		return null;
	}

	@Override
	public BoardVO getBoardPost(int BoardNo) {
		return null;
	}
}