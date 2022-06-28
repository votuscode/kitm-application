package com.kitm.application.backend.domain.restaurant;

import com.kitm.application.api.restaurant.IRestaurantService;
import com.kitm.application.api.restaurant.dto.RestaurantDto;
import com.kitm.application.api.restaurant.dto.UpsertRestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Collection<RestaurantDto> findAll() {

        return restaurantRepository.findAll().stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public RestaurantDto getOne(final UUID id) {

        final RestaurantEntity bookEntity = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find restaurant"));

        return convert(bookEntity);
    }

    @Override
    public RestaurantDto createOne(final UpsertRestaurantDto upsertRestaurantDto) {

        final RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .name(upsertRestaurantDto.getName())
                .description(upsertRestaurantDto.getDescription())
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

        return convert(
                restaurantRepository.save(restaurantEntity)
        );
    }

    @Override
    public void deleteOne(final UUID id) {

        restaurantRepository.deleteById(id);
    }

    private RestaurantDto convert(final RestaurantEntity restaurantEntity) {

        return RestaurantDto.builder()
                .id(restaurantEntity.getId())
                .name(restaurantEntity.getName())
                .description(restaurantEntity.getDescription())
                .build();
    }
}
