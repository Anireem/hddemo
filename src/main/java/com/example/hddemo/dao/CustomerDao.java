package com.example.hddemo.dao;

import com.example.hddemo.models.Customer;
import com.example.hddemo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class CustomerDao {

    @Autowired
    private static CustomerRepository customerRepository;

    public static Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

}
