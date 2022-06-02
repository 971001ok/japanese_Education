package controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QnaDAO;
import dto.QnaDTO;

@WebServlet("/qnawritepro.do")
public class qnawriteproStervlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	      
	      QnaDTO ndto = new QnaDTO();
	      
	      ndto.setWriter(request.getParameter("writer"));
	      ndto.setTitle(request.getParameter("title"));
	      ndto.setQcontent(request.getParameter("qcontent"));
	      
	      QnaDAO ndao = QnaDAO.getInstance();
	      
	      ndao.qnaInsert(ndto);
	      
	      response.sendRedirect("qna.do");
		
	}

	}
