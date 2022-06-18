package com.example.demo.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginError").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/newBook").hasAnyRole( "USER");
        http.authorizeRequests().antMatchers("/editBook/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/removeBook/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/addGames").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/addGamesError").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/checkout").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/user/*").fullyAuthenticated();


        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/index");
        http.formLogin().failureUrl("/loginError");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/index");
    }
}


