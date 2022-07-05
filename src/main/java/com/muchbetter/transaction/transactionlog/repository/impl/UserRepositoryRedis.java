package com.muchbetter.transaction.transactionlog.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPooled;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Setter
public class UserRepositoryRedis implements UserRepository {
    //    private final JedisPooled jedis = new JedisPooled("redis://localhost:6379");
    private JedisPooled jedis = new JedisPooled(System.getenv("REDIS_URL"));
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String DB = "users:";

    @Override
    public User save(User user) {
        try {
            jedis.set(getSearchToken(user.token()), objectMapper.writeValueAsString(user));
            return user;
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jedis.mget(
                jedis.keys(getSearchToken("*")).toArray(new String[0])
        ).stream().map(item -> {
            try {
                return objectMapper.readValue(item, User.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public User findByToken(String token) {
        try {
            return jedis.get(getSearchToken(token)) != null ?
                    objectMapper.readValue(jedis.get(getSearchToken(token)), User.class)
                    : null;
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User deleteByToken(String token) {
        try {
            return jedis.get(getSearchToken(token)) != null ?
                    objectMapper.readValue(jedis.get(getSearchToken(token)), User.class)
                    : null;
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    private String getSearchToken(String token) {
        return DB + token;
    }

}
