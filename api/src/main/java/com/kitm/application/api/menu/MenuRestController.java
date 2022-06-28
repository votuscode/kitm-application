package com.kitm.application.api.menu;

import com.kitm.application.api.menu.dto.MenuDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@Api(value = "Menu")
@RestController
@RequestMapping(path = "/api/menus")
@RequiredArgsConstructor
public class MenuRestController {
    private final IMenuService menuService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MenuDto> getMenus() {

        return menuService.findAll().stream().toList();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuDto getMenu(@PathVariable("id") UUID id) {

        return menuService.getOne(id);
    }
}
