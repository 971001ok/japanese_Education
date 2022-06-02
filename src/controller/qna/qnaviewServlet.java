package controller.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JjcmtDAO;
import dao.QnaDAO;
import dto.JjcmtDTO;
import dto.QnaDTO;
import utility.Criteria;

@WebServlet("/qnaview.do")
public class qnaviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qnaviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		Criteria cri = new Criteria(pageNum,amount);
			
		QnaDAO dao = QnaDAO.getInstance();
		JjcmtDAO cdao = JjcmtDAO.getInstance();
		
		QnaDTO dto = dao.qnaView(qbno);
		QnaDTO prev = dao.prevByBno(qbno);
		QnaDTO next = dao.nextByBno(qbno);
		
		List<JjcmtDTO> clist = cdao.selectJjcmt(qbno);
			
		request.setAttribute("cri", cri);
		request.setAttribute("view", dto);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		
		request.setAttribute("cmtList", clist);
		
		RequestDispatcher rd = request.getRequestDispatcher("qna/qnaview.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
