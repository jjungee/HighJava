package kr.or.ddit.mvc.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberDaoImpl implements IMemberDao{
	
	private static MemberDaoImpl dao;
	private SqlMapClient smc; //ibatis용 SqlMapClient
	
	
	private MemberDaoImpl(){
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
	
	public static MemberDaoImpl getInstance(){
		if(dao == null){
			dao = new MemberDaoImpl();
		}
		
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt =0;
		try {
			
			Object obj = smc.insert("mymember.insertMember", memVo);
			if(obj==null) cnt=1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt =0;
		try {
		//	conn = DBUtil3.getConnection();
			Object obj = smc.delete("mymember.deleteMember", memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt =0;
		try {
//			conn = DBUtil3.getConnection();
			String sql = "UPDATE mymember SET mem_id = ?, mem_name = ? , mem_tel = ? , mem_addr = ? where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null)try {pstmt.close();} catch (Exception e2) {}
			if(conn!=null)try {conn.close();} catch (Exception e2) {}
		}
		return cnt;
		
			
		
		
	}

	@Override
	public List<MemberVO> getAllMember() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MemberVO> list = new ArrayList<>();
		try {
//			conn = DBUtil3.getConnection();
			String sql =  "SELECT * FROM mymember";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//?
			while(rs.next()){
				MemberVO out = new MemberVO();
				out.setMem_id(rs.getString("mem_id"));
				out.setMem_name(rs.getString("mem_name"));
				out.setMem_tel(rs.getString("mem_tel"));
				out.setMem_addr(rs.getString("mem_addr"));
				list.add(out);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (Exception e2) {}
			if(pstmt!=null)try {pstmt.close();} catch (Exception e2) {}
			if(conn!=null)try {conn.close();} catch (Exception e2) {}
		}
		
		return list;
		
		
		
		
		
		
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt =0;
		try {
//			conn = DBUtil3.getConnection();
			String sql =  "SELECT count(*) FROM mymember WHERE mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memId);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				cnt = Integer.valueOf(String.valueOf(rs.getObject(1)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();} catch (Exception e2) {}
			if(pstmt!=null)try {pstmt.close();} catch (Exception e2) {}
			if(conn!=null)try {conn.close();} catch (Exception e2) {}
		}
		
		return cnt;
		
			

		
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}