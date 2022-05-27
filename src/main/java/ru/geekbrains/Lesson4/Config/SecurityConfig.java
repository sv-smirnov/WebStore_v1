package ru.geekbrains.Lesson4.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/products/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                .antMatchers("/customers/**").hasRole("ADMIN")
                .and()
                .formLogin()
//                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/products");

    }

}
