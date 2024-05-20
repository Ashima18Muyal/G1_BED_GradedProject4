package com.greatlearning.emr.security.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;    

import com.greatlearning.emr.security.service.impl.EmrUserDetailsServiceImpl;

@Configuration
public class EmrSecurityConfiguration {

  @Bean
  public UserDetailsService userDetailsService() {
    return new EmrUserDetailsServiceImpl();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider EmrDaoAuthenticationProvider() {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .requestMatchers("/","/employees/list", "/employees/displayemployeeForm", "/employees/save").hasAnyAuthority("NORMAL_USER","ADMIN_USER")
      .requestMatchers("/students/displayEmployeeForm_Update","/students/delete").hasAuthority("ADMIN_USER")
      .anyRequest().authenticated()
      .and()
      .formLogin().loginProcessingUrl("/login").successForwardUrl("/employees/list").permitAll()
      .and()
      .logout().logoutSuccessUrl("/login").permitAll()
      .and()
      .exceptionHandling().accessDeniedPage("/employees/403")
      .and()
      .cors().and().csrf().disable();
    
    http.authenticationProvider(EmrDaoAuthenticationProvider());
    return http.build();
  }    
}  

