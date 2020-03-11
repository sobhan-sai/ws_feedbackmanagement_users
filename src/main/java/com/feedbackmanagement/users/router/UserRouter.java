package com.feedbackmanagement.users.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.feedbackmanagement.users.handler.UserHandler;

@Configuration
public class UserRouter {

	@Bean
	public RouterFunction<ServerResponse> userRoute(UserHandler userHandler) {
		return RouterFunctions
				.route(RequestPredicates.POST("/auth/register")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::userRegistration)
				.andRoute(
						RequestPredicates.POST("/auth/login").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						userHandler::loginUser)
				.andRoute(
						RequestPredicates.GET("/auth/users").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						userHandler::getAllUsers)
				.andRoute(
						RequestPredicates.PUT("/users/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						userHandler::updateRole)
				.andRoute(RequestPredicates.PUT("/users/{id}/{status}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::inactivateUser);
	}
}
