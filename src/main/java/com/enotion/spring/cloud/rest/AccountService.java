package com.enotion.spring.cloud.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Value("${account.greeting:NOT SET}")
    String greeting;

    @RequestMapping("zuul/hello")
    public String zuulHello() {

        logger.info("[sc-account][zuul/hello] account.greeting = " + greeting);

        return "[sc-account][zuul/hello] Hello! account.greeting = " + greeting;
    }

    @RequestMapping("feign/hello")
    @HystrixCommand(fallbackMethod = "homeFallback")
    public String feignHello() {

        logger.info("[sc-account][feign/hello] account.greeting = " + greeting);

        if ("bye".equals(greeting)) {
            throw new RuntimeException("Good bye!");
        }

        return "[sc-account][feign/hello] Hello! account.greeting = " + greeting;
    }

    String homeFallback() {
        return "[feign/hello] AccountController.hello() Fallback !";
    }
}
