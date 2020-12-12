package ee.taltech.cars.security;

import ee.taltech.cars.security.JwtRequestFilter;
import ee.taltech.cars.security.MyUserDetailsService;
import ee.taltech.cars.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration // this marks this class as configuration, so spring knows to look for config
@EnableWebSecurity//(debug = true) // enables the whole thing, turn on debug to see filters applied to your requests
@EnableGlobalMethodSecurity(securedEnabled = true) //this makes spring use @Secured
public class SecurityConfig extends WebSecurityConfigurerAdapter { //WebSecurityConfigurerAdapter is the main spring security class you implement

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().httpStrictTransportSecurity().disable()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**").permitAll()
                .antMatchers("/listings/**").permitAll()
                .antMatchers("/user", "/user/**").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
        ;
    }

    /**
     * password encoder, bcrypt is one of the algorithms
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * authentication manager is used as entrance to creating authentication
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
