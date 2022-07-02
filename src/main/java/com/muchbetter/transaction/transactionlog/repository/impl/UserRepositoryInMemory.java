package com.muchbetter.transaction.transactionlog.repository.impl;

import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository {
    Map<String, User> users = new HashMap<>();

    @Override
    public User save(User user){
        users.put(user.token(), user);
        return users.get(user.token());
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public User findByToken(String token) {
        return users.get(token);
    }

    @Override
    public User deleteByToken(String token) {
        return users.remove(token);
    }
}
