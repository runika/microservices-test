package com.miru.rating_service;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableSwagger2
@RequestMapping("/ratings")
public class RatingServiceApplication {
	
	@Bean
    public Docket developersValoraFutbolApi() {

        ApiInfo apiInfo = new ApiInfoBuilder().title("Ratings API")
                .description("Ratings API Developers API reference for Developers").version("1.0")
                .build();

        @SuppressWarnings("unchecked")
        Predicate<String> paths = or(regex("/ratings.*"));

        List<springfox.documentation.service.Parameter> globalParameters = Arrays.asList();

        return new Docket(DocumentationType.SWAGGER_2).groupName("ratings").apiInfo(apiInfo).select()
                .paths(paths).build().globalOperationParameters(globalParameters);
    }
	
	public static void main(String[] args) {

		new SpringApplication(RatingServiceApplication.class).run(args);
	}

	private List<Rating> ratingList = Arrays.asList(new Rating(1L, 1L, 2), new Rating(2L, 1L, 3), new Rating(3L, 2L, 4),
			new Rating(4L, 2L, 5));

	@GetMapping("")
	public List<Rating> findRatingsByBookId(@RequestParam Long bookId) {
		return bookId == null || bookId.equals(0L) ? Collections.EMPTY_LIST
				: ratingList.stream().filter(r -> r.getBookId().equals(bookId)).collect(Collectors.toList());
	}

	@GetMapping("/all")
	public List<Rating> findAllRatings() {
		return ratingList;
	}
}