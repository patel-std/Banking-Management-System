package patel.s.banking.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import patel.s.banking.Filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    public SecurityConfig(JwtFilter jwtFilter,UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
        this.jwtFilter=jwtFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
     httpSecurity.authorizeHttpRequests(request -> request
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/cashier/**").hasAuthority("cashier")
            .requestMatchers("/manager/**").hasAuthority("manager")
            .requestMatchers("/clerk/**").hasAuthority("clerk")
            .requestMatchers("/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**").permitAll()
            .anyRequest().authenticated())
             .csrf(AbstractHttpConfigurer::disable)
             .authenticationProvider(authenticationProvider())
             .addFilterBefore(
                     jwtFilter,
                     UsernamePasswordAuthenticationFilter.class
             )
             .sessionManagement(session ->
                     session.sessionCreationPolicy(
                             SessionCreationPolicy.STATELESS
                     )
             );
            return httpSecurity.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
