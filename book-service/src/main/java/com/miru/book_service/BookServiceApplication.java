package com.miru.book_service;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;
import com.miru.book_service.clients.RatingClient;
import com.miru.model.RatingDTO;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableSwagger2
@EnableFeignClients
@RequestMapping("/books")
public class BookServiceApplication {
	
	@Autowired
	private RatingClient ratingClient;
	
    public static void main(String[] args) {
    	new SpringApplication(BookServiceApplication.class).run(args);
    }
 
    @Bean
    public Docket developersValoraFutbolApi() {

        ApiInfo apiInfo = new ApiInfoBuilder().title("Books API")
                .description("Books API Developers API reference for Developers").version("1.0")
                .build();

        @SuppressWarnings("unchecked")
        Predicate<String> paths = or(regex("/books.*"));

        List<springfox.documentation.service.Parameter> globalParameters = Arrays.asList();

        return new Docket(DocumentationType.SWAGGER_2).groupName("books").apiInfo(apiInfo).select()
                .paths(paths).build().globalOperationParameters(globalParameters);
    }
    
    private List<Book> bookList = Arrays.asList(
        new Book(1L, "Baeldung goes to the market", "Tim Schimandle"),
        new Book(2L, "Baeldung goes to the park", "Slavisa")
    );
 
    @GetMapping("")
    public List<Book> findAllBooks() {
        return bookList;
    }
 
    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable Long bookId) {
        return bookList.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
    }
    
    @GetMapping("/rating/{bookId}")
    public List<RatingDTO> getBookRating(@PathVariable Long bookId) {

        return ratingClient.findRatingsByBookId(bookId);
    }
}
