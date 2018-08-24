package br.com.thiagogarciaalves.springtest.city;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityTest {

    @ClassRule
    public static MySQLContainer mysql = new MySQLContainer("mysql:5.7");

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
