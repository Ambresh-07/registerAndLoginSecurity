package com.Tech.Register_Login_Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    //    for filtering authorizing apis
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/","/register","/signin","/saveuser").permitAll()
                                .requestMatchers("/user/**")// Allow public access to these paths
                                .authenticated() // All other paths require authentication
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/signin") // Custom login page
                                .loginProcessingUrl("/loginuser") // URL to process login
                                .defaultSuccessUrl("/user/profile", true) // Redirect after successful login

                                .permitAll() // Allow everyone to access the login page
                )

                .csrf(csrf -> csrf.disable()); // Optional: Disable CSRF for simplicity (use with caution)

        return http.build();

    }


}
