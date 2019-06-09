package com.boozeonwheel.product;

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
import static springfox.documentation.builders.PathSelectors.regex;

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
				.paths(regex("/api/productcategorytype/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Product Category API")
						.description("Documentation BoozeOnWheel Product Category API v1.0").build());
	}

	@Bean
	public Docket swaggerProductSubCategoryApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Product-Subcategory-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.subcategory"))
				.paths(regex("/api/productsubcategorytype/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Product Sub Category API")
						.description("Documentation BoozeOnWheel Product Sub Category API v1.0").build());
	}

	@Bean
	public Docket swaggerPriceDecisionFactorApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Price-Decision-Factor-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.pricedecision"))
				.paths(regex("/api/pricedecisionfactor/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Price Decision Factor API")
						.description("Documentation BoozeOnWheel Price Decision Factor API v1.0").build());
	}

	@Bean
	public Docket swaggerCurrencyDominanceApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Currency-Dominance-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.currencydominance"))
				.paths(regex("/api/currencydominance/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Currency Dominance Factor API")
						.description("Documentation BoozeOnWheel Currency Dominance Factor API v1.0").build());
	}
	
	@Bean
	public Docket swaggerSellerCategoryApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Seller-Category-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.sellercategory"))
				.paths(regex("/api/sellercategory/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Seller Category API")
						.description("Documentation BoozeOnWheel Seller Category API v1.0").build());
	}
	
	@Bean
	public Docket swaggerFileServiceApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("S3-File-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.s3servcie"))
				.paths(regex("/api/fileservice/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel File services for S3 bucket API")
						.description("Documentation BoozeOnWheel File Services For S3 Bucket API v1.0").build());
	}
	
	@Bean
	public Docket swaggerLiquorServiceApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Liquor-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.liquor"))
				.paths(regex("/api/liquor/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel Liquor services API")
						.description("Documentation BoozeOnWheel liquor Services API v1.0").build());
	}
	
	@Bean
	public Docket swaggerFileUploadApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("FileUpload-Service-API-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.boozeonwheel.product.controller.file"))
				.paths(regex("/api/fileupload/v1.0.*")).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("BoozeOnWheel File Upload services API")
						.description("Documentation BoozeOnWheel File Upload Services API v1.0").build());
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
