package com.muchbetter.transaction.transactionlog.repository;

import com.muchbetter.transaction.transactionlog.model.User;

import java.util.List;

public interface UserRepository {
    /**
     * Save a user to the database.
     *
     * @param user The user object to be saved.
     * @return The user object that was saved.
     */
    User save(User user);

    /**
     * Find all users.
     *
     * @return An array of all the users in the database.
     */
    List<User> findAll();

    /**
     * Find a user by their token.
     *
     * @param token The token that was generated when the user was created.
     * @return A User object.
     */
    User findByToken(String token);

    /**
     * Delete a user by token.
     *
     * @param token The token that was returned when the user was created.
     * @return The user that was deleted.
     */
    User deleteByToken(String token);
}
