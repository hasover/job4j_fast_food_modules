package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.Dish;
import ru.job4j.service.DishService;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final DishService dishService;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("dishes", dishService.findAllDishes());
        model.addAttribute("dish", new Dish());
        return "index";
    }

    @PostMapping("/dish")
    public String addDish(@ModelAttribute Dish dish) {
        dishService.addDish(dish);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDish(@PathVariable int id) {
        dishService.deleteDish(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("dish", dishService.findDishById(id));
        return "edit";
    }

    @PutMapping("/edit")
    public String editDish(@ModelAttribute Dish dish) {
        dishService.editDish(dish);
        return "redirect:/";
    }

}
