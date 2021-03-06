package com.example.hddemo.repositories;

import com.example.hddemo.models.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommentRepository.class);

    List<Comment> findAllByTicketId(Long ticketId);

    default Object saveAndLog(Object object) {
        object = save((Comment)object);
        LOGGER.info("Save {}", object.toString());
        return object;
    }

    default void deleteAndLog(Object object) {
        delete((Comment) object);
        LOGGER.info("Delete {}", object.toString());
    }
}
