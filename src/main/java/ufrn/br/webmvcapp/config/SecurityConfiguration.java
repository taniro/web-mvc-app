package ufrn.br.webmvcapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        // Constrói um usuário padrão do Spring
        UserDetails administrador = User.builder()
                .username("admin")
                // Criptografa a senha em tempo real usando a injeção do encoder
                .password(encoder.encode("senha123"))
                .roles("ADMIN")
                .build();

        // Entrega o usuário construído para o contêiner gerenciar na memória RAM
        return new InMemoryUserDetailsManager(administrador);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/css/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .defaultSuccessUrl("/dashboard", true)
                                .permitAll()
                )
                .logout(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Fator de força (Work Factor) configurável. Por padrão usa-se 10 ou 12.
        return new BCryptPasswordEncoder();
    }
}
