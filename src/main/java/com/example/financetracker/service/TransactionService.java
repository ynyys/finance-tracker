package com.example.financetracker.service;

import com.example.financetracker.exception.TransactionNotFoundException;
import com.example.financetracker.model.Transaction;
import com.example.financetracker.repository.TransactionRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    public List<Transaction> getAll(){
        return repository.findAll();
    }

    public Transaction getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public Transaction update(Long id, Transaction transaction) {
        Transaction toUpdate = getById(id);

        toUpdate.setDescription(transaction.getDescription());
        toUpdate.setAmount(transaction.getAmount());
        toUpdate.setCategory(transaction.getCategory());
        toUpdate.setDate(transaction.getDate());
        toUpdate.setType(transaction.getType());

        return save(toUpdate);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TransactionNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
