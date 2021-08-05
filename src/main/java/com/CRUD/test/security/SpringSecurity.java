package com.CRUD.test.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    private final JwtTokenProdvider jwtTokenProdvider;
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .cors().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .and()
                .apply(new JwtTokenFilterConfiguer(jwtTokenProdvider));
    }

    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("swagger-ui/**")
                .antMatchers("swagger-ui.html")
                .antMatchers("/**api-docs")
                .antMatchers("/api/**")
                .antMatchers("/h2-console/**");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}

