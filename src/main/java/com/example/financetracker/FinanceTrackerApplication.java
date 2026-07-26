package com.example.financetracker;

import com.example.financetracker.model.Transaction;
import com.example.financetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class FinanceTrackerApplication {

    private final TransactionRepository repository;

    public FinanceTrackerApplication(TransactionRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinanceTrackerApplication.class, args);
    }

    /*
    @Bean
    public CommandLineRunner test1 (TransactionRepository repository) {
        return args -> {
            Transaction t1 = new Transaction();

            t1.setId(null);
            t1.setDescription("lime");
            t1.setAmount(67.0);
            t1.setCategory("test");
            t1.setDate(LocalDate.now());
            t1.setType("deposit");

            repository.save(t1);

            List<Transaction> transactions = repository.findAll();
            transactions.forEach(t -> System.out.println(t));
        };
    }
    */
}
