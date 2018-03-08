package seniorproject.commercebank2.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommerceBankUtils {

	public static String generateHash(String password, String salt) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes());
			byte bytes[] = md.digest(password.getBytes());

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String generateSalt() {
		return String.valueOf(System.currentTimeMillis());
	}
	
}
