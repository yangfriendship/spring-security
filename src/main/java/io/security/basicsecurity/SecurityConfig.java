package io.security.basicsecurity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*인가 : 요청에 대한 보안검사 설정 */
        http
            .authorizeRequests()
            .anyRequest().authenticated();

        /*인증 : 인증방식 설정*/
        http
            .formLogin()
//            .loginPage("/loginPage")
            .defaultSuccessUrl("/")
            .failureUrl("/loginPage")
            .usernameParameter("userId")
            .passwordParameter("passwd")
            .successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                    System.out.println("authentication Success:" + authentication.getName());
                    httpServletResponse.sendRedirect("/");
                }
            })
            .failureHandler(new AuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, AuthenticationException e)
                    throws IOException, ServletException {
                    System.out.println("authentication Failure");
                    httpServletResponse.sendRedirect("/loginPage");
                }
            })
            .permitAll()
        ;

        /* 로그아웃 설정*/
        http
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .addLogoutHandler(new LogoutHandler() {
                @Override
                public void logout(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, Authentication authentication) {
                    httpServletRequest.getSession().invalidate();
                    System.out.println("logoutHandler");
                }
            })
            .logoutSuccessHandler(new LogoutSuccessHandler() {
                @Override
                public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                    httpServletResponse.sendRedirect("/");
                    System.out.println("logoutSuccessHandler");
                }
            })
            .deleteCookies("remember-me")
        ;


        http
            .rememberMe()
            .rememberMeParameter("remember")
            .tokenValiditySeconds(3600) // 쿠키의 수명 기본은 14일
//            .alwaysRemember(true)   // 강제로 설정할 수도 있다.
            .userDetailsService(userDetailsService())
        ;
    }
}
