
package com.feedbackmanagement.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.users.model.AuthResponse;
import com.feedbackmanagement.users.model.LoginRequest;
import com.feedbackmanagement.users.model.Message;
import com.feedbackmanagement.users.model.User;
import com.feedbackmanagement.users.repoitory.UserRepository;
import com.feedbackmanagement.users.security.JWTUtil;
import com.feedbackmanagement.users.security.PBKDF2Encoder;

import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PBKDF2Encoder passwordEncoder;
	

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody LoginRequest ar) {
		return userRepository.findByUserName(ar.getUserName()).map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}


	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Mono userRegistration(ServerRequest sererRequest) {
		Mono<User> userMono = sererRequest.bodyToMono(User.class);
		return userMono.map(user -> {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return user;
		}).flatMap(user -> userRepository.findById(user.getEmployeeId()).flatMap(
				dbUser -> ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("User already exist"))))
				.switchIfEmpty(userRepository.save(user).flatMap(savedUser -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(savedUser)))));
	}
}
