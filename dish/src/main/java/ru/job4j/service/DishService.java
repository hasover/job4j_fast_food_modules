package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.Dish;
import ru.job4j.messaging.EventPublisher;
import ru.job4j.repository.DishRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {

    private final EventPublisher eventPublisher;
    private final DishRepository dishRepository;

    public void saveDish(Dish dish) {
        dishRepository.save(dish);
        eventPublisher.dishUpdated();
    }

    public void removeDish(int id) {
        dishRepository.deleteById(id);
        eventPublisher.dishUpdated();
    }

    public Optional<Dish> findDishById(int id) {
        return dishRepository.findById(id);
    }

    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }

}
