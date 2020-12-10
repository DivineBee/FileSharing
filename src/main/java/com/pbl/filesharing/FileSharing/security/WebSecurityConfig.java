package com.pbl.filesharing.FileSharing.security;

import com.pbl.filesharing.FileSharing.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Beatrice V.
 * @created 24.11.2020 - 19:10
 * @project FileSharing
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(getCustomFilter(), BeforeAuthenticationFilter.class)
                .formLogin()
                    .usernameParameter("email")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/").permitAll();
    }

    private BeforeAuthenticationFilter getCustomFilter() throws Exception {
        BeforeAuthenticationFilter filter = new BeforeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(){
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                System.out.println("onAuthenticationFailure");
                super.setDefaultFailureUrl("/login?error");
                super.onAuthenticationFailure(request, response, exception);
            }
        });
        return filter;
    }

}
