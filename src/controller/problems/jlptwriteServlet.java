package controller.problems;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.DAO;
import dto.VO;


@WebServlet("/jlptwrite.do")
public class jlptwriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jlptwriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jj = request.getParameter("jj");
		
		request.setAttribute("jj", jj);
		
		RequestDispatcher rd=request.getRequestDispatcher("problems/jlptwrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
	      
	    //첨부파일 처리
	    String savepath = "upload"; //업로드 폴더
	    int maxPostSize = 20 * 1024 * 1024; // 최대 업로드 용량
	    String enctype = "utf-8"; //인코딩 문자
	      
	    ServletContext context = getServletContext();
	    String path = context.getRealPath(savepath); //서버상의 업로드 폴더 실제 경로
	    System.out.println("서버상의 업로드 실제 디렉토리: "+path);

	    MultipartRequest multi = new MultipartRequest(
	          request, 
	          path, 
	          maxPostSize,
	          enctype, 
	          new DefaultFileRenamePolicy()
	          ); //동일한 파일이 존재하면 새로운 이름을 부여하는 객체
	    
	       Enumeration files = multi.getFileNames(); //업로드 이미 완료됨
	       
	       while(files.hasMoreElements()) {
	    	   String file1 = (String)files.nextElement();
	    	   String file_name = multi.getFilesystemName(file1);
	    	   // 중복된 파일을 업로드 할 경우 파일명이 바뀐다.
	    	   String ori_file_name = multi.getOriginalFileName(file1);
	    	   System.out.print("업로드 된 파일명 : " + file_name);
	    	   System.out.print("원본 파일명 :" + ori_file_name);
	    	   System.out.print("");
	       }
     
			    String title = multi.getParameter("title");
			    String writer = multi.getParameter("writer");
			    String wordName = multi.getOriginalFileName("file1");
			    String wordRealName = multi.getFilesystemName("file1"); //실제 서버에 업로드된 파일명을 구함
			    String grammarName = multi.getOriginalFileName("file2");
			    String grammarRealName = multi.getFilesystemName("file2"); //실제 서버에 업로드된 파일명을 구함
			    String readingName = multi.getOriginalFileName("file3");
			    String readingRealName = multi.getFilesystemName("file3"); //실제 서버에 업로드된 파일명을 구함
			    String listeningName = multi.getOriginalFileName("file4");
			    String listeningRealName = multi.getFilesystemName("file4"); //실제 서버에 업로드된 파일명을 구함
			    String jlpt = multi.getParameter("jlpt");
			    
			    VO vo = new VO();
			    
			    vo.setTitle(title);
			    vo.setWriter(writer); //임의로 저장(글쓰기 화면엔 작성자(writer)가 없는데 dao와 테이블에는 작성자가 있기때문에 null값이 넘어가면 안되니까)
			    vo.setWordname(wordName);
			    vo.setWordrealname(wordRealName);
			    vo.setGrammarname(grammarName);
			    vo.setGrammarrealname(grammarRealName);
			    vo.setReadingname(readingName);
			    vo.setReadingrealname(readingRealName);
			    vo.setListeningname(listeningName);
			    vo.setListeningrealname(listeningRealName);
			    vo.setJlpt(jlpt);
			    
			    String jj = multi.getParameter("jj");
			    //앞으로 DAO 싱글톤으로 해라
			    // DBManager는 싱글톤 X
			    DAO dao = DAO.getInstance();
			    dao.Insert(vo, jj);
			    
			    response.sendRedirect("jlpt.do?jj="+jj);
	 }//-> 글쓰기 저장
}
