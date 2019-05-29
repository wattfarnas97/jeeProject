package gestion.compte.securite;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecuriteConfig  extends WebSecurityConfigurerAdapter {
@Autowired
    private DataSource datasource;
    @Override
protected  void  configure(AuthenticationManagerBuilder auth) throws Exception {
/*
        auth.inMemoryAuthentication().withUser("admin").password("{noop}12345").roles("ADMIN","USER");
        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
*/
auth.jdbcAuthentication().dataSource(datasource)
        .usersByUsernameQuery("select username as principal ,password as credentials,active from users where username=?")
        .authoritiesByUsernameQuery("select username as principal,roles as role from uses_roles where username=?")
.rolePrefix("ROLE_")
.passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.formLogin().loginPage("/login");
      http.authorizeRequests().antMatchers("/comptes","/detailCompte").hasRole("USER");
        http.authorizeRequests().antMatchers("/operation","/saveoperation").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
