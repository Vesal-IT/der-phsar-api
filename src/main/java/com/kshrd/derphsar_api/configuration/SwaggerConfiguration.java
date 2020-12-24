package com.kshrd.derphsar_api.configuration;


import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo())
                .enableUrlTemplating(false)
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()));
    }
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DerPhsar Mobile Project API")
                .description("Korea Software HRD Center \n React Native Team \uD83D\uDC95")
                .termsOfServiceUrl("https://spring.io/")
                .license("License of API")
                .licenseUrl("Email")
                .version("HRD 8th Generation")
                .contact(new Contact("Copyright by DerPhsar team", "https://spring.io/", "derphsar@gmail.com"))
                .build();
    }

    @Bean
    SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }
    List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT",authorizationScopes));
    }
    private ApiKey apiKey(){
        return new ApiKey("JWT","Authorization","header");
    }
}
