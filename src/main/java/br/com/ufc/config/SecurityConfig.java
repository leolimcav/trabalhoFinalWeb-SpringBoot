package br.com.ufc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ufc.security.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementation userDetailsServiceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/logout").hasRole("USER")
			.antMatchers("/register").permitAll()
			.antMatchers("/users/list").hasRole("ADMIN")
			.antMatchers("/users/delete/{userId}").hasRole("ADMIN")
			.antMatchers("/users/update/{userId}").authenticated()
			.antMatchers("/user/orders/{userId}").hasRole("USER")
			.antMatchers("/plates/gallery").permitAll()
			.antMatchers("/plates/create").hasRole("ADMIN")
			.antMatchers("/plates/update/{plateId}").hasRole("ADMIN")
			.antMatchers("/plates/delete/{plateId}").hasRole("ADMIN")
			.antMatchers("/cart/view").authenticated()
			.antMatchers("/cart/buy/{plateId}").authenticated()
			.antMatchers("/cart/checkout").authenticated()
			.antMatchers("/orders/create").authenticated()
			.antMatchers("/orders/list").hasRole("ADMIN")
			.antMatchers("/orders/update/{orderId}").hasRole("ADMIN")
			.antMatchers("/orders/delete/{orderId}").hasRole("ADMIN")
			.anyRequest().authenticated()
			
			.and()
			.formLogin()
			.loginPage("/login").defaultSuccessUrl("/").permitAll()
			.permitAll()
			
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	}
}
