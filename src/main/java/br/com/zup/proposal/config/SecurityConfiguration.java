package br.com.zup.proposal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(GET, "/api/v1/proposal/**").hasAuthority("SCOPE_proposal:read")
                .antMatchers(GET, "/api/v1/card/**").hasAuthority("SCOPE_proposal:read")
                .antMatchers(POST, "/api/v1/proposal/**").hasAuthority("SCOPE_proposal:write")
                .antMatchers(POST, "/api/v1/card/**").hasAuthority("SCOPE_proposal:write")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
