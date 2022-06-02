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
import dto.VO;
import utility.Criteria;
import utility.PageVO;

@WebServlet("/jlpt.do")
public class jlptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jlptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String sel="";
	    String word = "";
	    String query="";
	      
	    int pageNum = 1;
	    int amount = 10;
	      
	    if(request.getParameter("pageNum") != null) {
	       pageNum = Integer.parseInt(request.getParameter("pageNum"));
	       amount = Integer.parseInt(request.getParameter("amount"));
	    }
	      
	    if(request.getParameter("sel") != null && !request.getParameter("word").equals("")) {
	       sel = request.getParameter("sel");
	       word = request.getParameter("word");
	       query = sel + " like '%" + word + "%'"; //title like '%코딩%'
	    }
	    
	    Criteria cri = new Criteria();
	    cri.setPageNum(pageNum);
	    cri.setAmount(amount);
	    cri.setType(sel);
	    cri.setKeyword(word);
	      
	    DAO dao = DAO.getInstance();
		
	    String jj= request.getParameter("jj");
	    
	    List<VO> list = dao.getListWithPaging(cri, query, jj);
	    
	    int count = dao.Count(query, jj);
	    
	    PageVO pvo = new PageVO(cri,count);

	    request.setAttribute("pageMaker", pvo);
	    request.setAttribute("qlist", list);
	    request.setAttribute("count", count);
	    
	    List<VO> list2 = dao.Select(jj);
	    List<VO> list1 = dao.JjSelect(jj, query);
	    
	    request.setAttribute("list", list2);
	    request.setAttribute("list1", list1);
	    request.setAttribute("jj", jj);
		
		RequestDispatcher rd=request.getRequestDispatcher("problems/jlpt.jsp?jj="+jj);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
