package controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.JjmemberDAO;
import utility.SecurityPassword1;


@WebServlet("/jlogin.do")
public class jloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public jloginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pw=SecurityPassword1.encoding(request.getParameter("pw"));
		
		JjmemberDAO jdao = new JjmemberDAO();
		int result = jdao.memberIdPwCheck(id,pw);
		
		HttpSession session = request.getSession();
		
		String url = "";
		
		if(result == 1) {
			session.setAttribute("userid", id);
			url="index.jsp";
		}else if(result == 0) {
			request.setAttribute("msg", "IDまたは暗証番号のご確認ください。");
			url="login/login.jsp";
		}else if(result == -1) {
			request.setAttribute("msg", "存在しないIDです。");
			url="login/login.jsp";
	}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

}

}
