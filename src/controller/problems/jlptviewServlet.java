package controller.problems;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.JjcmtDAO;
import dto.JjcmtDTO;
import dto.VO;
import utility.Criteria;

@WebServlet("/jlptview.do")
public class jlptviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jlptviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		String jj=request.getParameter("jj");
		
		Criteria cri = new Criteria(pageNum,amount);
		
		DAO dao = DAO.getInstance();
		
		VO jvo = dao.View(bno,jj);
		VO prev = dao.prevByBno(bno,jj);
		VO next = dao.nextByBno(bno,jj);
		
		request.setAttribute("cri", cri);
		request.setAttribute("view", jvo);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);	
		
		dao.View(bno, jj);
	
		request.setAttribute("jj", jj);
		request.setAttribute("jvo", jvo);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("problems/jlptview.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
