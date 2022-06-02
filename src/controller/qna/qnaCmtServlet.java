package controller.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.JjcmtDAO;
import dto.JjcmtDTO;

@WebServlet("/qnaCmt.do")
public class qnaCmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qnaCmtServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		JjcmtDAO cdao = JjcmtDAO.getInstance();
		
		List<JjcmtDTO> clist = cdao.selectJjcmt(bno);
		
		// gson 데이터 형식으로 데이터 리턴하는 방법
		
		Gson gson = new Gson();
		
		String cmtList = gson.toJson(clist);
		System.out.println("cmtList: "+cmtList);
		PrintWriter out = response.getWriter();
		out.print(cmtList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 요청형식
		
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		JjcmtDTO cvo = new JjcmtDTO();
		cvo.setBno(bno);
		cvo.setWriter(writer);
		cvo.setContent(content);
		
		JjcmtDAO cdao = JjcmtDAO.getInstance();
		int result = cdao.JjcmtInsert(cvo);
		response.setContentType("application/x-json; charset=utf-8");
		// 응답형식
		
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}

}
