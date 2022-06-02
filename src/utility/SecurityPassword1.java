package utility;

import java.security.MessageDigest;

public class SecurityPassword1 {

	// 패스워드 문자열을 전달받아 암호화하여 반환하는 메소드
	public static String encoding(String pw) {
		String newPassword = "";
			
		try {
			// 암호화 알고리즘을 선택하여 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// 암호화 하고자 하는 패스워드를 update()메소드를 이용하여 전달
			// String 객체가 아닌 byte 배열 형태로 전달
			md.update(pw.getBytes());
			// 패스워드 문자열을 digest() 메소드를 이용하여 암호화 하고 byte배열로 반환하여 저장
			byte[] encodeData = md.digest();
			// 암호화 된 byte배열을 string 객체로 반환하여 저장
			for(int i=0; i<encodeData.length; i++) {
				// byte 데이터를 16진수 문자열로 변환하여 반환, 문자열에 추가(한번 더 암호화)
				newPassword += Integer.toHexString(encodeData[i]&0xFF);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newPassword;
	}
}
