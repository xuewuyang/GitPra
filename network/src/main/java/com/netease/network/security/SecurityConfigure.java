package com.netease.network.security;

import com.netease.network.util.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("seller").password("relles").roles("SELLER")
				.and()
				.withUser("buyer").password("reyub").roles("BUYER");
        //auth.inMemoryAuthentication().withUser("buyer").password("reyub").roles("BUYER");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("seller").password("relles").roles("SELLER")
				.and()
				.passwordEncoder(new MyPasswordEncoder()).withUser("buyer").password("reyub").roles("BUYER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
				.authorizeRequests()
				.antMatchers("/","/home","/getIndexData","/login","/show","/search").permitAll()
				.antMatchers("/seller**","/public").access("hasRole('SELLER')")
				.antMatchers("/buyer**","/shoppingcart","/account").access("hasRole('BUYER')")

				 .and()
				.formLogin().loginPage("/login")
		    .usernameParameter("username")
		    .passwordParameter("password")
				 .successHandler(customSuccessHandler)
				 .permitAll()
		    .and().csrf()
		    .and().exceptionHandling().accessDeniedPage("/accessDenied");
		 http.sessionManagement().maximumSessions(1).expiredUrl("/login");
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/static/**");
	}
}
