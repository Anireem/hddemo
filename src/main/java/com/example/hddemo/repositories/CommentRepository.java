package com.example.hddemo.repositories;

import com.example.hddemo.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTicketId(Long ticketId);
//    List<Comment> deleteByTicketId(Long ticketId);
//    void deleteByTicketId(Long ticketId);
//    @Modifying
//    @Query("delete from Comment where ticketId = 7")
//    void deleteAllByTicketId(Long ticketId);

//    List<Comment> deleteCommentsByTicketId(Long ticketId);
}
