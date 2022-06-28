package com.kitm.application.api.authentication;

import com.kitm.application.api.authentication.dto.AuthenticatedDto;
import com.kitm.application.api.authentication.dto.LoginDto;
import org.springframework.security.core.Authentication;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
public interface IAuthenticationService {

  AuthenticatedDto login(LoginDto loginDto);

  AuthenticatedDto convert(Authentication authentication);
}
