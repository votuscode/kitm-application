package com.kitm.application.api.menu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpsertMenuDto {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("description")
    private String description;
}
