package com.springboot.springsecurity.demo.websecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

     @Override
     // This will let us configure Authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Setting my configuration on the auth object
         auth.inMemoryAuthentication()
                 .withUser("blah")
                 .password("blah")
                 .roles("USER")
                 .and()
                 .withUser("foo")
                 .password("foo")
                 .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests()
                 .antMatchers("/admin").hasRole("ADMIN")
                 .antMatchers("/user").hasAnyRole("USER","ADMIN")
                 .antMatchers("/").permitAll()
                 .and().formLogin();
    }
}
