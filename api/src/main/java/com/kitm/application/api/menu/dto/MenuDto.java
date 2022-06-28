package com.kitm.application.api.menu.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class MenuDto {

    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
