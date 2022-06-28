package com.kitm.application.backend.domain.menu;

import com.kitm.application.backend.admin.menu.dto.MenuDetailDto;
import com.kitm.application.backend.domain.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MenuDetailService {

    private final MenuRepository menuRepository;

    private final RestaurantService restaurantService;

    public Collection<MenuDetailDto> findAll() {

        return menuRepository.findAll().stream()
                .map(this::convert)
                .toList();
    }

    private MenuDetailDto convert(final MenuEntity menuEntity) {

        return MenuDetailDto.builder()
                .id(menuEntity.getId())
                .name(menuEntity.getName())
                .description(menuEntity.getDescription())
                .image(menuEntity.getImage())
                .restaurant(restaurantService.convert(menuEntity.getRestaurantEntity()))
                .build();
    }
}
