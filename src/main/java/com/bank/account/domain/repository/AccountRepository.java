package com.bank.account.domain.repository;

import com.bank.account.domain.model.Account;
import java.util.Optional;

/**
 * Repository interface for Account entity operations.
 */
public interface AccountRepository {

    /**
     * Save an account to the database.
     * @param account the account to save
     * @return the saved account
     */
    Account save(Account account);

    /**
     * Find an account by user ID.
     * @param userId the user ID
     * @return the account if found
     */
    Optional<Account> findByUserId(String userId);

    /**
     * Find an account by account ID (assuming we add an ID field later).
     * @param accountId the account ID
     * @return the account if found
     */
    Optional<Account> findById(String accountId);

    /**
     * Check if an account exists for the user.
     * @param userId the user ID
     * @return true if exists
     */
    boolean existsByUserId(String userId);
}
