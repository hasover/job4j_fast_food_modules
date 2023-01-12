package ru.job4j.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.Dish;
import ru.job4j.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public List<Dish> findAllDishes() {
        return dishService.findAllDishes();
    }

    @GetMapping("/{id}")
    public Dish findDish(@PathVariable("id") int id) {
        return dishService.findDishById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDish(@RequestBody Dish dish) {
        dishService.saveDish(dish);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void editDish(@RequestBody Dish dish) {
        dishService.saveDish(dish);
    }

    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable("id") int id) {
        dishService.removeDish(id);
    }
}
