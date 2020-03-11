package com.feedbackmanagement.users.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.users.model.LoginRequest;
import com.feedbackmanagement.users.model.Message;
import com.feedbackmanagement.users.model.Role;
import com.feedbackmanagement.users.model.User;
import com.feedbackmanagement.users.repoitory.UserRepository;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	@Autowired
	UserRepository userRepository;

	public Mono<ServerResponse> userRegistration(ServerRequest sererRequest) {
		Mono<User> userMono = sererRequest.bodyToMono(User.class);
		return userMono.flatMap(user -> userRepository.findById(user.getEmployeeId()).flatMap(
				dbUser -> ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("User already exist"))))
				.switchIfEmpty(userRepository.save(user).flatMap(savedUser -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(savedUser)))));
	}

	public Mono<ServerResponse> loginUser(ServerRequest sererRequest) {
		Mono<LoginRequest> userMono = sererRequest.bodyToMono(LoginRequest.class);
		return userMono.flatMap(user -> userRepository.findByUserName(user.getUserName()).log().flatMap(dbUser -> {
			if (dbUser.getPassword().equals(user.getPassword())) {
				return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromObject(new Message("Login Succesful")));
			} else {
				return ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("Invalid Credentials")));
			}
		}).switchIfEmpty(
				ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("User Doesnot exist")))));
	}

	public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userRepository.findAll(), User.class);
	}

	public Mono<ServerResponse> updateRole(ServerRequest serverRequest) {
		String employeeId = serverRequest.pathVariable("id");
		Mono<User> updatedUser = serverRequest.bodyToMono(User.class).log().flatMap((item) -> {

			Mono<User> userMono = userRepository.findByEmployeeId(employeeId).flatMap(currentItem -> {
				currentItem.setRole(item.getRole());
				return userRepository.save(currentItem);

			}).log();
			return userMono;
		}).log();
		return updatedUser.flatMap(responseItem->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(responseItem)).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("User Doesnot exist")))));
	}
	
	public Mono<ServerResponse> inactivateUser(ServerRequest serverRequest) {
		String employeeId = serverRequest.pathVariable("id");
		String status= serverRequest.pathVariable("status");
		
		Mono<User> userMono = userRepository.findByEmployeeId(employeeId).flatMap(currentItem -> {
			currentItem.setIsActive(Boolean.parseBoolean(status));
			return userRepository.save(currentItem);

		}).log();
		return userMono.flatMap(responseItem->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(responseItem)).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(new Message("User Doesnot exist")))));
	}
	
	
}
