package io.security.basicsecurity;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration//설정클래스이기 때문에
@EnableWebSecurity//이걸 해야, 여러 설정 클래스들을 import 한다
public class SecurityConfig extends WebSecurityConfigurerAdapter {//파일 찾기 : Ctrl+Shift+n
                                    //여러가지 설정을 해줌
    //OVERRIDE 메서드 고르는법 : CTRL + O


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //인가정책
        http
                .authorizeRequests()
                .anyRequest().authenticated();//어떠한 요청도 다 받는다.
        //인증정책
        http
                .formLogin()//폼로그인 방식으로 인증을 받도록 하용
                ;

    }
}
