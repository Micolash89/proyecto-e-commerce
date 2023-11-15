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
        
//          return http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth ->
//            {
//                auth.antMatchers(publicResources).permitAll(); // Rutas disponibles para cualquier usuario/visitante
//                auth.antMatchers("/admin/**").hasAuthority("ADMIN"); // Rutas protegidas - solo pueden acceder quienes tengan el rol "ADMIN"
//                auth.anyRequest().authenticated(); // Todas las demás rutas - disponible solo para usuarios logueados
//            })
//            .formLogin(auth ->
//            {
//                auth.loginPage("/login");
//                auth.usernameParameter("email");
//                auth.passwordParameter("password");
//                auth.permitAll();
//            })
//                      .logout(logout ->
//            {
//                logout.logoutSuccessUrl("/");
//                logout.logoutSuccessHandler((request, response, authentication) ->
//                {
//                    session.invalidate();
//                    response.sendRedirect("/");
//                });
//            })
//            .build();
//        
//        http.authorizeRequests()
//                .requestMatchers("/admin/*").hasAnyRole("ADMIN")
//                .requestMatchers("/user/**").hasAnyRole("CLIENTE") 
//                .requestMatchers("/css/", "/js/", "/img/*", "/**", "/index", "/").permitAll()
//                .and().formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/logincheck")
//                .usernameParameter("email")
//                .passwordParameter("clave")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .permitAll()
//                .and().csrf()
//                .disable();
//        return http.build();
//
//        http.securityMatcher("/**")
//            .authorizeHttpRequests((authz) -> authz
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//               // .requestMatchers("/user/**").hasRole("USER")
//                .anyRequest().authenticated()
//            );
//        return http.build();
    }
}
