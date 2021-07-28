package com.example.hddemo.controllers;

import com.example.hddemo.models.Comment;
import com.example.hddemo.models.Ticket;
import com.example.hddemo.repositories.CommentRepository;
import com.example.hddemo.repositories.CustomerRepository;
import com.example.hddemo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Controller
public class TicketController {

    @Autowired private TicketRepository ticketRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private CustomerRepository customerRepository;
    private ArrayList<Object[]> tickets;

    @GetMapping("/")
    public String home() {
        return "redirect:/ticket-all";
    }

    @GetMapping("/ticket-all")
    public String ticketAll(@ModelAttribute("sorting") String sorting,
                            @ModelAttribute("find") String find,
                            @ModelAttribute("before") String before,
                            @ModelAttribute("after") String after,
                            Model model,
                            HttpServletRequest request) {

        tickets = (ArrayList<Object[]>) ticketRepository.findAllTest();

        findObjects(find);
        filterObjectsByDate(before, after);
        sortObjects(sorting);

        model.addAttribute("tickets", tickets);

        return "ticket-all.html";
    }

    @GetMapping("/ticket-add")
    public String ticketAdd(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("customers", customerRepository.findAllOrderByName());

        return "ticket-add.html";
    }

    // Save after add
    @PostMapping("/ticket-add")
    public String ticketAdd(@ModelAttribute("ticket") Ticket ticket) {
        ticketRepository.save(ticket);
        return "redirect:/ticket-all";
    }

    // Edit
    @GetMapping("/ticket-all/{id}")
    public String editTicket(@PathVariable("id") long id, Model model) {
        model.addAttribute("ticket",    ticketRepository.findById(id));
        model.addAttribute("customers", customerRepository.findAllOrderByName());
        model.addAttribute("comments",  commentRepository.findAllByTicketId(id));
        return "ticket.html";
    }

    // Save after edit
    @PostMapping("/ticket-all/{id}/save")
    public String editTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketRepository.saveAndLog(ticket);
        return "redirect:/ticket-all";
    }
    @PostMapping("/ticket-all/{id}/remove")
    public String DeleteTicket(@ModelAttribute("ticket") Ticket ticket) {
        List<Comment> comments = commentRepository.findAllByTicketId(ticket.getId());
        for (Comment comment: comments)
            commentRepository.deleteAndLog(comment);
        ticketRepository.deleteAndLog(ticket);
        return "redirect:/ticket-all";
    }

    private void findObjects(String find) {
        List<Object[]> ticketsLinkedList = new LinkedList<>();
        ticketsLinkedList.addAll(tickets);
        if (!find.equals("")) {
            for (Iterator<Object[]> iterator = ticketsLinkedList.iterator(); iterator.hasNext();) {
                Object[] ticket = iterator.next();
                boolean remove = true;
                for (Object column : ticket) {
                    if (column != null && column.toString().contains(find)) {
                        remove = false;
                    }
                }
                if (remove) {
                    iterator.remove();
                }
            }
        }
        tickets.clear();
        tickets.addAll(ticketsLinkedList);
    }

    private void filterObjectsByDate(String before, String after) {
        Date beforeDate = null;
        Date afterDate = null;
        try {
            if (before.equals(""))
                beforeDate = new Date(Long.MAX_VALUE);
            else
                beforeDate = new SimpleDateFormat("yyyy-MM-dd").parse(before);

            if (after.equals(""))
                afterDate = new Date(Long.MIN_VALUE);
            else
                afterDate = new SimpleDateFormat("yyyy-MM-dd").parse(after);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Date beforeDate1 = beforeDate;
        final Date afterDate1 = afterDate;
        if (!before.equals("") || !after.equals("")) {
            tickets = (ArrayList<Object[]>) tickets.stream().filter(columns -> {
                try {
                    Method beforeMethod = columns[2].getClass().getMethod("before", Date.class);
                    Method afterMethod = columns[2].getClass().getMethod("after", Date.class);
                    return (boolean) beforeMethod.invoke(columns[2], beforeDate1) && (boolean) afterMethod.invoke(columns[2], afterDate1);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());
        }
    }

    private void sortObjects(String sorting) {
        if (sorting.equals("id"))
            tickets.sort((t1, t2) -> (int) ((Long) t2[0] - (Long) t1[0]));
        else if (sorting.equals("date"))
            tickets.sort((t1, t2) -> (int) (((Date) t1[2]).getTime() - ((Date) t2[2]).getTime()));
    }
}
