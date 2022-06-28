package com.kitm.application.backend.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
}
