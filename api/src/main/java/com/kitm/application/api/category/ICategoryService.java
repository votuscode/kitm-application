package com.kitm.application.api.category;

import com.kitm.application.api.category.dto.CategoryDto;
import com.kitm.application.api.category.dto.UpsertCategoryDto;
import com.kitm.application.api.common.ICrudService;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface ICategoryService extends ICrudService<CategoryDto, UpsertCategoryDto> {
}
