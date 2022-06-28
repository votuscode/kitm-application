package com.kitm.application.backend.admin.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kitm.application.api.restaurant.dto.RestaurantDto;
import com.kitm.application.api.restaurant.dto.UpsertRestaurantDto;
import com.kitm.application.backend.admin.common.interfaces.ISubmitFormDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SubmitRestaurantFormDto extends UpsertRestaurantDto implements ISubmitFormDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("action")
    private Action action;

    private SubmitRestaurantFormDto(final String name, final String description, final String image, final UUID id) {
        super(name, description, image);
        this.id = id;
    }

    public static SubmitRestaurantFormDto create() {
        return new SubmitRestaurantFormDto();
    }

    public static SubmitRestaurantFormDto update(final RestaurantDto restaurantDto) {
        return new SubmitRestaurantFormDto(
                restaurantDto.getName(),
                restaurantDto.getDescription(),
                restaurantDto.getImage(),
                restaurantDto.getId()
        );
    }
}
