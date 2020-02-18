package com.nbrown.quizmanager.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher; 

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private DataSource dataSource;
    
    @Value("${spring.queries.users-query}")
    private String usersQuery;
    
    @Value("${spring.queries.perms-query}")
    private String permsQuery;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
    			.usersByUsernameQuery(usersQuery)
    			.authoritiesByUsernameQuery(permsQuery)
    			.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests()
	    		.antMatchers("/", "/login", "/loggedout").permitAll()
				.antMatchers("/quizzes").hasAnyAuthority("EDIT", "VIEW", "RESTRICTED")
				.antMatchers("/quizzes/create").hasAnyAuthority("EDIT")
				.antMatchers("/quizzes/{id}/questions/add").hasAnyAuthority("EDIT")
				.antMatchers("/quizzes/{id}/questions/edit").hasAnyAuthority("EDIT")
				.antMatchers("/quizzes/{id}/questions/answer/view").hasAnyAuthority("EDIT","VIEW")
				.antMatchers("/quizzes/{id}/questions/answer/add").hasAnyAuthority("EDIT")
				.antMatchers("/quizzes/{id}/questions/answer/edit").hasAnyAuthority("EDIT")
				.anyRequest().authenticated()
			.and()
				 .csrf().disable().formLogin()
				 .loginPage("/login")
				 .failureUrl("/login?error=true")
				 .defaultSuccessUrl("/quizzes")
				 .usernameParameter("username")
				 .passwordParameter("password")
			.and()
			  .logout()
			  .logoutUrl("/logout")
			  .logoutSuccessUrl("/loggedout")
			  .deleteCookies("remove")
              .invalidateHttpSession(true)
              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
			  .exceptionHandling()
			  .accessDeniedPage("/access-denied");
    }	
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/resources/**", "/static/**", "/templates/**", "/js/**", "/css/**", "/images/**");
    }
}
