package com.hermes.web.config;

import com.hermes.web.config.trivia.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", customUserDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .eraseCredentials(true)
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/", "/favicon.ico", "/resources/**", "/signup").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/signin")
//                .permitAll()
//                .failureUrl("/signin?error=1")
//                .loginProcessingUrl("/authenticate")
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .permitAll()
//                .logoutSuccessUrl("/signin?logout")
//                .and()
//            .rememberMe()
//                .rememberMeServices(rememberMeServices())
//                .key("remember-me-key");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().
        antMatchers("/secure/**")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/signup")
                .and()
                .logout()
                .logoutUrl("/appLogout")
                .logoutSuccessUrl("/signin");
    }


    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Aver").password("123").roles("USER");
    }
}