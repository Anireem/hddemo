package com.example.hddemo.controllers;

import com.example.hddemo.models.Comment;
import com.example.hddemo.models.Customer;
import com.example.hddemo.models.Ticket;
import com.example.hddemo.repositories.CommentRepository;
import com.example.hddemo.repositories.CustomerRepository;
import com.example.hddemo.repositories.TicketRepository;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    CustomerRepository customerRepository;

    // вывод всех тикетов
    @GetMapping("/ticket-all")
    public String ticketAllGet(Model model) {
        model.addAttribute("tickets", this.ticketRepository.findAll());
//        List list = ticketRepository.findAll().sort(Comparator.comparing(Ticket::getId));
        return "ticket-all.html";
    }

    // вывод формы добавления тикета
    @GetMapping("/ticket-add")
    public String ticketAddGet(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("customers", this.customerRepository.findAll());
        return "ticket-add.html";
    }

    // сохраняем новый тикет из формы добавления
    @PostMapping("/ticket-add")
    public String ticketAddPost(@ModelAttribute("ticket") Ticket ticket) {
        ticketRepository.save(ticket);
        return "redirect:/ticket-all";
    }

    // вывод формы редактирования тикета
    @GetMapping("/ticket-all/{id}")
    public String editTicketGet(@PathVariable("id") long id, Model model) {
        model.addAttribute("ticket", ticketRepository.findById(id));
        model.addAttribute("customers", this.customerRepository.findAll());
//        model.addAttribute("comments", commentRepository.)
        model.addAttribute("comments", commentRepository.findAllByTicketId(id));
        System.out.println("fff");
        return "ticket.html";
    }

    // cохранение тикета после редактирования
    @PostMapping("/ticket-all/{id}/save")
    public String editTicketPost(@ModelAttribute("ticket") Ticket ticket) {
        ticketRepository.save(ticket);
        return "redirect:/ticket-all";
    }

    // удаление тикета
    @PostMapping("/ticket-all/{id}/remove")
    public String DeleteTicketPost(@ModelAttribute("ticket") Ticket ticket) {

//        commentRepository.deleteCommentsByTicketId(ticket.getId());
        List<Comment> comments = commentRepository.findAllByTicketId(ticket.getId());
        for (Comment comment: comments) {
            commentRepository.delete(comment);
        }
        ticketRepository.delete(ticket);
        return "redirect:/ticket-all";
    }


}
