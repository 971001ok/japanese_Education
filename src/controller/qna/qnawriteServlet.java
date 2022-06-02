package controller.qna;

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

import dao.QnaDAO;
import dto.QnaDTO;

@WebServlet("/qnawrite.do")
public class qnawriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qnawriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("qna/qnawrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		QnaDTO qdto = new QnaDTO();
		qdto.setWriter(request.getParameter("writer"));
		qdto.setTitle(request.getParameter("title"));
		qdto.setQcontent(request.getParameter("content"));

		QnaDAO qdao = QnaDAO.getInstance();
		
		qdao.qnaInsert(qdto);
		
		response.sendRedirect("qna.do");
	}

}
