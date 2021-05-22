package com.inovvet.app.config.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PwEnconder implements PasswordEncoder {

	private static final String SALT = "{excelsior}";

	@Override
	public String encode(CharSequence rawPassword) {
		return DigestUtils.sha256Hex(SALT + rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String encryptedPassword = DigestUtils.sha256Hex(SALT + rawPassword);
		return encryptedPassword.equals(encodedPassword);
	}
	
	public static void main(String[] args) throws Exception {
		PwEnconder pw = new PwEnconder();
		System.out.println(pw.encode("2"));
	}

}
