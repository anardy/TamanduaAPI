package br.com.tamandua.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Transaction {

	public static final int MIN_LENGTH = 10;

	protected static java.util.Random r = new java.util.Random();
	 
	protected static char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
	'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
	'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
	'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
	'2', '3', '4', '5', '6', '7', '8', '9', };
	
	protected static String get_senha() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < MIN_LENGTH; i++) {
			sb.append(goodChar[r.nextInt(goodChar.length)]);
		}
		return sb.toString();
	}
	
	public String hash_senha(String senha) {
		String hash_senha = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(senha.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			hash_senha = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash_senha;
	}
	
	public String getToken() {
		return hash_senha(get_senha());
	}
	
	public static void main(String args[]) {
		Transaction t = new Transaction();
		System.out.print(t.getToken());
	}
}
