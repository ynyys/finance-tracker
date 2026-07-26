package com.example.financetracker.service;

import com.example.financetracker.model.Transaction;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TransactionServiceTest {

    @Autowired
    private TransactionService service;

    @Test
    void testSaveAndGetById() {
        Transaction t = new Transaction();
        t.setDescription("Coffee");
        t.setAmount(4.50);
        t.setCategory("Food");
        t.setDate(LocalDate.now());
        t.setType("expense");

        Transaction saved = service.save(t);
        Transaction found = service.getById(saved.getId());

        assertNotNull(saved.getId());
        assertEquals("Coffee", found.getDescription());
        assertEquals(4.50, found.getAmount());
        assertEquals("Food", found.getCategory());
        assertEquals("expense", found.getType());
    }

    @Test
    void testGetAll() {
        Transaction t1 = new Transaction();
        t1.setDescription("Coffee");
        t1.setAmount(4.50);
        t1.setCategory("Food");
        t1.setDate(LocalDate.now());
        t1.setType("expense");

        Transaction t2 = new Transaction();
        t2.setDescription("Bus ticket");
        t2.setAmount(2.75);
        t2.setCategory("Transport");
        t2.setDate(LocalDate.now());
        t2.setType("expense");

        service.save(t1);
        service.save(t2);

        List<Transaction> all = service.getAll();

        assertTrue(all.size() >= 2);
    }

    @Test
    void testGetByIdNotFound() {
        assertThrows(RuntimeException.class, () -> service.getById(999999L));
    }

    @Test
    void testUpdate() {
        Transaction t = new Transaction();
        t.setDescription("Old description");
        t.setAmount(10.0);
        t.setCategory("Misc");
        t.setDate(LocalDate.now());
        t.setType("expense");

        Transaction saved = service.save(t);

        Transaction updateData = new Transaction();
        updateData.setDescription("New description");
        updateData.setAmount(20.0);
        updateData.setCategory("Updated");
        updateData.setDate(LocalDate.now());
        updateData.setType("expense");

        Transaction updated = service.update(saved.getId(), updateData);

        assertEquals("New description", updated.getDescription());
        assertEquals(20.0, updated.getAmount());
        assertEquals("Updated", updated.getCategory());
    }

    @Test
    void testDelete() {
        Transaction t = new Transaction();
        t.setDescription("To be deleted");
        t.setAmount(5.0);
        t.setCategory("Misc");
        t.setDate(LocalDate.now());
        t.setType("expense");

        Transaction saved = service.save(t);
        Long id = saved.getId();

        service.delete(id);

        assertThrows(RuntimeException.class, () -> service.getById(id));
    }
}
