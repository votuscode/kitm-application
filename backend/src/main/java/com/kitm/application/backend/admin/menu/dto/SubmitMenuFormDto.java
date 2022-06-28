package com.kitm.application.backend.admin.menu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kitm.application.api.menu.dto.MenuDto;
import com.kitm.application.api.menu.dto.UpsertMenuDto;
import com.kitm.application.backend.admin.common.interfaces.ISubmitFormDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SubmitMenuFormDto extends UpsertMenuDto implements ISubmitFormDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("action")
    private Action action;

    private SubmitMenuFormDto(final String name, final String description, final UUID id) {
        super(name, description);
        this.id = id;
    }

    public static SubmitMenuFormDto create() {
        return new SubmitMenuFormDto();
    }

    public static SubmitMenuFormDto update(final MenuDto menuDto) {
        return new SubmitMenuFormDto(menuDto.getName(), menuDto.getDescription(), menuDto.getId());
    }
}
