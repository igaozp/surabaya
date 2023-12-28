package xyz.andornot.reactive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.andornot.reactive.CityHandler;
import xyz.andornot.reactive.domain.City;

@RestController
@RequestMapping("city")
@RequiredArgsConstructor
public class CityController {
    private final CityHandler cityHandler;

    @GetMapping("{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        return cityHandler.findCityById(id);
    }

    @GetMapping
    public Flux<City> findAllCity() {
        return cityHandler.findAllCity();
    }

    @PostMapping
    public Mono<Long> saveCity(@RequestBody City city) {
        return cityHandler.modifyCity(city);
    }

    @DeleteMapping("{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityHandler.deleteCity(id);
    }
}
