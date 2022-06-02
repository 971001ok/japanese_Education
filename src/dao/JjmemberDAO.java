package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbmanager.DBManager;
import dto.JjmemberDTO;

public class JjmemberDAO {
	
DBManager dbm = DBManager.getInstance();
	
	public int memberIdCheck(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	      
		String query = "select * from jjmember where id = ?";

		int result = 0;
	      
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result= 1;
			}else {
				result= -1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public void memberJoin(JjmemberDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql ="insert into jjmember(bno,id,pw,phone,email,post,address1,address2)" + " values(member_seq.nextval,?,?,?,?,?,?,?)";
		
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getEmail());
			pstmt.setNString(5, dto.getPost());
			pstmt.setString(6, dto.getAddress1());
			pstmt.setString(7, dto.getAddress2());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public int memberIdPwCheck(String id, String pw) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query ="Select pw from jjmember where id=?";
		
		int result=0;
		
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString("pw") != null && rs.getString("pw").equals(pw)){
						result=1; //id/pw가 같을때
					}else{
						result = 0; //pw가 같지 않을때
					}
				}else{
				result=-1; //id가 없을 때 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}
	
	// 업데이트를 위한 레코드 검색
	public JjmemberDTO memberOneSelect(String id) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	      
		String sql = "select * from jjmember where id ='"+id+"'";

		JjmemberDTO mdto = null;
		System.out.println(id);
	      
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mdto=new JjmemberDTO();
				mdto.setBno(rs.getInt("bno"));
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPost(rs.getString("post"));
				mdto.setAddress1(rs.getString("address1"));
				mdto.setAddress2(rs.getString("address2"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return mdto;
	}
	
	public void setMemberEdit(JjmemberDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String sql = null;

		try {
			conn = dbm.getConnection();
			if(dto.getPw() != null) {
				sql = "update jjmember set id=?, pw=?, email=?, phone=?, post=?, address1=?, address2=? "
						+ " where id='"+dto.getId()+"'";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPw());
				pstmt.setString(3, dto.getEmail());
				pstmt.setString(4, dto.getPhone());
				pstmt.setString(5, dto.getPost());
				pstmt.setString(6, dto.getAddress1());
				pstmt.setString(7, dto.getAddress2());
				pstmt.executeUpdate();
				}else {
					sql = "update member set id=?, email=? ,phone=?, post=?, address1=?, address2=?"
							+ " where id= '"+dto.getId()+"'";	
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, dto.getId());
					pstmt.setString(2, dto.getEmail());
					pstmt.setString(3, dto.getPhone());
					pstmt.setString(4, dto.getPost());
					pstmt.setString(5, dto.getAddress1());
					pstmt.setString(6, dto.getAddress2());
					pstmt.executeUpdate();
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
}