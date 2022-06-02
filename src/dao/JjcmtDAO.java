package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBManager;
import dto.JjcmtDTO;
import dto.QnaDTO;
import dto.VO;

public class JjcmtDAO {
	
	// 싱글톤패턴
	
	private static JjcmtDAO instance = new JjcmtDAO();
	private JjcmtDAO() {}
	public static JjcmtDAO getInstance() {
		return instance;
	}
	
	DBManager dbm = DBManager.getInstance();
	
	public int JjcmtInsert(JjcmtDTO jdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rst=0;
		String query="insert into jjcmt(num, bno, writer, content) values(jjcmt_seq.nextval,?,?,?)";
		
		try {
			conn= dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, jdto.getBno());
			pstmt.setString(2, jdto.getWriter());
			pstmt.setString(3, jdto.getContent());
			rst= pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		return rst;
	}
	
	public List<JjcmtDTO> selectJjcmt(int qbno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from jjcmt where bno = ? order by num desc";
		
		List<JjcmtDTO> clist = new ArrayList<JjcmtDTO>();
		
		try {
			conn = dbm.getConnection();
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				JjcmtDTO jdto = new JjcmtDTO();
				jdto.setBno(rs.getInt("bno"));
				jdto.setNum(rs.getInt("num"));
				jdto.setWriter(rs.getString("writer"));
				jdto.setWdate(rs.getString("wdate").substring(0,10));
				jdto.setContent(rs.getString("content"));
				clist.add(jdto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return clist;
	}
		
		//조회수 증가하기
	public int ViewCount(int bno) {
			
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		String query = "update jjcmt set viewcount = viewcount + 1 where bno = ?";
			
		int result=0;
			
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		return result;
	}
}
