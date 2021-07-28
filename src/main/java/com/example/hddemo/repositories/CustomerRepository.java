package com.example.hddemo.repositories;

import com.example.hddemo.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public static final Logger LOGGER = LoggerFactory.getLogger(TicketRepository.class);

    @Query("select c from Customer c order by c.name")
    List<Customer> findAllOrderByName();

    default List<Customer> findALlOrderByNameAndLog() {
        return findAllOrderByName();
    }

    default Object saveAndLog(Object object) {
        object = save((Customer) object);
        LOGGER.info("Save {}", object.toString());
        return object;
    }

    default void deleteAndLog(Object object) {
        delete((Customer)object);
        LOGGER.info("Delete {}", object.toString());
    }
}
