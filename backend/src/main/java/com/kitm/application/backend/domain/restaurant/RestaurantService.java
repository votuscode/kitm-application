package com.kitm.application.backend.domain.restaurant;

import com.kitm.application.api.restaurant.IRestaurantService;
import com.kitm.application.api.restaurant.dto.RestaurantDto;
import com.kitm.application.api.restaurant.dto.UpsertRestaurantDto;
import com.kitm.application.backend.domain.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    @Override
    public Collection<RestaurantDto> findAll() {

        return restaurantRepository.findAll().stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public RestaurantDto getOne(final UUID id) {

        final RestaurantEntity restaurantEntity = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find restaurant"));

        return convert(restaurantEntity);
    }

    @Override
    public RestaurantDto createOne(final UpsertRestaurantDto upsertRestaurantDto) {

        final RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .name(upsertRestaurantDto.getName())
                .description(upsertRestaurantDto.getDescription())
                .image(upsertRestaurantDto.getImage())
                .build();

        return convert(
                restaurantRepository.save(restaurantEntity)
        );
    }

    @Override
    public RestaurantDto updateOne(final UUID id, final UpsertRestaurantDto upsertRestaurantDto) {

        final RestaurantEntity restaurantEntity = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find restaurant"));

        restaurantEntity.setName(upsertRestaurantDto.getName());
        restaurantEntity.setDescription(upsertRestaurantDto.getDescription());
        restaurantEntity.setImage(upsertRestaurantDto.getImage());

        return convert(
                restaurantRepository.save(restaurantEntity)
        );
    }

    @Override
    public void deleteOne(final UUID id) {

        restaurantRepository.deleteById(id);
    }

    public RestaurantDto convert(final RestaurantEntity restaurantEntity) {

        final Integer menus = Optional.ofNullable(restaurantEntity.getMenuEntitySet())
                .map(Set::size)
                .orElse(0);

        return RestaurantDto.builder()
                .id(restaurantEntity.getId())
                .name(restaurantEntity.getName())
                .description(restaurantEntity.getDescription())
                .image(restaurantEntity.getImage())
                .menus(menus)
                .build();
    }
}
