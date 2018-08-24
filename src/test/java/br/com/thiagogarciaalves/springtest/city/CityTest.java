package br.com.thiagogarciaalves.springtest.city;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityTest {

    @Autowired
    private CityRepository cityRepo;

    @Test
    public void testWithDb() {
        City city1 = cityRepo.save(new City(null, "city1", "USA", 20000L));
        City city2 = cityRepo.save(new City(null, "city2", "USA", 40000L));
        assertThat(city1)
                .matches(c -> c.getId() != null && c.getName() == "city1" && c.getPop() == 20000L);
        assertThat(city2)
                .matches(c -> c.getId() != null && c.getName() == "city2" && c.getPop() == 40000L);
        assertThat(cityRepo.findAll()).containsExactly(city1, city2);
    }

}
