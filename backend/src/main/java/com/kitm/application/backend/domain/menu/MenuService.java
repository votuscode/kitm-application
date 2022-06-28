package com.kitm.application.backend.domain.menu;

import com.kitm.application.api.menu.IMenuService;
import com.kitm.application.api.menu.dto.MenuDto;
import com.kitm.application.api.menu.dto.UpsertMenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService implements IMenuService {

    private final MenuRepository menuRepository;

    @Override
    public Collection<MenuDto> findAll() {

        return menuRepository.findAll().stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public MenuDto getOne(final UUID id) {

        final MenuEntity bookEntity = menuRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find menu"));

        return convert(bookEntity);
    }

    @Override
    public MenuDto createOne(final UpsertMenuDto upsertMenuDto) {

        final MenuEntity menuEntity = MenuEntity.builder()
                .name(upsertMenuDto.getName())
                .description(upsertMenuDto.getDescription())
                .build();

        return convert(
                menuRepository.save(menuEntity)
        );
    }

    @Override
    public MenuDto updateOne(final UUID id, final UpsertMenuDto upsertMenuDto) {

        final MenuEntity menuEntity = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find menu"));

        menuEntity.setName(upsertMenuDto.getName());
        menuEntity.setDescription(upsertMenuDto.getDescription());

        return convert(
                menuRepository.save(menuEntity)
        );
    }

    @Override
    public void deleteOne(final UUID id) {

        menuRepository.deleteById(id);
    }

    private MenuDto convert(final MenuEntity menuEntity) {

        return MenuDto.builder()
                .id(menuEntity.getId())
                .name(menuEntity.getName())
                .description(menuEntity.getDescription())
                .build();
    }
}
