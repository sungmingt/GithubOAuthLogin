package oauth.GithubOAuthLogin.config;

import lombok.RequiredArgsConstructor;
import oauth.GithubOAuthLogin.service.OAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthService oAuthService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //h2 console 접속을 위해
                .headers().frameOptions().disable() //h2 console 접속을 위해

                .and()
                .authorizeRequests()
                .antMatchers("/home").authenticated()
                .antMatchers("/h2/**").permitAll()

                .and()
                .oauth2Login()
                .defaultSuccessUrl("/home")
                .userInfoEndpoint()
                .userService(oAuthService);

        return http.build();
    }
}
