package com.kitm.application.api.wish;

import com.kitm.application.api.wish.dto.CreateWishDto;
import com.kitm.application.api.wish.dto.WishDto;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
public interface IWishService {

  Collection<WishDto> findAllByUserId(UUID userId);

  WishDto getOne(UUID wishId);

  WishDto createOne(CreateWishDto createWishDto);

  void deleteOne(UUID wishId);
}
