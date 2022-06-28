package com.kitm.application.api.author;

import com.kitm.application.api.author.dto.AuthorDto;
import com.kitm.application.api.author.dto.UpsertAuthorDto;
import com.kitm.application.api.common.ICrudService;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface IAuthorService extends ICrudService<AuthorDto, UpsertAuthorDto> {
}
