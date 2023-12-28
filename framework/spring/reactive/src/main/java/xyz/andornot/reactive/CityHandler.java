package xyz.andornot.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.andornot.reactive.dao.CityRepository;
import xyz.andornot.reactive.domain.City;

@Component
@RequiredArgsConstructor
public class CityHandler {
    private final CityRepository cityRepository;

    public Mono<Long> save(City city) {
        return Mono.create(sink -> sink.success(cityRepository.save(city)));
    }

    public Mono<City> findCityById(Long id) {
        return Mono.justOrEmpty(cityRepository.findCityById(id));
    }

    public Flux<City> findAllCity() {
        return Flux.fromIterable(cityRepository.findAll());
    }

    public Mono<Long> modifyCity(City city) {
        return Mono.create(sink -> sink.success(cityRepository.updateCity(city)));
    }

    public Mono<Long> deleteCity(Long id) {
        return Mono.create(sink -> sink.success(cityRepository.deleteCity(id)));
    }

    public Mono<ServerResponse> helloCity(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, City!"));
    }
}
