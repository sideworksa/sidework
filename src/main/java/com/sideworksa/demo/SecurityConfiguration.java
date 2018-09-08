package com.sideworksa.demo;

import com.sideworksa.demo.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsLoader userDetailsLoader;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard") // class notes - user's home page, it can be any URL. Cannot configure 2 different URLs here
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/listings",  // anyone can see the job listing pages
                        "/",
                        "/users",
                        "/workers",
                        "/aboutus",
                        "/login",
                        "/businesses",
                        "/index") // anyone can see the homepage, login & register pages, and view all users without being signed in
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/users/password",
                        "/businesses/{id}/edit",
                        "/listings/create",  // only authenticated users can create ads
//                        "/listings/edit", // only authenticated users can create ads
                        "/workers/{id}/edit" // only authenticated users can edit their profile
                )
                .authenticated()
        ;
    }
}

