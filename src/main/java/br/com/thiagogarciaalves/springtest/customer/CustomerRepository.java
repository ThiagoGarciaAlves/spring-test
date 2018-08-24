package br.com.thiagogarciaalves.springtest.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

}
