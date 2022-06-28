package com.kitm.application.api.restaurant;

import com.kitm.application.api.restaurant.dto.RestaurantDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@Api(value = "Restaurant")
@RestController
@RequestMapping(path = "/api/restaurants")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantService restaurantService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<RestaurantDto> getRestaurants() {

        return restaurantService.findAll().stream().toList();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantDto getRestaurant(@PathVariable("id") UUID id) {

        return restaurantService.getOne(id);
    }
}
