package dev.leanio.blogapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenhaComBcrypt {

	public static void main(String[] args) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		System.out.println(bc.encode("mg"));
		
	}
}
