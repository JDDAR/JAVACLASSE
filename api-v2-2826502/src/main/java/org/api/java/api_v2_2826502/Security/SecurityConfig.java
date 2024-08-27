package org.api.java.api_v2_2826502.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;


@Component
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configurar(HttpSecurity http)
    throws Exception{
            http.authorizeRequests(
                    request -> {
                        request.anyRequest().authenticated();
                    }
            ).csrf( csrf -> csrf.disable()).httpBasic(Customizer.withDefaults());
            return http.build();
        }

}
