package com.kitm.application.api.restaurant.dto;

import com.kitm.application.api.common.IGenericDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class RestaurantDto implements IGenericDto {

    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String image;

    @NotNull
    private Integer menus;
}
