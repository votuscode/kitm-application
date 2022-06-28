package com.kitm.application.backend.admin.menu.dto;

import com.kitm.application.api.restaurant.dto.RestaurantDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class MenuDetailDto {
    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String image;

    @NotNull
    private RestaurantDto restaurant;
}
