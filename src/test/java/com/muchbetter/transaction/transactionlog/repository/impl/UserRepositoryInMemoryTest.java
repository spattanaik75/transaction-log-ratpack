package com.muchbetter.transaction.transactionlog.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryInMemoryTest {
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = new UserRepositoryInMemory();
        userRepository.save(new User("Default", new BigDecimal("1000.00"), "USD", new ArrayList<>()));
    }

    @Test
    void save() throws JsonProcessingException {
        //when
        userRepository.save(new User("token", new BigDecimal("100.00"), "ZAR", new ArrayList<>()));
        //then
        assertEquals(new BigDecimal("100.00"), userRepository.findByToken("token").balance());
        assertEquals("ZAR", userRepository.findByToken("token").currency());
    }

    @Test
    void findAll() throws JsonProcessingException {
        assertEquals(1, userRepository.findAll().stream().count());
    }

    @Test
    void findByToken() {
        assertNotNull(userRepository.findByToken("Default"));
        assertEquals("USD", userRepository.findByToken("Default").currency());
    }

    @Test
    void deleteByToken() {
        //when
        userRepository.deleteByToken("Default");
        //then
        assertNull(userRepository.findByToken("Default"));
    }
}