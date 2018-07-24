package com.john.rod.booting.config;


import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.regex.Pattern;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title(" 用户模块 ").build())
                .groupName("1.user")
                .select()
                .apis(matchPattern("/user*"))
                .build();
    }

    @Bean
    public Docket createRestApi1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title(" 部门模块 ").build())
                .groupName("2.dept")
                .select()
                .apis(matchPattern("/dept*"))
                .build();
    }


    private Predicate<RequestHandler> matchPattern(final String pattern) {
        return new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                return match(input.getPatternsCondition().getPatterns(), pattern);
            }
        };
    }


    private boolean match(Set<String> urls, String pattern) {
        Boolean flag = false;
        for (String url : urls) {
            if (Pattern.matches(pattern, url)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
