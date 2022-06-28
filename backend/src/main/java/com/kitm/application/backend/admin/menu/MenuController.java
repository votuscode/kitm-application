package com.kitm.application.backend.admin.menu;

import com.kitm.application.backend.admin.menu.dto.SubmitMenuFormDto;
import com.kitm.application.backend.admin.common.models.Form;
import com.kitm.application.backend.admin.common.models.ItemList;
import com.kitm.application.backend.admin.common.models.Layout;
import com.kitm.application.backend.domain.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    
    @GetMapping("/admin/menus")
    public String menusList(Model model) {

        model.addAttribute("layout", Layout.create(Layout.Pages.Menus));

        model.addAttribute("itemList", new ItemList(
                "Menus",
                "Menus page",
                new ItemList.Action("Add menu", "/admin/menus/add"),
                menuService.findAll().stream()
                        .map(menu -> ItemList.Item.builder()
                                .name(menu.getName())
                                .description(menu.getDescription())
                                .info(String.format("Info: %s", "--------"))
                                .href("/admin/menus/" + menu.getId())
                                .build())
                        .toList()
        ));

        return "layout/item-list";
    }

    @PostMapping("/admin/menus/update")
    public String updateMenu(@ModelAttribute SubmitMenuFormDto submitMenuFormDto, Model model) {

        switch (submitMenuFormDto.getAction()) {
            case Add -> menuService.createOne(submitMenuFormDto);
            case Update -> menuService.updateOne(submitMenuFormDto.getId(), submitMenuFormDto);
            case Delete -> menuService.deleteOne(submitMenuFormDto.getId());
        }

        return "redirect:/admin/menus";
    }

    @GetMapping("/admin/menus/add")
    public String addMenu(Model model) {

        model.addAttribute("layout", Layout.create(Layout.Pages.Menus));

        model.addAttribute("form", Form.add("Add menu", "Add menu page", "/admin/menus/update"));
        model.addAttribute("dto", SubmitMenuFormDto.create());

        return "menus/update";
    }

    @GetMapping("/admin/menus/{id}")
    public String updateMenu(@PathVariable("id") UUID id, Model model) {

        model.addAttribute("layout", Layout.create(Layout.Pages.Menus));

        model.addAttribute("form", Form.update("Update menu", "Update menu page", "/admin/menus/update"));
        model.addAttribute("dto", SubmitMenuFormDto.update(menuService.getOne(id)));

        return "menus/update";
    }
}
