package com.bank.account.application.service;

import com.bank.account.adapters.outbound.messaging.AccountEventProducer;
import com.bank.account.domain.model.Account;
import com.bank.account.domain.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.Optional;
import java.util.UUID;

/**
 * Service for account-related business logic.
 */
@ApplicationScoped
public class AccountService {

    private static final Logger LOG = Logger.getLogger(AccountService.class);

    @Inject
    AccountRepository accountRepository;

    @Inject
    AccountEventProducer eventProducer;

    /**
     * Create a new bank account for a user.
     * @param userId the user ID
     * @param firstName user's first name
     * @param surname user's surname
     * @param address user's address
     * @return the created account
     */
    @Transactional
    public Account createAccount(String userId, String firstName, String surname, String address) {
        LOG.infof("Creating account for user: %s", userId);

        // Check if account already exists
        if (accountRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("Account already exists for user: " + userId);
        }

        // Generate account ID and number
        String accountId = UUID.randomUUID().toString();
        String accountNumber = generateAccountNumber();

        // Create account
        Account account = new Account(accountId, accountNumber, userId, firstName, surname, address);

        // Save to repository
        Account savedAccount = accountRepository.save(account);

        // Publish event
        eventProducer.publishAccountCreated(accountId, accountNumber, "Account created for user " + userId);

        LOG.infof("Account created successfully: %s", accountId);
        return savedAccount;
    }

    /**
     * Find account by user ID.
     * @param userId the user ID
     * @return the account if found
     */
    public Optional<Account> findAccountByUserId(String userId) {
        return accountRepository.findByUserId(userId);
    }

    /**
     * Simple account number generation (in real app, use a proper generator).
     * @return a generated account number
     */
    private String generateAccountNumber() {
        // For demo, generate a random 10-digit number
        return String.valueOf((long) (Math.random() * 9_000_000_000L) + 1_000_000_000L);
    }
}
