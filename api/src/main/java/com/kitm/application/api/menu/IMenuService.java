package com.kitm.application.api.menu;

import com.kitm.application.api.common.ICrudService;
import com.kitm.application.api.menu.dto.MenuDto;
import com.kitm.application.api.menu.dto.UpsertMenuDto;

public interface IMenuService extends ICrudService<MenuDto, UpsertMenuDto> {
}
