package br.com.thiagogarciaalves.springtest.customer;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTest {

    @ClassRule
    public static MySQLContainer mysql = new MySQLContainer("mysql:5.7");

    @Autowired
    private CustomerRepository repository;

    @Test
    public void repositoryTests() {

        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));


        System.out.println();
        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer.toString());
        }
        System.out.println("");

        // fetch an individual customer by ID
        repository.findById(1L)
                .ifPresent(customer -> {
                    System.out.println("Customer found with findById(1L):");
                    System.out.println("--------------------------------");
                    System.out.println(customer.toString());
                    System.out.println("");
                });

        // fetch customers by last name
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        repository.findByLastName("Bauer").forEach(bauer -> {
            System.out.println(bauer.toString());
        });
        // for (Customer bauer : repository.findByLastName("Bauer")) {
        // 	System.out.println(bauer.toString());
        // }
        System.out.println("");

    }

}
