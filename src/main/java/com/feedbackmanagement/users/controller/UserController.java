/*
 * package com.feedbackmanagement.users.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.MediaType; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.reactive.function.BodyInserters; import
 * org.springframework.web.reactive.function.server.ServerRequest; import
 * org.springframework.web.reactive.function.server.ServerResponse;
 * 
 * import com.feedbackmanagement.users.model.Message; import
 * com.feedbackmanagement.users.model.User; import
 * com.feedbackmanagement.users.repoitory.UserRepository;
 * 
 * import reactor.core.publisher.Mono;
 * 
 * @RestController
 * 
 * @RequestMapping("/feedbackmanagement") public class UserController {
 * 
 * @Autowired BCryptPasswordEncoder passwordEncoder;
 * 
 * @Autowired UserRepository userRepository;
 * 
 * @PostMapping(value = "/user") public Mono userRegistration(ServerRequest
 * sererRequest) { Mono<User> userMono = sererRequest.bodyToMono(User.class);
 * return userMono.map(user -> {
 * user.setPassword(passwordEncoder.encode(user.getPassword())); return user;
 * }).flatMap(user -> userRepository.findByUsername(user.getUserName())
 * .flatMap(dbUser -> ServerResponse.badRequest()
 * .body(BodyInserters.fromObject(new Message("User already exist"))))
 * .switchIfEmpty(userRepository.save(user).flatMap(savedUser ->
 * ServerResponse.ok()
 * .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(
 * savedUser))))); } }
 */