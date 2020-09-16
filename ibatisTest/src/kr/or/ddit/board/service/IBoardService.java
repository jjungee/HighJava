package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {

	public int insertBoard(BoardVO boardVO);

	public int deleteBoard(int boardNo);

	public int updateBoard(BoardVO boardVO);

	public List<BoardVO> getAllBoard();

	public List<BoardVO> getSelectBoard(String query);

	public BoardVO getBoardPost(int BoardNo);

}