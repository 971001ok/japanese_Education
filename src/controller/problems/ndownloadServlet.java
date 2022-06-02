package controller.problems;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ndownload.do")
public class ndownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ndownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fileName = request.getParameter("file"); 
		// 파일 요청
		String directory = this.getServletContext().getRealPath("upload");
		// 실제 업로드 경로를 구한다.
		File file = new File(directory+"\\"+fileName);
		// 기존 파일이나 폴더를 제어한다.
		String mimeType = getServletContext().getMimeType(file.toString());
		// file 클래스 객체의 toString 메서드를 이용하여 자신이 가진 해당 경로 값을 리턴한다.
		
		if(mimeType == null) {
			response.setContentType("application/octet-stram");
			// 이즌 바이너리 타입(application/octet-stram)으로 다운로드 받겠습니다.
			// mime 파일변환
		}
		
		String ndownloadName = null;
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {
			ndownloadName = new String(fileName.getBytes("UTF-8"), "8859_1");
		}else {
			ndownloadName = new String(fileName.getBytes("EUC=KR"), "8859_1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\" "+ndownloadName+"\";");
		// 일반적인 HTTP 응답에서 Content-Disposition 헤더는 컨텐츠가 브라우저에 inline 되어야 하는 웹페이지 자체이거나 웹페이지의 일부인지, 
		// 아니면 attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지를 알려주는 헤더입니다.
		
		FileInputStream fileInputStream = new FileInputStream(file);
		
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b[] = new byte[1024];
		int data = 0;
		
		while((data= (fileInputStream.read(b,0,b.length))) != -1) {
			// 스트림 끝에 도달하면 -1을 리턴한다.
			// read(b,0,b.length) b배열의 크기만큼 0부터 length까지 읽어온다.
			servletOutputStream.write(b, 0, data);
			// b배열의 크기만큼 0부터 data까지 디스크에 저장한다.
		}
		
		servletOutputStream.flush();
		// 버퍼에 남아있는 데이터가 있으면 내보내고 버퍼를 비운다.
		servletOutputStream.close();
		fileInputStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
