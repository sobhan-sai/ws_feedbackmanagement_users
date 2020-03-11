/*
 * //package com.feedbackmanagement.users.configuration; // //import
 * java.util.List; //import java.util.stream.Collectors; // //import
 * org.springframework.beans.factory.annotation.Autowired; //import
 * org.springframework.security.authentication.ReactiveAuthenticationManager;
 * //import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; //import
 * org.springframework.security.core.Authentication; //import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; //import
 * org.springframework.security.core.context.SecurityContextHolder; //import
 * org.springframework.stereotype.Component; // //import
 * com.feedbackmanagement.users.constants.Constants; // //import
 * io.jsonwebtoken.Claims; //import reactor.core.publisher.Mono; // //@Component
 * //public class AuthenticationManager implements
 * ReactiveAuthenticationManager{ // @Autowired // private TokenProvider
 * tokenProvider; // @Override // public Mono<Authentication>
 * authenticate(Authentication authentication) { // String authToken =
 * authentication.getCredentials().toString(); // String username; // try { //
 * username = tokenProvider.getUsernameFromToken(authToken); // } catch
 * (Exception e) { // username = null; // } // if (username != null && !
 * tokenProvider.isTokenExpired(authToken)) { // Claims claims =
 * tokenProvider.getAllClaimsFromToken(authToken); // List roles =
 * claims.get(Constants.AUTHORITIES_KEY, List.class); // List authorities =
 * roles.stream().map(role -> new
 * SimpleGrantedAuthority(role)).collect(Collectors.toList()); //
 * UsernamePasswordAuthenticationToken auth = new
 * UsernamePasswordAuthenticationToken(username, username, authorities); //
 * SecurityContextHolder.getContext().setAuthentication(new
 * AuthenticatedUser(username, authorities)); // return Mono.just(auth); // }
 * else { // return Mono.empty(); // } // } // } // //}
 */