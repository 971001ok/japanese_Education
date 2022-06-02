package controller.problems;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dto.VO;

@WebServlet("/jlptDelete.do")
public class jlptDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jlptDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		System.out.println("bnobno"+bno);
		DAO dao = DAO.getInstance();
		
		String jj = request.getParameter("jj");
		System.out.println("jjjj"+jj);
		dao.Delete(jj, bno);
		
		request.setAttribute("jj", jj);
		
		response.sendRedirect("jlpt.do?jj="+jj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String jj= request.getParameter("jj");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		DAO dao = DAO.getInstance();
		
		dao.Edit(bno, jj);
		
		request.setAttribute("jj", jj);
		
		response.sendRedirect("jlpt.do");
	}

}
