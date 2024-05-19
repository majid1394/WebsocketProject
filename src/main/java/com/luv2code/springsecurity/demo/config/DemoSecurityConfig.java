package com.luv2code.springsecurity.demo.config;


import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment env;

	@Autowired
	AuthenticationService authenticationService;




	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;
	}



	@Autowired
	DataSource dataSource;



/*	@Autowired
	protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
// add our users for in mysql authentication
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select fk_username, role from user_roles where fk_username=?")
				.passwordEncoder(new BCryptPasswordEncoder());
						// add our users for in memory authentication
	*//*	auth.inMemoryAuthentication()
			.withUser("john").password("test123").roles("EMPLOYEE");

		auth.inMemoryAuthentication()
		.withUser("mary").password("test123").roles("MANAGER");

		auth.inMemoryAuthentication()
		.withUser("susan").password("test123").roles("ADMIN");*//*

	}*/



	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
			auth.userDetailsService(authenticationService)/*.passwordEncoder(encoder)*/;


	}






	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/script/**", "/css/**","/resources/css/**","/resources/js/**", "/registerTheUser","/signUp").permitAll()
				.antMatchers("/customer/list").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.usernameParameter("username")
				.passwordParameter("password")
				/*.defaultSuccessUrl("/customer/list", true)*/
				.defaultSuccessUrl("/loggedUsers", true)
				/*.defaultSuccessUrl("/customer/list", true)*/
				.permitAll()
				.and()
				.logout().logoutSuccessUrl("/login?logout")
				.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
				.csrf().disable();


	/*	http.authorizeRequests()
				.anyRequest().access("authenticateTheUser")
				.antMatchers("/registerTheUser").permitAll()
				.antMatchers("/customer/list").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and()

					.formLogin()
				.loginPage("/showMyLoginPage").usernameParameter("username").passwordParameter("password")   //in controller
				.loginProcessingUrl("/authenticateTheUser")// in jsp file action
			*//*	.loginProcessingUrl("/registerTheUser")// in jsp file action*//*
				.defaultSuccessUrl("/customer/list",true)
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
				.logout().permitAll();*/

		/*http.authorizeRequests()
				.antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll()
				.and()
				.formLogin().loginPage("/login")
				.usernameParameter("username").passwordParameter("password")
				.and()
				.logout().logoutSuccessUrl("/login?logout")
				.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
				.csrf();*/
	}

}

/*	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage").usernameParameter("username").passwordParameter("password")   //in controller
				.loginProcessingUrl("/authenticateTheUser")// in jsp file action
				.defaultSuccessUrl("/customer/list",true)

*//*				.and().logout().logoutSuccessUrl("/login?logout")*//**//*

				.permitAll()
			.and()
			.logout().permitAll();
		
	}
*//*

}*/













