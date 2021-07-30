package com.example.hddemo.repositories;

import com.example.hddemo.models.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public static final Logger LOGGER = LoggerFactory.getLogger(TicketRepository.class);

    @Query("select t.id, t.name, t.date, t.completed, c.name from Ticket t left join Customer c on t.customerId = c.id order by t.date desc")
    List<Object[]> findAllTest();

    default Object saveAndLog(Object object) {
        object = save((Ticket)object);
        LOGGER.info("Save {}", object.toString());
        return object;
    }

    default void deleteAndLog(Object object) {
        delete((Ticket)object);
        LOGGER.info("Delete {}", object.toString());
    }
}
