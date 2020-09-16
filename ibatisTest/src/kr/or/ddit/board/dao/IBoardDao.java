package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 
 * Service에 전달하는 Dao의 interface
 * 
 * @author PC-10
 *
 */

public interface IBoardDao {

	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert 작업성공 : 1, 작업실패 : 0
	 */
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * MemberVO자료를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVo update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * Map에 저장된 정보를 이요해서 한개의 컬럼값을 수정하는 메서드
	 * 
	 * @param paramMap update할 컬럼정보가 들어있는 Map객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard2(Map<String, String> paramMap);
	
	/**
	 * 전체 회원 정보를 DB에서 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return 회원정보가 저장될 List객체
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 */
	public int getBoardCount(int boardNo);
	
}



