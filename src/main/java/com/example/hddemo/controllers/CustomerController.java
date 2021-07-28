package com.example.hddemo.controllers;

import com.example.hddemo.models.Customer;
import com.example.hddemo.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer-all")
    public String customerAll(Model model) {
        model.addAttribute("customers", this.customerRepository.findAllOrderByName());
        return "customer-all.html";
    }

    @GetMapping("/customer-add")
    public String customerAdd(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-add.html";
    }

    // Save after add
    @PostMapping("/customer-add")
    public String customerAdd(@ModelAttribute("customer") Customer customer) {
        customerRepository.saveAndLog(customer);
        return "redirect:/customer-all";
    }

    // Edit
    @GetMapping("/customer-all/{id}")
    public String customerTicketGet(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id));
        return "customer.html";
    }

    // Save after edit
    @PostMapping("/customer-all/{id}/save")
    public String editTicket(@ModelAttribute("customer") Customer customer) {
        customerRepository.saveAndLog(customer);
        return "redirect:/customer-all";
    }

    @PostMapping("/customer-all/{id}/remove")
    public String DeleteTicket(@ModelAttribute("customer") Customer customer) {
        customerRepository.deleteAndLog(customer);
        return "redirect:/customer-all";
    }

}
