package com.example.hddemo.db;

import com.example.hddemo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class DBInit implements CommandLineRunner {

    private UserRepository userRepository;

    public DBInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        Logger logger = LoggerFactory.getLogger(DBInit.class);
        logger.info("DBInit");


//        User admin = new User("admin", "admin", "ADMIN", "ACCESS_TEST1");
//        List<User> users = Arrays.asList(admin);
//        this.userRepository.saveAll(users);
    }
}