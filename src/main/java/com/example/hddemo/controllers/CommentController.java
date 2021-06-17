package com.example.hddemo.controllers;

import com.example.hddemo.models.Comment;
import com.example.hddemo.models.Customer;
import com.example.hddemo.models.Ticket;
import com.example.hddemo.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/ticket-all/{id}/addcomment")
    public String addCommentPost(@PathVariable("id") long ticketId, @ModelAttribute("comment") Comment comment) {
        comment.setTicketId(ticketId);
        comment.setId(null);
        commentRepository.save(comment);
        return "redirect:/ticket-all/{id}/#end";
    }

}
