package com.kitm.application.backend.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {
}
