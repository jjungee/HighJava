package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			
			result = pstmt.executeUpdate();
			
			if(result > 0){
				System.out.println("작업을 완료했습니다!!");
			}
			
		} catch (SQLException e) {
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e){}
			if(conn != null) try { conn.close(); } catch(SQLException e){}
		}
		
		return result;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			result = pstmt.executeUpdate();
			
			if(result > 0){
				System.out.println("작업을 완료했습니다!!");
			}
			
		} catch (SQLException e) {
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e){}
			if(conn != null) try { conn.close(); } catch(SQLException e){}
		}
		
		return result;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET MEM_ID = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			
			result = pstmt.executeUpdate();
			
			if(result > 0){
				System.out.println("작업을 완료했습니다!!");
			}
			
		} catch (SQLException e) {
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e){}
			if(conn != null) try { conn.close(); } catch(SQLException e){}
		}
		
		return result;

	}
	
	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null;	//select한 회원정보가 저장될 List 변수 선언
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			memList = new ArrayList<>();	//List 객체 생성
			
			while(rs.next()){
				//한 레코드의 정보를 VO에 저장하고, 이 VO객체를 List에 추가한다.
				MemberVO memVo = new MemberVO();
				memVo.setMem_id(rs.getString("MEM_ID"));
				memVo.setMem_name(rs.getString("MEM_NAME"));
				memVo.setMem_tel(rs.getString("MEM_TEL"));
				memVo.setMem_addr(rs.getString("MEM_ADDR"));
				
				memList.add(memVo);
			}
			
		} catch (SQLException e) {
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e){}
			if(conn != null) try { conn.close(); } catch(SQLException e){}
			if(rs != null) try { rs.close(); } catch(SQLException e){}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("CNT");
			}
			
		} catch (SQLException e) {
			result = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
		return result;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE MYMEMBER SET " + paramMap.get("field") + " = ? WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memid"));
			
			result = pstmt.executeUpdate();
			
			if(result > 0){
				System.out.println("작업을 완료했습니다!!");
			}
			
		} catch (SQLException e) {
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e){}
			if(conn != null) try { conn.close(); } catch(SQLException e){}
		}
		
		return result;
	}
	
	
	
}
