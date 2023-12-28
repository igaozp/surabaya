package xyz.andornot.reactive.city;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import xyz.andornot.reactive.CityHandler;
import xyz.andornot.reactive.controller.CityController;
import xyz.andornot.reactive.domain.City;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CityControllerTest {

    @InjectMocks
    private CityController cityController;

    @Mock
    private CityHandler cityHandler;

    @Test
    public void findCityByIdReturnsCity() {
        City city = new City(1L, null, null, null);
        when(cityHandler.findCityById(anyLong())).thenReturn(Mono.just(city));

        StepVerifier.create(cityController.findCityById(1L))
                .expectNext(city)
                .verifyComplete();
    }

    @Test
    public void findCityByIdReturnsEmptyWhenNotFound() {
        when(cityHandler.findCityById(anyLong())).thenReturn(Mono.empty());

        StepVerifier.create(cityController.findCityById(1L))
                .verifyComplete();
    }

    @Test
    public void findAllCityReturnsCities() {
        City city1 = new City(1L, null, null, null);
        City city2 = new City(2L, null, null, null);
        when(cityHandler.findAllCity()).thenReturn(Flux.just(city1, city2));

        StepVerifier.create(cityController.findAllCity())
                .expectNext(city1, city2)
                .verifyComplete();
    }

    @Test
    public void findAllCityReturnsEmptyWhenNoCities() {
        when(cityHandler.findAllCity()).thenReturn(Flux.empty());

        StepVerifier.create(cityController.findAllCity())
                .verifyComplete();
    }

    @Test
    public void saveCityReturnsCityId() {
        City city = new City(1L, null, null, null);
        when(cityHandler.modifyCity(any(City.class))).thenReturn(Mono.just(1L));

        StepVerifier.create(cityController.saveCity(city))
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    public void deleteCityReturnsCityId() {
        when(cityHandler.deleteCity(anyLong())).thenReturn(Mono.just(1L));

        StepVerifier.create(cityController.deleteCity(1L))
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    public void deleteCityReturnsEmptyWhenNotFound() {
        when(cityHandler.deleteCity(anyLong())).thenReturn(Mono.empty());

        StepVerifier.create(cityController.deleteCity(1L))
                .verifyComplete();
    }
}
