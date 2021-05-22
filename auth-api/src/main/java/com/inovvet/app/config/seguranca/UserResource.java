package com.inovvet.app.config.seguranca;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oauth/user")
@RestController
public class UserResource {

	@GetMapping
	public ResponseEntity<?> user(Principal principal) {
//		OAuth2Authentication oauth =  (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

		return ResponseEntity.ok(principal);

	}

}
