package io.security.basicsecurity;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration//설정클래스이기 때문에
@EnableWebSecurity//이걸 해야, 여러 설정 클래스들을 import 한다
@Order(0)
public class SecurityConfig extends WebSecurityConfigurerAdapter {//파일 찾기 : Ctrl+Shift+n
                                    //여러가지 설정을 해줌
    //OVERRIDE 메서드 고르는법 : CTRL + O


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //인가정책
        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .anyRequest().authenticated();//어떠한 요청도 다 받는다.
        //인증정책
        http
                .httpBasic();
    }
}

@Configuration
@Order(1)
class SecurityConfig2 extends  WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated();
        http.formLogin();

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);

    }
}
