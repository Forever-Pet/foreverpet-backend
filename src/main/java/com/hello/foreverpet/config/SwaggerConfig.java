package com.hello.foreverpet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .addOpenApiCustomizer(openApi -> openApi
//                        .info(new Info().title("ForeverPet Api")
//                                .description("ForeverPet Project API 입니다")
//                                .version("v1.0")
//                        )
//                )
//                .group("ForeverPet Group")
//                .displayName("Hello ForeverPet")
//                .build();
//    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("test")
                .description("description")
                .version("1.0.0")
                .build();
    }
}
