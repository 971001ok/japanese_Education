package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JjmemberDAO;


@WebServlet("/memberIdCheck.do")
public class memberIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public memberIdCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 요청
		String id= request.getParameter("id");
//		// 메소드 호출
//		MemberDAO dao = new MemberDAO();
//		int result = dao.getSelectIdCheck()
		int result = new JjmemberDAO().memberIdCheck(id);
		// 리턴값 받기
		PrintWriter out = response.getWriter();
		out.print(result);
		// jsp 보내기
	}

}
