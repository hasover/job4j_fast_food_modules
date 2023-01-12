package ru.job4j.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ru.job4j.Dish;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DishAPIRepository {
    private final RestTemplate restTemplate;

    @Value("${dishes.api.url}")
    private String url;

    public Dish findDishById(int id) {
        return  restTemplate.getForEntity(
               String.format("%s/%d", url, id), Dish.class).getBody();
    }

    public List<Dish> findAllDishes() {
        List<Dish> body = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Dish>>() {
        }).getBody();
        return body == null ? Collections.emptyList() : body;
    }

    public Dish addDish(Dish dish) {
        return restTemplate.postForEntity(
                url, dish, Dish.class
        ).getBody();
    }

    public boolean editDish(Dish dish) {
        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(dish),
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean deleteDish(int id) {
        return restTemplate.exchange(
                String.format("%s/%d", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }
}
