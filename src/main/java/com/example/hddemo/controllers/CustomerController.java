package com.example.hddemo.controllers;

import com.example.hddemo.models.Customer;
import com.example.hddemo.models.Ticket;
import com.example.hddemo.repositories.CustomerRepository;
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

    // вывод всех клиентов
    @GetMapping("/customer-all")
    public String customerAllGet(Model model) {
        model.addAttribute("customers", this.customerRepository.findAll());
        return "customer-all.html";
    }

    // вывод формы добавления клиента
    @GetMapping("/customer-add")
    public String customerAddGet(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-add.html";
    }

    // сохраняем новый клиент из формы добавления
    @PostMapping("/customer-add")
    public String customerAddPost(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer-all";
    }

    // вывод формы редактирования клиента
    @GetMapping("/customer-all/{id}")
    public String customerTicketGet(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id));
        return "customer.html";
    }

    // cохранение клиента после редактирования
    @PostMapping("/customer-all/{id}/save")
    public String editTicketPost(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer-all";
    }

    // удаление клиента
    @PostMapping("/customer-all/{id}/remove")
    public String DeleteTicketPost(@ModelAttribute("customer") Customer customer) {
        customerRepository.delete(customer);
        return "redirect:/customer-all";
    }

}
