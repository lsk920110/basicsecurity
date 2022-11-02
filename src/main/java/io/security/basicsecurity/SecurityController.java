package io.security.basicsecurity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String main(){

        return "home";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

}
