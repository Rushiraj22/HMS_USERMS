package com.hms.user.user.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

        }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests((requests) ->
//             requests.requestMatchers("/**").permitAll().anyRequest().authenticated()
//            );
//
//        http.csrf(csrf -> csrf.disable());
//
//        return http.build();
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(request -> "SECRET".equals(request.getHeader("x-Secret-Key"))).permitAll()
                            .requestMatchers("/**").permitAll()
                 .anyRequest().denyAll()
//                            .requestMatchers("/user/register", "/user/login").permitAll() // ✅ Allow public endpoints
//                            .anyRequest().authenticated()
            );
        // Enable basic authentication

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
