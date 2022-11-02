package io.security.basicsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                //1-8) 내용


        //인증정책
        http
                .formLogin()//폼로그인 방식으로 인증을 받도록 하용
        ;


        /**
        http
                .sessionManagement()
                .maximumSessions(1)//최대 세션갯수
                .maxSessionsPreventsLogin(false)//default=false , true는 동시사용자의 로그인을 막는 전략
        ;       //false : 2번쨰 로그인자는 Maximum sessions of 1 for this principal exceeded 메시지가 뜸
                //true : 1번쨰 로그인자는 This session has been expired (possibly due to multiple concurrent logins being attempted as the same user). 메시지가 뜸
         * */

        /**세션고정공격*/
        http
                .sessionManagement()
                //.sessionFixation().none();//none은 공격에 노출됨
                .sessionFixation().changeSessionId()//세션아이디를 계속 바꿔줌
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)//스프링 시큐리티가 항상 세션생성
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//스프링 시큐리티가 필요 시 생성(기본값)
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)//스프링 시큐리티가 생성하지 않지만 이미 존재하면 사용
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//스프링 시큐리티가 생성하지 않고 존재해도 사용하지 않음

        ;
    }
}
