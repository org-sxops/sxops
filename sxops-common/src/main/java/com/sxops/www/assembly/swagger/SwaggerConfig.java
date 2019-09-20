package com.sxops.www.assembly.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 * Created by camelot on 2017/10/17.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.profiles.active: }")
    private String profile;

    @Value("${service.name: }")
    private String serviceName;

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sxops.www"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }



    /**
     * 封装API接口信息
     * @return 接口信息。ApiInfo对象
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("临汾同城生活圈API-"+profile+"环境: "+serviceName)
                .contact(new Contact("gewei","swagger-bootstrap-ui","geweiHome@163.com"))
                .version("1.0")
                .build();
        return apiInfo;
    }
}
