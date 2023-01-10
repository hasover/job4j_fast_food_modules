package ru.job4j;

public interface DishService {
    void addDish(Dish dish);

    void removeDish(int id);

    void updateDish(Dish dish);

    Dish findDishById(int id);

}
