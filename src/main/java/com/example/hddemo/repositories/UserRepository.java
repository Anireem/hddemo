package com.example.hddemo.repositories;

import com.example.hddemo.models.Ticket;
import com.example.hddemo.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    User findByUsername(String username);

    default Object saveAndLog(Object object) {
        object = save((User)object);
        LOGGER.info("Save {}", object.toString());
        return object;
    }

    default void deleteAndLog(Object object) {
        delete((User)object);
        LOGGER.info("Delete {}", object.toString());
    }
}
