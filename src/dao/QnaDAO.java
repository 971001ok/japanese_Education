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
import utility.Criteria;

public class QnaDAO {
	
	private static QnaDAO instance = new QnaDAO();
	private QnaDAO() {}
	public static QnaDAO getInstance() {
		return instance;
	}
	DBManager dbm = DBManager.getInstance();

	public void qnaInsert(QnaDTO qdto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "insert into qna(qbno, writer, title, qcontent)"
				+ " values(qna_seq.nextval,?,?,?)";
		
		System.out.println(qdto.getTitle());
		try {
			conn = dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, qdto.getWriter());
			pstmt.setString(2, qdto.getTitle());
			pstmt.setString(3, qdto.getQcontent());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public List<QnaDTO> getQnaSelect(){
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from qna order by qbno desc";
		
		List<QnaDTO> list = new ArrayList<QnaDTO>();
		
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				QnaDTO qdto = new QnaDTO();
				qdto.setQbno(rs.getInt("qbno"));
				qdto.setTitle(rs.getString("title"));
				qdto.setQcontent(rs.getString("qcontent"));
				qdto.setStatus(rs.getInt("status"));
				qdto.setWriter(rs.getString("writer"));
				qdto.setRegdate(rs.getString("regdate"));
				qdto.setViewcount(rs.getInt("viewcount"));
				list.add(qdto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<QnaDTO> Select(String query) { //오버로딩
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
	      
		if(query != "") {
			sql="select * from qna where "+query+" order by qbno desc"; //..where title like '%코딩%' order by...
		}else {
			sql="select * from qna order by qbno desc"; //최근에 등록된 글이 먼저 보이기 위해 desc
		}       
			List<QnaDTO> list1 = new ArrayList<QnaDTO>();
		         
			try {
				conn=dbm.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					QnaDTO qdto = new QnaDTO();
					qdto.setQbno(rs.getInt("qbno"));
					qdto.setTitle(rs.getString("title"));
					qdto.setQcontent(rs.getString("qcontent"));
					qdto.setStatus(rs.getInt("status"));
					qdto.setWriter(rs.getString("writer"));
					qdto.setRegdate(rs.getString("regdate"));
					qdto.setViewcount(rs.getInt("viewcount"));
					list1.add(qdto);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbm.close(conn, pstmt,rs);
			}
			return list1;
	}
	
	//총 레코드 개수 구하기
   public int qnaCount(String query) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String sql = "";
      
      if(query != "") {
         sql="select count(*) as cnt from qna where "+query; //...where title like '%코딩%' 부분조건
      }else {
         sql="select count(*) as cnt from qna"; //전부
      }
      int count = 0; //총 레코드 개수가 저장될 변수
      
      try {
         conn = dbm.getConnection();
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery(); //실행
         while(rs.next()) {
            count = rs.getInt("cnt");
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         dbm.close(conn, pstmt, rs);
      }
      return count;   
   }
	
   //조회수 증가하기
	public int setViewcount(int qbno) {
		Connection conn= null;
		PreparedStatement pstmt=null;
		
		String sql = "update qna set viewcount = viewcount +1 where qbno = ?";
		
		int result=0;
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,qbno);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		return result;
	}
	
	public int getViewcount(int qbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String sql = "select viewcount from qna where qbno = ?";
		
		int count =0;
		
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qbno);
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
	public QnaDTO prevByBno(int bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		String query="select qbno,title from qna where qbno = (select max(qbno) from qna where qbno < ?)"; //최근에 등록된 글이 먼저 보이기 위해 desc
      
		QnaDTO vo = new QnaDTO();
		try {
			conn = dbm.getConnection();
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, bno);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            vo = new QnaDTO();
            vo.setQbno(rs.getInt("qbno"));
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
	public QnaDTO nextByBno(int bno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		String query="select qbno, title from qna where qbno = (select min(qbno) from qna where qbno > ?)"; 
      
		QnaDTO vo = new QnaDTO();
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new QnaDTO();
				vo.setQbno(rs.getInt("qbno"));
				vo.setTitle(rs.getString("title"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return vo;   
	}
	
	//조회수 증가하기
	public void ViewCount(int qbno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update qna set viewcount = viewcount + 1 where qbno = ?";
		
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void Delete(int qbno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
      
		String query="delete from qna where qbno = ?"; 
      
		try {
			conn = dbm.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public QnaDTO Edit(int qbno) { // 수정 검색
		
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;

    	String query="select * from qna where qbno = ?"; //최근에 등록된 글이 먼저 보이기 위해 desc
    	QnaDTO qvo= new QnaDTO();
    	
    	try {
    		conn=dbm.getConnection();
    		pstmt=conn.prepareStatement(query);
    		pstmt.setInt(1, qbno);
    		rs=pstmt.executeQuery();
    		while(rs.next()) {
    			qvo = new QnaDTO();
				qvo.setQbno(rs.getInt("qbno"));
				qvo.setTitle(rs.getString("title"));
				qvo.setQcontent(rs.getString("qcontent"));
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
	
	public void QnaEdit(QnaDTO qdto) { // 수정 덮어버리는거
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update qna set writer=?, title=?, qcontent=? "
				+ "where qbno=?"; //최근에 등록된 글이 먼저 보이기 위해 desc
		try {
			conn = dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, qdto.getWriter());
			pstmt.setString(2, qdto.getTitle());
			pstmt.setString(3, qdto.getQcontent());
			pstmt.setInt(4, qdto.getQbno());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}

	//view
	public QnaDTO qnaView(int qbno) {
	   
		ViewCount(qbno);
	   
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
      
		String query="select * from qna where qbno=?"; 
      
		QnaDTO jvo = new QnaDTO();
      
		try {
			conn=dbm.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				jvo.setQbno(rs.getInt("qbno"));
				jvo.setQcontent(rs.getString("qcontent"));
				jvo.setTitle(rs.getString("title"));
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
	
	//page
	public List<QnaDTO> ListWithPaging(Criteria cri, String query) {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = null;
        
        List<QnaDTO> list = new ArrayList<QnaDTO>();
        
        if(query != "") { //조건이 있을때
           sql = "select * from (select /*+ index_desc(qna qna_pk) */ "
                 + " rownum rn, qbno, title, qcontent, writer, regdate, viewcount,status "
                 + "from qna where ("+ query +") and rownum <= ? * ?) where rn > (?-1) * ?";
        }else { //조건이 없을때
           sql = "select * from (select /*+ index_desc(qna qna_pk) */ "
                 + " rownum rn, qbno, title, qcontent, writer, regdate, viewcount,status "
                 + "from qna where rownum <= ? * ?) where rn > (?-1) * ?";
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
        	  QnaDTO dto = new QnaDTO();
              dto.setQbno(rs.getInt("qbno"));
              dto.setTitle(rs.getString("title"));
              dto.setQcontent(rs.getString("qcontent"));
              dto.setStatus(rs.getInt("status"));
              dto.setWriter(rs.getString("writer"));
              dto.setRegdate(rs.getString("regdate"));
              //dto.setRegdate(rs.getString("regdate").substring(0,10));
              dto.setViewcount(rs.getInt("viewcount"));
              list.add(dto);
           }
        }catch(Exception e) {
           e.printStackTrace();
        }finally {
           dbm.close(conn, pstmt, rs);
        }
        return list;      
     }

}
