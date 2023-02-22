package com.example.projectone.security;

import com.example.projectone.auth.ApplicationUserDetailsService;
import com.example.projectone.jwt.JwtTokenVerifier;
import com.example.projectone.jwt.JwtUsernameAndPasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.projectone.security.ApplicationUserRole.*;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
    public SpringSecurityConfig(PasswordEncoder passwordEncoder,
                                ApplicationUserDetailsService applicationUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()))
                    .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("index","/api/v1/students/hello").permitAll()
                .antMatchers("/api/v1/students/**")
                    .hasAnyRole(ADMIN.name(), MODERATOR.name(), STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/management/api/**")
                    .hasRole(ADMIN.name())
                .antMatchers(HttpMethod.POST, "/management/api/**")
                    .hasAnyRole(ADMIN.name(), MODERATOR.name())
                .antMatchers(HttpMethod.PUT, "/management/api/**")
                    .hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET, "/management/api/**")
                    .hasAnyRole(ADMIN.name(), MODERATOR.name(), STUDENT.name())
                .anyRequest()
                .authenticated();

//                .antMatchers("/management/api/v1/students/**").authenticated()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserDetailsService);
        return daoAuthenticationProvider;
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails andrewUser = User.builder()
//                .username("andrew")
//                .password(passwordEncoder.encode("1111"))
////                .roles(STUDENT.name())
//                .authorities(STUDENT.getSimpleGrantedAuthority())
//                .build();
//
//        UserDetails artemUser = User.builder()
//                .username("artem")
//                .password(passwordEncoder.encode("1111"))
////                .roles(ADMIN.name())
//                .authorities(ADMIN.getSimpleGrantedAuthority())
//                .build();
//
//        UserDetails vladUser = User.builder()
//                .username("vlad")
//                .password(passwordEncoder.encode("1111"))
////                .roles(MODERATOR.name())
//                .authorities(MODERATOR.getSimpleGrantedAuthority())
//                .build();
//
//        return new InMemoryUserDetailsManager(andrewUser, artemUser, vladUser);
//    }
}
