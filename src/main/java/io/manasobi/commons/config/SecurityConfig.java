package io.manasobi.commons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import io.manasobi.security.MongoDBUserDetailsService;

@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private MongoDBUserDetailsService mongoDBUserDetailsService;

	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mongoDBUserDetailsService);
    }
	
	
	@Override 
	public void configure(WebSecurity web) throws Exception { 
		web
			.ignoring()
			.antMatchers("/resources/**"); 
	} // 리소스에 대해서는 처리하니 않는다.
	
	
	@Override 
	public void configure(HttpSecurity http) throws Exception { 
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/","/login","/login/form**","/reg/**","/logout").permitAll()
				.antMatchers("/publish2","/admin/**").hasRole("ADMIN")
				//.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login") // 로그인 Form을 제공하는 페이지.
				.loginProcessingUrl("/login") // 로그인 폼에서 ID, PW를 전송해야 하는 URL
				.usernameParameter("username") // 로그인 폼에서 ID를 담는 Input name
				.passwordParameter("password") // 로그인 폼에서 PW를 담는 Input name
				.defaultSuccessUrl("/main")
				.failureUrl("/")
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/app/logout") // 로그아웃을 처리할 가상 URL
				.logoutSuccessUrl("/app/seeYouAgain")//
				.permitAll();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder registry) throws Exception { 
		
		/*registry
			.inMemoryAuthentication()
				.withUser("siva").password("siva").roles("USER")
				.and()
				.withUser("admin").password("admin").roles("ADMIN","USER");*/
		
		/*registry.jdbcAuthentication().dataSource(dataSource); 
		registry.userDetailsService(customUserDetailsService);*/
		
		registry.userDetailsService(mongoDBUserDetailsService);
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	            .antMatchers("/", "/public/**").permitAll()
	            .antMatchers("/users/**").hasAuthority("ADMIN")
	            .anyRequest().fullyAuthenticated()
	            .and()
	            .formLogin()
	            .loginPage("/login")
	            .failureUrl("/login?error")
	            .usernameParameter("email")
	            .permitAll()
	            .and()
	            .logout()
	            .logoutUrl("/logout")
	            .deleteCookies("remember-me")
	            .logoutSuccessUrl("/")
	            .permitAll()
	            .and()
	            .rememberMe();
	}*/
	
	/*@PostConstruct
	public void init() {
		
		//SpringSecurityMessageSource ssms = (SpringSecurityMessageSource) wac.getBean("springSecurityMessageSource");
		
		Locale oLocalePL = new Locale("ko","KR");
		LocaleContextHolder.setLocale(oLocalePL);
		
	}*/
	
	
	/*

	  @Override
	  protected void authorizeUrls(ExpressionUrlAuthorizations interceptUrls) {
	    interceptUrls
	      .antMatchers("/app/login/*", "/").permitAll()
	      .antMatchers("/app/**").hasRole("ADMIN");
	  }
	  
	  @Override
	  protected void configure(HttpConfigurator http) throws Exception {
	    configureLogin(http);
	    configureLogout(http);
	    configureSSOFilter(http);
	  }

	  protected void configureLogin(HttpConfigurator http) throws Exception {
	    http
	      .formLogin()
	        .loginPage("/app/login/welcomeToLoginPage")  
	        .usernameParameter("userName")  // 로그인 폼에서 ID를 담는 Input name
		.passwordParameter("password")  // 로그인 폼에서 PW를 담는 Input name
	        .loginProcessingUrl("/app/login/checkLogin") 
	        .defaultSuccessUrl("/app/login/loginSuccess")  // 로그인에 성공했을때, Referer가 없을떄  리다이렉트될 URL. 기본구현에서는 Referer가 있으면 Referer로 이동하게 된다. 
	        .failureUrl("/app/login/loginFailed") // 로그인에 실패했을때 리다이렉트 될 URL
	      .permitAll(); // 이상의 로그인 과정에 필요한 URL들에 대한 접근권한을 허가함 
	  }

	  protected void configureLogout(HttpConfigurator http) throws Exception {
	    http
	      .logout()//
	        . logoutUrl("/app/logout") // 로그아웃을 처리할 가상 URL
	        .logoutSuccessUrl("/app/seeYouAgain")//
	      .permitAll();
	  }

	  protected void configureSSOFilter(HttpConfigurator http) throws Exception {
	    // 여기는 추가적인 필터를 등록하는 법. 대부분의 프로젝트에서 만들어지 추가적인 인증 필터는 UsernamePassword 앞에 추가되면 될듯하다
	    http
	      .addFilterBefore(
	        casSSOAuthenticationProcessingFilter()
	        , UsernamePasswordAuthenticationFilter.class
	      );

	  }

	  // 여기는 추가적인 필터를 초기화 하는 샘플
	  @Bean
	  public CasSSOAuthenticationProcessingFilter casSSOAuthenticationProcessingFilter() {
	    CasSSOAuthenticationProcessingFilter filter 
	      = new CasSSOAuthenticationProcessingFilter("/app/casSSOLoginProcess");
	    filter.setAuthenticationManager(authenticationManager);

	    filter.setAuthenticationSuccessHandler(new CasLoginSuceessHandler());    
	    filter.setAuthenticationFailureHandler(new CasLoginFailureHandler());

	    return filter;
	  }

	  @Override
	  protected void registerAuthentication(AuthenticationRegistry registry) throws Exception {
	    // 테스트를 위한 더미 데이터기반의 인증 추가
	    registry
	      .inMemoryAuthentication()//
	        .withUser("user").password("password").roles("USER")
	        .and()//
	        .withUser("admin").password("password").roles("USER", "ADMIN");
	  }
	}  */ 

}
