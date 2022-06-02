package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBManager;
import dto.VO;
import utility.Criteria;

public class DAO {

	private static DAO instance = new DAO();
	private DAO() {}
	public static DAO getInstance() {
		return instance;
	}
   
   DBManager dbm= DBManager.getInstance();
   
   public void Insert(VO vo, String jj){
      Connection conn=null;
      PreparedStatement pstmt =null;
      
      String sql="insert into "+jj+"(bno,title,writer,wordname,wordrealname,grammarname,"
      		+ "grammarrealname,readingname,readingrealname,listeningname,listeningrealname,jlpt)"
            + " values("+jj+"_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
      
      try {
         conn=dbm.getConnection();
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, vo.getTitle());
         pstmt.setString(2, vo.getWriter());
         pstmt.setString(3, vo.getWordname());
         pstmt.setString(4, vo.getWordrealname());
         pstmt.setString(5, vo.getGrammarname());
         pstmt.setString(6, vo.getGrammarrealname());
         pstmt.setString(7, vo.getReadingname());
         pstmt.setString(8, vo.getReadingrealname());
         pstmt.setString(9, vo.getListeningname());
         pstmt.setString(10, vo.getListeningrealname());
         pstmt.setString(11, vo.getJlpt());
         pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         dbm.close(conn, pstmt);
      }
   }
   
   public List<VO> Select(String jj) {
	   
	  Connection conn  = null;
      PreparedStatement pstmt=null;
      ResultSet rs = null;
      
      String sql="select * from "+jj+" order by bno desc";
      
      List<VO> list= new ArrayList<VO>();
      try {
          conn=dbm.getConnection();
          pstmt=conn.prepareStatement(sql);
          rs=pstmt.executeQuery();
          while(rs.next()) {
          	  VO jvo= new VO();
              jvo.setBno(rs.getInt("bno"));
              jvo.setTitle(rs.getString("title"));
              jvo.setWriter(rs.getString("writer"));
              jvo.setWordname(rs.getString("wordname"));
              jvo.setWordrealname(rs.getString("wordrealname"));
              jvo.setGrammarname(rs.getString("grammarname"));
              jvo.setGrammarrealname(rs.getString("grammarrealname"));
              jvo.setReadingname(rs.getString("readingname"));
              jvo.setReadingrealname(rs.getString("readingrealname"));
              jvo.setListeningname(rs.getString("Listeningname"));
              jvo.setListeningrealname(rs.getString("Listeningrealname"));
              jvo.setJlpt(rs.getString("jlpt"));
              jvo.setViewcount(rs.getInt("viewcount"));
              list.add(jvo);
           }
       }catch(Exception e) {
          e.printStackTrace();
       }finally {
          dbm.close(conn, pstmt);
       }
      return list;
   }
   public List<VO> JjSelect(String jj, String query) { //오버로딩
	   
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String sql = "";
	      
	   if(query != "") {
		   sql="select * from "+jj+" where "+query+" order by bno desc"; //..where title like '%코딩%' order by...
	   }else {
		   sql="select * from "+jj+" order by bno desc"; //최근에 등록된 글이 먼저 보이기 위해 desc
	   }
	   List<VO> list1 = new ArrayList<VO>();
		         
	   try {
		   conn=dbm.getConnection();
		   pstmt=conn.prepareStatement(sql);
		   rs=pstmt.executeQuery();
		   
		   while(rs.next()) {
			   VO jvo = new VO();
	 	       jvo.setBno(rs.getInt("bno"));
	 	       jvo.setTitle(rs.getString("title"));
	 	       jvo.setWriter(rs.getString("writer"));
	 	       jvo.setWordname(rs.getString("wordname"));
	 	       jvo.setWordrealname(rs.getString("wordrealname"));
	 	       jvo.setGrammarname(rs.getString("grammarname"));
	 	       jvo.setGrammarrealname(rs.getString("grammarrealname"));
	 	       jvo.setReadingname(rs.getString("readingname"));
	 	       jvo.setReadingrealname(rs.getString("readingrealname"));
	 	       jvo.setListeningname(rs.getString("Listeningname"));
	 	       jvo.setListeningrealname(rs.getString("Listeningrealname"));
	 	       jvo.setJlpt(rs.getString("jlpt"));
	 	       jvo.setViewcount(rs.getInt("viewcount"));
	 	       list1.add(jvo);
		   }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }finally {
		   dbm.close(conn, pstmt,rs);
	   }
	   return list1;
	}
   
 //총 레코드 개수 구하기
   public int Count(String query, String jj) {
	   
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String sql = "";
      
      if(query != "") {
         sql="select count(*) as cnt from " +jj+ " where "+query; //...where title like '%코딩%' 부분조건
      }else {
          sql="select count(*) as cnt from " +jj; //전부   
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
   
	//view
	public VO View(int bno, String jj) {
	   
		ViewCount(bno,jj);
	   
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		String query="select * from " +jj+ " where bno=?"; 
      
		VO jvo = new VO();
      
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				jvo.setBno(rs.getInt("bno"));
				jvo.setTitle(rs.getString("title"));
				jvo.setWordname(rs.getString("wordname"));
				jvo.setWordrealname(rs.getString("wordrealname"));
				jvo.setGrammarname(rs.getString("grammarname"));
				jvo.setGrammarrealname(rs.getString("grammarrealname"));
				jvo.setReadingname(rs.getString("readingname"));
				jvo.setReadingrealname(rs.getString("readingrealname"));
				jvo.setListeningname(rs.getString("listeningname"));
				jvo.setListeningrealname(rs.getString("listeningrealname"));
				jvo.setViewcount(rs.getInt("viewcount"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt,rs);
		}
		return jvo;
	}
   
	//조회수 증가하기
	public int ViewCount(int bno, String jj) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update " +jj+ " set viewcount = viewcount + 1 where bno = ?";
		
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
	public int getViewcount(int bno, String jj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String sql = "select viewcount from " +jj+ " where bno = ?";
		
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
   
	//이전글
	public VO prevByBno(int bno, String jj) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		String query="select bno,title from " +jj+ " where bno = (select max(bno) from " +jj+ " where bno < ?)"; //최근에 등록된 글이 먼저 보이기 위해 desc
      
		VO vo = new VO();
		try {
			conn = dbm.getConnection();
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, bno);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            vo = new VO();
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
	public VO nextByBno(int bno, String jj) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		String query= "select bno, title from " +jj+ " where bno = (select min(bno) from " +jj+ " where bno > ?)"; 
      
		VO vo = new VO();
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new VO();
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
   
	public void Delete(String jj, int bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
      
		String query="delete from "+jj+" where bno = ?"; 
      
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

	public VO Edit(int bno, String jj) { // 수정 검색
		
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	String query="select * from "+jj+" where bno = ?"; //최근에 등록된 글이 먼저 보이기 위해 desc
    	VO jvo= null;
    	
    	try {
    		conn=dbm.getConnection();
    		pstmt=conn.prepareStatement(query);
    		pstmt.setInt(1, bno);
    		rs=pstmt.executeQuery();
    		while(rs.next()) {
    			jvo = new VO();
    			jvo.setBno(rs.getInt("bno"));
				jvo.setTitle(rs.getString("title"));
	            jvo.setWriter(rs.getString("writer"));
	            jvo.setWordname(rs.getString("wordname"));
	            jvo.setWordrealname(rs.getString("wordrealname"));
	            jvo.setGrammarname(rs.getString("grammarname"));
	            jvo.setGrammarrealname(rs.getString("grammarrealname"));
	            jvo.setReadingname(rs.getString("readingname"));
	            jvo.setReadingrealname(rs.getString("readingrealname"));
	            jvo.setListeningname(rs.getString("listeningname"));
	            jvo.setListeningrealname(rs.getString("listeningrealname"));
	            jvo.setJlpt(rs.getString("jlpt"));
	            jvo.setViewcount(rs.getInt("viewcount"));
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		dbm.close(conn, pstmt,rs);
    	}
    	return jvo;
	}
    
	public void JlptEdit(VO vo, String jj) { // 수정 덮어버리는거
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query="update "+jj+" set title=?, Writer=?, jlpt=?, Wordname=?, Wordrealname=?, Grammarname=?, "
				+ "Grammarrealname=?, Readingname=?, Readingrealname=?, Listeningname=?, "
				+ "Listeningrealname=? where bno=?"; //최근에 등록된 글이 먼저 보이기 위해 desc
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getJlpt());
			pstmt.setString(4, vo.getWordname());
			pstmt.setString(5, vo.getWordrealname());
			pstmt.setString(6, vo.getGrammarname());
			pstmt.setString(7, vo.getGrammarrealname());
			pstmt.setString(8, vo.getReadingname());
			pstmt.setString(9, vo.getReadingrealname());
			pstmt.setString(10, vo.getListeningname());
			pstmt.setString(11, vo.getListeningrealname());
			pstmt.setInt(12, vo.getBno());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
    
	//page
	public List<VO> getListWithPaging(Criteria cri, String query, String jj) {
		
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	String sql = null;
    	
    	List<VO> list = new ArrayList<VO>();
    	
    	if(query != "") { //조건이 있을때
    		sql = "select * from " 
    				+ "(select /*+ index_desc("+jj+" "+jj+"_pk) */ "
    				+ "rownum rn, bno, title, writer, wordname, wordrealname, grammarname, "
    				+ "grammarrealname, readingname, readingrealname, listeningname, "
    				+ "listeningrealname, jlpt, viewcount from "+ jj +" where ("+ query +") "
    				+ "and rownum <= ? * ?) where rn > (?-1) * ?";
    	}else { //조건이 없을때
    		sql = "select * from "
    				+ "(select /*+ index_desc("+jj+" "+jj+"_pk) */ "
    				+ "rownum rn, bno, title, writer, wordname, wordrealname, grammarname, "
    				+ "grammarrealname, readingname, readingrealname, listeningname, "
    				+ "listeningrealname, jlpt, viewcount from "+ jj +" where "
    				+ "rownum <= ? * ?) where rn > (?-1) * ?";
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
            	VO vo = new VO();
            	vo.setBno(rs.getInt("bno"));
            	vo.setTitle(rs.getString("title"));
            	vo.setWriter(rs.getString("writer"));
            	vo.setWordname(rs.getString("wordname"));
            	vo.setWordrealname(rs.getString("wordrealname"));
            	vo.setGrammarname(rs.getString("grammarname"));
            	vo.setGrammarrealname(rs.getString("grammarrealname"));
            	vo.setReadingname(rs.getString("readingname"));
            	vo.setReadingrealname(rs.getString("readingrealname"));
            	vo.setListeningname(rs.getString("listeningname"));
            	vo.setListeningrealname(rs.getString("listeningrealname"));
            	vo.setJlpt(rs.getString("jlpt"));
            	vo.setViewcount(rs.getInt("viewcount"));
            	list.add(vo);
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		dbm.close(conn, pstmt, rs);
    	}
    	return list;      
	}
}
