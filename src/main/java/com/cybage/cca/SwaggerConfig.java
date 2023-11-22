package com.cybage.cca;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
				.basePackage("com.cybage.controller"))
				.paths(PathSelectors.any())
				.build()
		        .apiInfo(apiInfo());
                
	}
	
	private List<Response> customResponses() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code("200")
                        .description("OK")
                        .build(),
                new ResponseBuilder()
                        .code("400")
                        .description("Bad Request")
                        .build(),
                new ResponseBuilder()
                        .code("401")
                        .description("Unauthorized")
                        .build(),
                new ResponseBuilder()
                        .code("403")
                        .description("Forbidden")
                        .build(),
                new ResponseBuilder()
                        .code("404")
                        .description("Not Found")
                        .build(),
                new ResponseBuilder()
                        .code("500")
                        .description("Internal Server Error")
                        .build());
    }

        private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Conference Club API")
                .description("API documentation for Conference Club App")
                .version("1.0")
                .build();
    }}
	 
	 
	
	
	


