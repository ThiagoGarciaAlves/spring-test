package br.com.thiagogarciaalves.springtest.city;

import org.springframework.data.repository.CrudRepository;

interface CityRepository extends CrudRepository<City, Long> {
}
