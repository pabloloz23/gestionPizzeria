// File: pizza-API/src/main/java/com/pizza/api/config/security/VaadinSecurityConfig.java
        package com.pizza.api.config.security;

        import com.pizza.views.LoginView;
        import com.vaadin.flow.spring.security.VaadinWebSecurity;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.builders.WebSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

        @Configuration
        @EnableWebSecurity
        public class VaadinSecurityConfig extends VaadinWebSecurity {

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http
                    // Permitimos las rutas públicas
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/register")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                            .anyRequest().authenticated()
                    )
                    .csrf(csrf -> csrf.disable()); // Desactivar CSRF si no es requerido

                // Configura la vista de login
                setLoginView(http, LoginView.class);
            }

            @Override
            public void configure(WebSecurity web) throws Exception {
                super.configure(web);
                // Ignoramos los recursos estáticos
                web.ignoring().requestMatchers(
                        new AntPathRequestMatcher("/images/**"),
                        new AntPathRequestMatcher("/icons/**"),
                        new AntPathRequestMatcher("/styles/**")
                );
            }
        }