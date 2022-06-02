package controller.anser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.AnserDAO;
import dto.AnserDTO;

@WebServlet("/anserwrite.do")
public class anserwriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public anserwriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("anser/anserwrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String savepath = "upload";
		int maxPostSize = 20*1024*1024;
		String enctype="utf-8";
		
		ServletContext context = getServletContext();
		String path = context.getRealPath(savepath);
		System.out.println("서버상의 업로드 실제 디렉토리: "+path);
		
		MultipartRequest multi = new MultipartRequest(
				request,
				path,
				maxPostSize,
				enctype,
				new DefaultFileRenamePolicy()
				);
		
		String imgurl = multi.getFilesystemName("imgurl");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		
		AnserDTO adto = new AnserDTO();
		adto.setTitle(title);
		adto.setContent(content);
		adto.setWriter("管理者");
		adto.setImgurl(imgurl);
		
		AnserDAO adao = AnserDAO.getInstance();
		adao.anserInsert(adto);
		
		response.sendRedirect("anser.do");
	}
}