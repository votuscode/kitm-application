package com.kitm.application.api.user;

import com.kitm.application.api.common.ICrudService;
import com.kitm.application.api.user.dto.CreateUserDto;
import com.kitm.application.api.user.dto.UserDto;
import org.springframework.security.core.Authentication;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 14.02.22
 */
public interface IUserService extends ICrudService<UserDto, CreateUserDto> {

  UserDto getAuthenticatedUser(Authentication authentication);

  boolean isUsernameExist(String username);
}
