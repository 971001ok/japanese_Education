package controller.member;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/email.do")
public class emailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public emailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// https://smujihoon.tistory.com/96 참조
		
		String email = request.getParameter("email");
			
		//mail server 설정
		String host = "smtp.naver.com";
		String user = ""; //자신의 네이버 계정
		String password = "";//자신의 네이버 패스워드
	        
		//메일 받을 주소
		String to_email = email;
	        
		//SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
	  	props.put("mail.smtp.ssl.enable", "true");
	  	props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        
	  	//인증 번호 생성기
	  	StringBuffer temp =new StringBuffer();
	   	Random rnd = new Random();
		for(int i=0;i<10;i++){
			int rIndex = rnd.nextInt(3); // 0 1 2
			switch (rIndex) {
				case 0:
					// a-z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 97)); // (0~25)+97 
	                break;
	            case 1:
	                // A-Z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	                break;
	            case 2:
	                // 0-9
	                temp.append((rnd.nextInt(10)));
	                break;
	            }
		}
		String AuthenticationKey = temp.toString();
		System.out.println(AuthenticationKey);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,password);
			}
		});
	        
		//email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "JAPANESE_EDUCATION"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
			//메일 제목
			msg.setSubject("こんにちは JAPANESE_EDUCATION 認証メールでございます。");
			//메일 내용
			msg.setText("認証番号 :"+temp);
			Transport.send(msg);
			System.out.println("email電送");
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
		// 인증번호를 AuthenticationKey 세션 속성에 저장한다.
		JSONObject obj = new JSONObject();
		obj.put("msg", "認証番号電送完了");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json, charset=utf-8");
		response.getWriter().print(obj);
		}
}