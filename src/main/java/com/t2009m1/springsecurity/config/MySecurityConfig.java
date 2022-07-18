package com.t2009m1.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests().antMatchers("/api/v1/hello").permitAll();
//        httpSecurity.authorizeHttpRequests().antMatchers("/api/v1/user").hasAnyAuthority("user");
//        httpSecurity.authorizeHttpRequests().antMatchers("/api/v1/admin").hasAnyAuthority("admin");
//    }
@Override
protected void configure(HttpSecurity http) throws Exception{
    http.cors().and().csrf().disable();
    http.authorizeHttpRequests().antMatchers("/api/v1/hello", "api/v1/accounts/*").permitAll();
    http.authorizeHttpRequests().antMatchers("/api/v1/user").hasAnyAuthority("USER","ADMIN");
    http.authorizeHttpRequests().antMatchers("/api/v1/admin").hasAnyAuthority("ADMIN");
    http.addFilterBefore(new MyAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
}
}
