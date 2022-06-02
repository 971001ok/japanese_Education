package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/mypage.do")
public class mypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public mypageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("member/mypage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String certinumber = request.getParameter("certinumber");
		String AuthenKey = (String) request.getSession().getAttribute("AuthenticationKey");
		
		JSONObject obj = new JSONObject();

		if(certinumber.equals(AuthenKey)) {
			obj.put("msg", "会員情報を変更致します");
			obj.put("check", "ok");
			//{msg:"회원정보를 수정합니다", check:"ok"}
		}else {
			obj.put("msg", "間違った認証番号です");
			obj.put("check", "nok");
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json,charset=utf-8");
		response.getWriter().print(obj);
		// success:function(data)로 obj가 전송된다.
	}

}
