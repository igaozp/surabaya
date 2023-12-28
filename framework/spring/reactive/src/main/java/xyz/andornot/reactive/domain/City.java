package xyz.andornot.reactive.domain;

public record City(Long id, Long provinceId, String cityName, String description) {
    public City withId(Long id) {
        return new City(id, provinceId, cityName, description);
    }
}
