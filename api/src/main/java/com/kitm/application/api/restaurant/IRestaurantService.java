package com.kitm.application.api.restaurant;

import com.kitm.application.api.common.ICrudService;
import com.kitm.application.api.restaurant.dto.RestaurantDto;
import com.kitm.application.api.restaurant.dto.UpsertRestaurantDto;

public interface IRestaurantService extends ICrudService<RestaurantDto, UpsertRestaurantDto> {
}
