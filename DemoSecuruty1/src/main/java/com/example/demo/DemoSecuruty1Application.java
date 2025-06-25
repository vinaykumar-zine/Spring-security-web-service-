package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableWebSecurity
public class DemoSecuruty1Application {
	
	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(DemoSecuruty1Application.class, args);
	}
	
//    @Bean
//    public UserDetailsService userDetailsService() {
//    	var user1 = User.withUsername("vinay")
//    			.password("123456")
//    			.roles("USER")
//    			.build();
//    	var user2 = User.withUsername("shengdana")
//    			.password("789456")
//    			.roles("ADMIN")
//    			.build();
//    	
//    	return new InMemoryUserDetailsManager(user1, user2);
//    }
    
    //configuring http security!
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(t -> t.requestMatchers("/").permitAll()
//										.requestMatchers("/**").hasRole("ADMIN")
										.requestMatchers("/admin/**").hasRole("ADMIN")
										.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
										.anyRequest().authenticated()
									)
									.formLogin(Customizer.withDefaults()) //this enables form login
									.httpBasic(Customizer.withDefaults());	//this enables basic auth!
		return http.build();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
	    return NoOpPasswordEncoder.getInstance();
	}

}
