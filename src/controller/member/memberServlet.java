package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JjmemberDAO;
import dto.JjmemberDTO;
import utility.SecurityPassword1;


@WebServlet("/member.do")
public class memberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public memberServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("member/member.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// DTO set
		JjmemberDTO dto = new JjmemberDTO();
		JjmemberDAO mdao = new JjmemberDAO();
		
		String sid = (String)request.getSession().getAttribute("userid");
	
		if(sid != null) {
			dto.setId(request.getParameter("id"));
			if(request.getParameter("pw1") != ""){
				dto.setPw(SecurityPassword1.encoding(request.getParameter("pw1")));
			}
			dto.setPhone(request.getParameter("phone"));
			dto.setEmail(request.getParameter("email"));
			dto.setPost(request.getParameter("post"));
			dto.setAddress1(request.getParameter("address1"));
			dto.setAddress2(request.getParameter("address2"));
			
			mdao.setMemberEdit(dto);
		}else {
			dto.setId(request.getParameter("id"));
			dto.setPw(SecurityPassword1.encoding(request.getParameter("pw1")));
			dto.setPhone(request.getParameter("phone"));
			dto.setEmail(request.getParameter("email"));
			dto.setPost(request.getParameter("post"));
			dto.setAddress1(request.getParameter("address1"));
			dto.setAddress2(request.getParameter("address2"));
			
			mdao.memberJoin(dto);
		}
		mdao.memberJoin(dto);
		// DAO 자료저장 메서드 호출 실행
		response.sendRedirect("index.jsp");
	}

	}

