package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.Dish;
import ru.job4j.repository.DishAPIRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishAPIRepository dishAPIRepository;

    public Dish findDishById(int id) {
        return dishAPIRepository.findDishById(id);
    }

    public List<Dish> findAllDishes() {
        return dishAPIRepository.findAllDishes();
    }

    public void addDish(Dish dish) {
        dishAPIRepository.addDish(dish);
    }

    public void deleteDish(int id) {
        dishAPIRepository.deleteDish(id);
    }

    public void editDish(Dish dish) {
        dishAPIRepository.editDish(dish);
    }
}
