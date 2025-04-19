package com.pizza.api.config;

    import com.pizza.api.config.security.JwtAuthEntryPoint;
    import com.pizza.api.config.security.JwtAuthenticationFilter;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.core.annotation.Order;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @Order(1)
    @EnableWebSecurity
    public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final JwtAuthEntryPoint authEntryPoint;

        public SecurityConfig(
                JwtAuthenticationFilter jwtAuthFilter,
                JwtAuthEntryPoint authEntryPoint
        ) {
            this.jwtAuthFilter = jwtAuthFilter;
            this.authEntryPoint = authEntryPoint;
        }

        @Bean
        public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
            http
                    .securityMatcher("/api/**")
                    .csrf(AbstractHttpConfigurer::disable)
                    .exceptionHandling(handling -> handling
                            .authenticationEntryPoint(authEntryPoint)
                    )
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .authorizeHttpRequests(auth -> auth
                            // API endpoints públicos
                            .requestMatchers("/api/auth/**").permitAll()

                            // Endpoints protegidos
                            .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                            .requestMatchers("/api/pizzas/**").hasAnyAuthority("USER", "ADMIN")
                            .requestMatchers("/api/orders/**").hasAnyAuthority("USER", "ADMIN")
                            .requestMatchers("/api/payments/**").hasAnyAuthority("USER", "ADMIN")

                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
    }