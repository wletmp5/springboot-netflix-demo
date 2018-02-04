package microservices.composite;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableHystrix
@EnableZuulProxy
public class GreetingServiceClientApplication {

    GreetingServiceClient greetingServiceClient;

    public static void main(String[] args) {
        SpringApplication.run(GreetingServiceClientApplication.class, args);
    }

    @Autowired
    public GreetingServiceClientApplication(GreetingServiceClient greetingServiceClient){
        this.greetingServiceClient = greetingServiceClient;
    }

    @GetMapping("/greeting/{name}")
    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String greeting(@PathVariable("name") String name) {
        return this.greetingServiceClient.greeting(name);
    }

    public String defaultGreeting(String name){
        return "default greeting";
    }
}

@FeignClient(value = "greeting-service")
interface GreetingServiceClient{
    @GetMapping("/greeting/{name}")
    String greeting(@PathVariable("name") String name);
}

