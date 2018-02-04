package microservice.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableZuulProxy
@EnableEurekaClient
public class GreetingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GreetingServiceApplication.class, args);
    }
    //produces = "application/vnd.company.app-v1+json" versioning
    @GetMapping("greeting/{name}")
    public String greeting(@PathVariable("name") String name){
        return "Hello " + name + "!";
    }
}
