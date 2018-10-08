package com.miru.book_service.clients;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miru.model.RatingDTO;

@FeignClient(name = "rating-service")
public interface RatingClient {

    @RequestMapping("/ratings")
    public List<RatingDTO> findRatingsByBookId(@RequestParam("bookId") Long bookId);
}