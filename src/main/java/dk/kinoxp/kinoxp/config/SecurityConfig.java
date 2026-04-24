package dk.kinoxp.kinoxp.config;

import dk.kinoxp.kinoxp.service.AdminDetailsService;
import dk.kinoxp.kinoxp.service.CustomerDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final AdminDetailsService adminDetailsService;
    private final CustomerDetailsService customerDetailsService;

    public SecurityConfig(AdminDetailsService adminDetailsService,
                          CustomerDetailsService customerDetailsService) {
        this.adminDetailsService = adminDetailsService;
        this.customerDetailsService = customerDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider adminProvider = new DaoAuthenticationProvider();
        adminProvider.setUserDetailsService(adminDetailsService);
        adminProvider.setPasswordEncoder(passwordEncoder());

        DaoAuthenticationProvider customerProvider = new DaoAuthenticationProvider();
        customerProvider.setUserDetailsService(customerDetailsService);
        customerProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(List.of(adminProvider, customerProvider));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationManager(authenticationManager())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/booking.html",
                                "/login.html", "/confirm.html", "/register.html").permitAll()
                        .requestMatchers("/**.css", "/**.js").permitAll()
                        .requestMatchers("/api/screenings/**").permitAll()
                        .requestMatchers("/api/seats/**").permitAll()
                        .requestMatchers("/api/customers/register").permitAll()
                        .requestMatchers("/api/reservations/**").authenticated()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/films/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .loginPage("/login.html")
                        .successHandler((request, response, authentication) -> {
                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                            if (isAdmin) {
                                response.sendRedirect("/admin.html");
                            } else {
                                response.sendRedirect("/");
                            }
                        })
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login.html")
                );
        return http.build();
    }
}