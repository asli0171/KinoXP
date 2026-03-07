package dk.kinoxp.kinoxp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/booking.html", "/confirm.html").permitAll()
                        .requestMatchers("/admin.html").authenticated()
                        .requestMatchers("/**.css", "/**.js").permitAll()
                        .requestMatchers("/api/screenings/**").permitAll()
                        .requestMatchers("/api/seats/**").permitAll()
                        .requestMatchers("/api/reservations/**").permitAll()
                        .requestMatchers("/api/admin/**").authenticated()
                        .requestMatchers("/api/films/**").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});
        return http.build();
    }
}