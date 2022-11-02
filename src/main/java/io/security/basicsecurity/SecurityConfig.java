package io.security.basicsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration//설정클래스이기 때문에
@EnableWebSecurity//이걸 해야, 여러 설정 클래스들을 import 한다
public class SecurityConfig extends WebSecurityConfigurerAdapter {//파일 찾기 : Ctrl+Shift+n
                                    //여러가지 설정을 해줌
    //OVERRIDE 메서드 고르는법 : CTRL + O

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //인가정책
        http
                .authorizeRequests()
                .anyRequest().authenticated();//어떠한 요청도 다 받는다.
        //인증정책
        http
                .formLogin()//폼로그인 방식으로 인증을 받도록 하용
//                .loginPage("/loginPage")//로그인 페이지 설정, 기본적으로는 시큐리티에서 제공을 함
//                .defaultSuccessUrl("/")//성공시 주소
//                .failureUrl("/login")//실패시 주소
//                .usernameParameter("userId")//파라미터 변경
//                .passwordParameter("1111")//비번 변경
//                .loginProcessingUrl("/login_proc")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        System.out.println("authentication : "+authentication.getName());
//                        response.sendRedirect("/");
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                        System.out.println("exception : "+exception.getMessage());
//                        response.sendRedirect("/login");
//                    }
//                })
//                .permitAll()//접근하는 모든 사용자들은 인증받지 않아도 모두 허용
        ;
        http.
                logout()
                .logoutUrl("/logout")//logout을 진행하는 주소 , 원칙적으로는 POST방식으로 진행한다
                .logoutSuccessUrl("/login")//로그아웃 성공시
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        HttpSession session = request.getSession();
                        session.invalidate();
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                })//successurl과 비슷하지만 기능이 더 많음
                .deleteCookies("JSESSIONID","remember-me")//삭제하고 싶은 쿠키 명
        ;
        http
                .rememberMe()
                .rememberMeParameter("remember")//기본 파라미터명은 remember-me
                .tokenValiditySeconds(3600)//Default는 14일
                .alwaysRemember(true)
                .userDetailsService(userDetailsService)
        ;
    }
}
