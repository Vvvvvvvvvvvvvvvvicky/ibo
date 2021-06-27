package com.vicky.demo_s.repository;

import com.vicky.demo_s.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @className CustomerRepository
 * @desc 测试jpa功能
 *
 * By extending CrudRepository,
 * CustomerRepository inherits several methods for working with Customer persistence,
 * including methods for saving, deleting, and finding Customer entities.
 *
 * In a typical Java application,
 * you might expect to write a class that implements CustomerRepository.
 * However, that is what makes Spring Data JPA so powerful:
 * You need not write an implementation of the repository interface.
 * Spring Data JPA creates an implementation when you run the application.
 *
 * @author Vic
 * @version 1.0
 * @date 2021/5/23 4:50 下午
 **/
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
