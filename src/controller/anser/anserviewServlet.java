package controller.anser;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnserDAO;
import dao.JjcmtDAO;
import dto.AnserDTO;
import dto.JjcmtDTO;
import utility.Criteria;

@WebServlet("/anserview.do")
public class anserviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public anserviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		Criteria cri = new Criteria(pageNum,amount);
			
		AnserDAO dao = AnserDAO.getInstance();
		JjcmtDAO cdao = JjcmtDAO.getInstance();
		
		AnserDTO dto = dao.AnserView(bno);
		AnserDTO prev = dao.prevByBno(bno);
		AnserDTO next = dao.nextByBno(bno);
		
		List<JjcmtDTO> clist = cdao.selectJjcmt(bno);
			
		request.setAttribute("cri", cri);
		request.setAttribute("view", dto);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		
		request.setAttribute("cmtList", clist);
		
		RequestDispatcher rd = request.getRequestDispatcher("anser/anserview.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
