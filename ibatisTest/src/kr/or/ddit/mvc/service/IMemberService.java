package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * Service객체는 Dao에 설정된 메서드를 운하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 결과 자료를 Controller에게 보내주는 역할을 한다.
 * 보통 DAO의 메서드와 구조를 같게한다. (고급자바에서 한정)
 * @author PC-10
 *
 */

public interface IMemberService {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert 작업성공 : 1, 작업실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO자료를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVo update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * Map에 저장된 정보를 이요해서 한개의 컬럼값을 수정하는 메서드
	 * 
	 * @param paramMap update할 컬럼정보가 들어있는 Map객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember2(Map<String, String> paramMap);
	
	/**
	 * 전체 회원 정보를 DB에서 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return 회원정보가 저장될 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 */
	public int getMemberCount(String memId);
	
}
