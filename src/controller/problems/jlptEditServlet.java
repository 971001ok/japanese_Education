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

@WebServlet("/jlptEdit.do")
public class jlptEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jlptEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		DAO dao = DAO.getInstance();
		
		String jj = request.getParameter("jj");
		
		VO vo = dao.Edit(bno, jj);
				
		request.setAttribute("vo", vo);
		request.setAttribute("jj", jj);
		
		RequestDispatcher rd = request.getRequestDispatcher("problems/jlptEdit.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		DAO dao = DAO.getInstance();
		VO vo = new VO();
		
		String jj=request.getParameter("jj");
		
		String saveDirectory = "upload";
		int maxPostSize = 20*1024*1024;
		String enctype ="utf-8";
		
		ServletContext context = request.getServletContext();
		String path = context.getRealPath(saveDirectory);
		System.out.println("서버상의 업로드 실제 디렉토리: "+path);
		
		MultipartRequest multi = new MultipartRequest (
				request,
				path,
				maxPostSize,
				enctype,
				new DefaultFileRenamePolicy()
				);
		
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
		
		int bno= Integer.parseInt(multi.getParameter("bno"));
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
		
		vo.setBno(bno);
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setWordname(wordName);
		vo.setWordrealname(wordRealName);
		vo.setGrammarname(grammarName);
		vo.setGrammarrealname(grammarRealName);
		vo.setReadingname(readingName);
		vo.setReadingrealname(readingRealName);
		vo.setListeningname(listeningName);
		vo.setListeningrealname(listeningRealName);
		vo.setJlpt(jlpt);
		
		dao.Edit(bno, jj);
		
		dao.JlptEdit(vo, jj);
		
		response.sendRedirect("jlpt.do?jj="+jj);
	}

}
