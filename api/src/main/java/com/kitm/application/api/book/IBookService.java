package com.kitm.application.api.book;

import com.kitm.application.api.book.dto.BookDto;
import com.kitm.application.api.book.dto.UpsertBookDto;
import com.kitm.application.api.common.ICrudService;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface IBookService extends ICrudService<BookDto, UpsertBookDto> {
}
