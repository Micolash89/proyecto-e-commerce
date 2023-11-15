package com.caballerosGuardiaReal.ecommerce;

import com.caballerosGuardiaReal.ecommerce.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadWeb  {

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
            return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth ->
            {
                auth.requestMatchers("/css/", "/js/", "/img/*", "/**", "/index", "/inicio").permitAll();
                auth.requestMatchers("/admin/**").hasAuthority("ADMIN");
                auth.anyRequest().permitAll();
            })
            .formLogin(form ->
            {
                form.loginPage("/login");
                form.loginProcessingUrl("/logincheck");
                form.usernameParameter("email");
                form.passwordParameter("clave");
                form.defaultSuccessUrl("/");
                form.permitAll();
            })
            .logout(logout ->
            {
                logout.logoutUrl("/logout");
                logout.logoutSuccessUrl("/");
                logout.permitAll();
            })
            .build();
    }
}
