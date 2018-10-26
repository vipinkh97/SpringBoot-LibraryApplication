package io.vipin.dtc.library;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class Config extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable().authorizeRequests()
					.antMatchers("/books").permitAll()
					.antMatchers("/books/*/").hasAnyRole("USER","ADMIN")
					.antMatchers("books/book/add").hasRole("ADMIN")
					.antMatchers("/books/*/update").hasRole("ADMIN")
					.antMatchers("/books/*/delete").hasRole("ADMIN")
					.anyRequest().fullyAuthenticated()
					.and()
					.formLogin()
					.loginPage("/login.jsp")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/books")
		            .permitAll()
		            .failureUrl("/error") 
					.and()
					.logout()
					.permitAll()
		            .logoutUrl("/logout")
		            .logoutSuccessUrl("/login.jsp");	
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .passwordEncoder(new PasswordEncoder() {
				
				@Override
				public boolean matches(CharSequence rawPassword, String encodedPassword) {
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public String encode(CharSequence rawPassword) {
					// TODO Auto-generated method stub
					return rawPassword.toString();
				}
			})
	        .withUser("user").password("password").roles("USER");
	    
	    auth.inMemoryAuthentication()
        .passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}
		})
        .withUser("admin").password("password").roles("ADMIN");
	    
	    auth.userDetailsService(inMemoryUserDetailsManager());
	}
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
	        // load you user here
	        final Properties users = new Properties();
	        return new InMemoryUserDetailsManager(users);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	}

