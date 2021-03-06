package com.person.profile.config;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).host("localhost:8080").pathMapping("/").select()
				.apis(RequestHandlerSelectors.basePackage("com.person.profile.controller"))
				.paths(Predicates.not(PathSelectors.regex("/error"))).build().apiInfo(apiInfo())
				.produces(new HashSet<>(Collections.singletonList("application/json")))
				.consumes(new HashSet<>(Collections.singletonList("application/json")))
				.securitySchemes(Collections.singletonList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()));
	}

	/**
	 * 
	 * Build api key.
	 * 
	 * @return the api key
	 */
	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	/**
	 * 
	 * Build the security context.
	 * 
	 * @return the security context
	 */
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(Collections.singletonList(
				SecurityReference.builder().reference("Authorization").scopes(new AuthorizationScope[0]).build()))
				.build();
	}

	/**
	 * Api Info.
	 * 
	 * @return the API info
	 */
	private ApiInfo apiInfo() {
		String description = "Person Profile app Swagger Testing !!! ";

		return new ApiInfoBuilder().title("Person Profile App").description(description).termsOfServiceUrl("gitlab").licenseUrl("")
				.version("1.0").build();
	}

}
