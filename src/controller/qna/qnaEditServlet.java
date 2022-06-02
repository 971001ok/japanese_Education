package controller.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QnaDAO;
import dto.QnaDTO;

@WebServlet("/qnaEdit.do")
public class qnaEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qnaEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		
		QnaDAO dao = QnaDAO.getInstance();
		
		QnaDTO dto = dao.Edit(qbno);
		
		request.setAttribute("dto", dto);
		
		RequestDispatcher rd = request.getRequestDispatcher("qna/qnaEdit.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		QnaDAO dao = QnaDAO.getInstance();
		QnaDTO qdto = new QnaDTO();
		
		qdto.setQbno(Integer.parseInt(request.getParameter("qbno")));
		qdto.setWriter(request.getParameter("writer"));
		qdto.setTitle(request.getParameter("title"));
		qdto.setQcontent(request.getParameter("qcontent"));
		
		dao.QnaEdit(qdto);
		
		response.sendRedirect("qna.do");
	}

}
