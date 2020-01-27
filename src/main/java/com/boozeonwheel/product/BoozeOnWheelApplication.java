package com.boozeonwheel.product;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class BoozeOnWheelApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		SpringApplication.run(BoozeOnWheelApplication.class, args);
	}

	@Bean
	public Docket swaggerProductCategoryApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Product-Category-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.category"))
				.paths(regex("/api/productcategory/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Product Category API")
						.description("Documentation BoozeOnWheel Product Category API v1.0").build());
	}

	
	@Bean
	public Docket swaggerFileServiceApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("S3-File-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.file"))
				.paths(regex("/api/fileservice/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel File services for S3 bucket API")
						.description("Documentation BoozeOnWheel File Services For S3 Bucket API v1.0").build());
	}
	
	
	
	@Bean
	public Docket swaggerBannerApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Banner-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.banner"))
				.paths(regex("/api/v1/taxonomies.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Banner services API")
						.description("Documentation BoozeOnWheel Banner Services API v1.0").build());
	}
	
	@Bean
	public Docket swaggerProductMasterApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Product-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.master"))
				.paths(regex("/api/v1/taxons/products.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Product Master services API")
						.description("Documentation BoozeOnWheel Product Master Services API v1.0").build());
	}
	
	@Bean
	public Docket swaggerMasterApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Product-Master-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.master"))
				.paths(regex("/api/v1/master.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Master services API")
						.description("Documentation BoozeOnWheel Master Services API v1.0").build());
	}
	
	@Bean
	public Docket swaggerUrlTypeApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("URLType-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.urltype"))
				.paths(regex("/api/v1/urltype.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel URL Type services API")
						.description("Documentation BoozeOnWheel URL Type Services API v1.0").build());
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

}
