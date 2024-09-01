package org.api.java.api_v2_2826502.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configurar(HttpSecurity http)
        throws Exception{
            http.authorizeRequests(
                    request -> {
                        request.requestMatchers(HttpMethod.GET, "/api/products").hasRole("CUSTOMER")
                                .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                                .anyRequest().authenticated();
                    }
            ).csrf( csrf -> csrf.disable())
                    .cors(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults());
            return http.build();
        }

    @Bean
    public UserDetailsService Usuarios(){

        //Crear usuario in-memory
        UserDetails admin = User.builder().username("admin").password(this.cifrador().encode("admin")).roles("ADMIN").build();
        UserDetails custom = User.builder().username("custom").password(this.cifrador().encode("custom")).roles("CUSTOMER").build();

        return new InMemoryUserDetailsManager(admin, custom);
    }
    @Bean
    public PasswordEncoder cifrador() {
        return new BCryptPasswordEncoder();
    }

}
