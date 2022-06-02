package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBManager;
import dto.AnserDTO;
import dto.QnaDTO;
import dto.VO;
import utility.Criteria;

public class AnserDAO {
	
	
	private static AnserDAO instance = new AnserDAO();
	private AnserDAO() {}
	public static AnserDAO getInstance() {
		return instance;
	}
	DBManager dbm = DBManager.getInstance();
	
	public void anserInsert(AnserDTO adto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "insert into anser (bno,imgurl,writer,title,content)"
				+ " values (anser_seq.nextval,?,?,?,?)";
		try {
			conn = dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, adto.getImgurl());
			pstmt.setString(2, adto.getWriter());
			pstmt.setString(3, adto.getTitle());
			pstmt.setString(4, adto.getContent());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public List<AnserDTO> getAnserSelect(){
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from anser";
		
		List<AnserDTO> list = new ArrayList<AnserDTO>();
		
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AnserDTO adto = new AnserDTO();
				adto.setBno(rs.getInt("bno"));
				adto.setContent(rs.getString("content"));
				adto.setTitle(rs.getString("title"));
				adto.setImgurl(rs.getString("imgurl"));
				adto.setViewcount(rs.getInt("viewcount"));
				adto.setWriter(rs.getString("writer"));
				list.add(adto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int setViewcount(int bno) {
		
		Connection conn= null;
		PreparedStatement pstmt=null;
		
		String sql = "update anser set viewcount = viewcount +1 where bno = ?";
		
		int result=0;
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		return result;
	}
	
	//총 레코드 개수 구하기
	public int Count(String query) {
		   
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	      
		String sql = "";
	      
		if(query != "") {
			sql="select count(*) as cnt from anser where "+query; //...where title like '%코딩%' 부분조건
		}else {
			sql="select count(*) as cnt from anser"; //전부   
		}   
		
		int count = 0; //총 레코드 개수가 저장될 변수
	      
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt("cnt");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt,rs);
		}
		return count;   
	}
	
	//page
	public List<AnserDTO> getListPaging(Criteria cri, String query) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    	
    	String sql = null;
    	
    	List<AnserDTO> list = new ArrayList<AnserDTO>();
    	
    	if(query != "") { //조건이 있을때
    		sql = "select * from " 
    				+ "(select /*+ index_desc(anser anser_pk) */ rownum rn, bno, content, title, imgurl, viewcount, writer "
    				+ " from anser where ("+ query +") and rownum <= ? * ?) where rn > (?-1) * ?";
    	}else { //조건이 없을때
            sql = "select * from "
                  + "(select /*+ index_desc(anser anser_pk) */ rownum rn, bno, content, title, imgurl, viewcount, writer "
                  + "from anser where rownum <= ? * ?) where rn > (?-1) * ?";
    	}
         try {
            conn = dbm.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cri.getPageNum());
            pstmt.setInt(2, cri.getAmount());
            pstmt.setInt(3, cri.getPageNum());
            pstmt.setInt(4, cri.getAmount());
            rs = pstmt.executeQuery();
            while(rs.next()) {
            	AnserDTO vo = new AnserDTO();
				vo.setBno(rs.getInt("bno"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setImgurl(rs.getString("imgurl"));
				vo.setViewcount(rs.getInt("viewcount"));
				vo.setWriter(rs.getString("writer"));
				list.add(vo);
            }
         }catch(Exception e) {
            e.printStackTrace();
         }finally {
            dbm.close(conn, pstmt, rs);
         }
         return list;      
	}
	
	public int getViewcount(int bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String sql = "select viewcount from anser where bno = ?";
		
		int count =0;
		
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt("viewcount");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;
	}
	
	//조회수 증가하기
		public void ViewCount(int bno) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String query = "update anser set viewcount = viewcount + 1 where bno = ?";
			
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
		}
	
	public void Delete(int bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
      
		String query="delete from anser where bno = ?"; 
      
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
	}
	
	public AnserDTO Edit(int bno) { // 수정 검색
		
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	String query="select * from anser where bno = ?"; //최근에 등록된 글이 먼저 보이기 위해 desc
    	AnserDTO qvo= new AnserDTO();
    	
    	try {
    		conn=dbm.getConnection();
    		pstmt=conn.prepareStatement(query);
    		pstmt.setInt(1, bno);
    		rs=pstmt.executeQuery();
    		while(rs.next()) {
    			qvo = new AnserDTO();
				qvo.setBno(rs.getInt("bno"));
				qvo.setTitle(rs.getString("title"));
				qvo.setContent(rs.getString("content"));
				qvo.setWriter(rs.getString("writer"));
				qvo.setViewcount(rs.getInt("viewcount"));
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		dbm.close(conn, pstmt,rs);
    	}
    	return qvo;
	}
	
	public void AnserEdit(AnserDTO dto) { // 수정 덮어버리는거
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query="update anser set write=?, title=?, content=? "
				+ "where bno=?"; //최근에 등록된 글이 먼저 보이기 위해 desc
		try {
			conn = dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getBno());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}

	//이전글
		public AnserDTO prevByBno(int bno) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	      
			String query="select bno,title from anser where bno = (select max(bno) from anser where bno < ?)"; //최근에 등록된 글이 먼저 보이기 위해 desc
	      
			AnserDTO vo = new AnserDTO();
			try {
				conn = dbm.getConnection();
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, bno);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            vo = new AnserDTO();
	            vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         dbm.close(conn, pstmt, rs);
	      }
	      return vo;   
	   }
	   
		//다음글
		public AnserDTO nextByBno(int bno) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	      
			String query="select bno, title from anser where bno = (select min(bno) from anser where bno > ?)"; 
	      
			AnserDTO vo = new AnserDTO();
			try {
				conn = dbm.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bno);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					vo = new AnserDTO();
					vo.setBno(rs.getInt("bno"));
					vo.setTitle(rs.getString("title"));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbm.close(conn, pstmt, rs);
			}
			return vo;   
		}
	
	//view
	public AnserDTO AnserView(int bno) {
	   
		ViewCount(bno);
	   
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		String query="select * from anser where bno=?"; 
      
		AnserDTO jvo = new AnserDTO();
      
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				jvo.setBno(rs.getInt("qbno"));
				jvo.setContent(rs.getString("qcontent"));
				jvo.setWriter(rs.getString("writer"));
				jvo.setViewcount(rs.getInt("viewcount"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt,rs);
		}
		return jvo;
	}
}
