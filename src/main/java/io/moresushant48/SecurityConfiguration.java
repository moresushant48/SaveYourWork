package io.moresushant48;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String roleQuery;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(roleQuery)
		.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/contactus").permitAll()
		.antMatchers("/forgotUsername").permitAll()
		.antMatchers("/forgotPassword").permitAll()
		.antMatchers("/newPassword").permitAll()
		.antMatchers("/gotNewPassword").permitAll()
		.antMatchers("/rest/**").permitAll()
		.antMatchers("/uploads/*").permitAll()
		.antMatchers("/{username}/**").permitAll()
		.antMatchers("/admin/**").hasAuthority("SUPER_USER")
		.antMatchers("/file-home/**").hasAnyAuthority("SUPER_USER","ADMIN_USER","SITE_USER")
		.anyRequest().authenticated()
		.and()
		
		//login
		.csrf().disable().formLogin()
		.loginPage("/login")
		.failureUrl("/login?error=true")
		.defaultSuccessUrl("/file-home")
		.usernameParameter("username")
		.passwordParameter("password")
		.and()
		
		// Session Management
		.sessionManagement()
		.maximumSessions(1)
		.maxSessionsPreventsLogin(true)
		.expiredUrl("/login?expired")
		.sessionRegistry(sessionRegistry())
		.and().and()
				
		//logout
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.clearAuthentication(true)
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.and()
		
		.rememberMe().key("moresushant48")
		.and()
		
		.cors().and()
		
		//exception
		.exceptionHandling()
		.accessDeniedPage("/accessDenied");
		super.configure(http);
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**","/rest/**");
	}
}
