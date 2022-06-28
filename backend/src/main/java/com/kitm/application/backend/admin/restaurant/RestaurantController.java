package com.kitm.application.backend.admin.restaurant;

import com.kitm.application.backend.admin.restaurant.dto.SubmitRestaurantFormDto;
import com.kitm.application.backend.admin.common.models.Form;
import com.kitm.application.backend.admin.common.models.ItemList;
import com.kitm.application.backend.admin.common.models.Layout;
import com.kitm.application.backend.domain.restaurant.RestaurantService;
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
public class RestaurantController {

    private final RestaurantService restaurantService;
    
    @GetMapping("/admin/restaurants")
    public String restaurantsList(Model model) {

        model.addAttribute("layout", Layout.create(Layout.Pages.Restaurants));

        model.addAttribute("itemList", new ItemList(
                "Restaurants",
                "Restaurants page",
                new ItemList.Action("Add restaurant", "/admin/restaurants/add"),
                restaurantService.findAll().stream()
                        .map(restaurant -> ItemList.Item.builder()
                                .name(restaurant.getName())
                                .description(restaurant.getDescription())
                                .image(restaurant.getImage())
                                .info(String.format("Menus: %s", restaurant.getMenus()))
                                .href("/admin/restaurants/" + restaurant.getId())
                                .build())
                        .toList()
        ));

        return "layout/item-list";
    }

    @PostMapping("/admin/restaurants/update")
    public String updateRestaurant(@ModelAttribute SubmitRestaurantFormDto submitRestaurantFormDto, Model model) {

        switch (submitRestaurantFormDto.getAction()) {
            case Add -> restaurantService.createOne(submitRestaurantFormDto);
            case Update -> restaurantService.updateOne(submitRestaurantFormDto.getId(), submitRestaurantFormDto);
            case Delete -> restaurantService.deleteOne(submitRestaurantFormDto.getId());
        }

        return "redirect:/admin/restaurants";
    }

    @GetMapping("/admin/restaurants/add")
    public String addRestaurant(Model model) {

        model.addAttribute("layout", Layout.create(Layout.Pages.Restaurants));

        model.addAttribute("form", Form.add("Add restaurant", "Add restaurant page", "/admin/restaurants/update"));
        model.addAttribute("dto", SubmitRestaurantFormDto.create());

        return "restaurants/update";
    }

    @GetMapping("/admin/restaurants/{id}")
    public String updateRestaurant(@PathVariable("id") UUID id, Model model) {

        model.addAttribute("layout", Layout.create(Layout.Pages.Restaurants));

        model.addAttribute("form", Form.update("Update restaurant", "Update restaurant page", "/admin/restaurants/update"));
        model.addAttribute("dto", SubmitRestaurantFormDto.update(restaurantService.getOne(id)));

        return "restaurants/update";
    }
}
