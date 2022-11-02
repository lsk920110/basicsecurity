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
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
        ;
    }
}
