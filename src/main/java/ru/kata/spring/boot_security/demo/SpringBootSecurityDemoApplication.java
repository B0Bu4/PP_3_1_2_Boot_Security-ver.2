package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

class FirstStartAddAdmin {

    @Autowired
    UserServiceImp userService;
    public void firstStartAddAdmin() {
        userService.addAdmin();
    }
}
@SpringBootApplication
public class SpringBootSecurityDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);

    }

    @Bean(initMethod = "firstStartAddAdmin")
    public FirstStartAddAdmin getInitBean(){
        return new FirstStartAddAdmin();
    }

}


