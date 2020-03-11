package com.feedbackmanagement.users.repoitory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.feedbackmanagement.users.model.User;

import reactor.core.publisher.Mono;



public interface UserRepository extends ReactiveMongoRepository<User, String> {

	Mono<User> findByUserName(String username);
	Mono<User> findByEmployeeId(String employeeId);
	//Mono<User> findByEmployeeId(String employeeId);
}
