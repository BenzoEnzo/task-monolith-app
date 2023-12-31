package pl.benzo.enzo.server.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import pl.benzo.enzo.server.security.FilterBeforeRequest;
import pl.benzo.enzo.server.util.enumeration.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String API = "/api/user/**";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String V_3 = "/v3/**";
    private final FilterBeforeRequest filterBeforeRequest;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.addFilterBefore(filterBeforeRequest, UsernamePasswordAuthenticationFilter.class).csrf(csrfConfigurer ->
                csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern(API))
                        );

        http.authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(mvcMatcherBuilder.pattern(API)).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern(SWAGGER_UI)).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern(V_3)).permitAll()
                                .anyRequest().hasAuthority("USER")
                )
                .logout((logout) -> logout
                        .logoutUrl("/api/user/logout")
                        .clearAuthentication(true)
                        .permitAll());

        return http.getOrBuild();
    }

}
