package controller.anser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnserDAO;
import dto.AnserDTO;

@WebServlet("/viewcount.do")
public class viewcountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public viewcountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		AnserDAO adao = AnserDAO.getInstance();
		AnserDTO adto =new AnserDTO();
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		int result = adao.setViewcount(bno);
		if(result>0) {
			int count = adao.getViewcount(bno);
			PrintWriter out = response.getWriter();
			out.print(count);
		}

	}
}
