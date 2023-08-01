package com.hello.foreverpet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    /*
        RestTemplate란?

        간단하게 백단에서 비동기로 api 호출을 가능하게 해주는 템플릿이다.
    */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
