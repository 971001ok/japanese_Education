package controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.QnaDAO;

@WebServlet("/qnaDelete.do")
public class qnaDeleteSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qnaDeleteSevlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qbno = Integer.parseInt(request.getParameter("qbno"));

		QnaDAO qdao = QnaDAO.getInstance();
		
		qdao.Delete(qbno);
		
		response.sendRedirect("qna.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.Edit(qbno);
		
		response.sendRedirect("qna.do");
	}

}
