package com.bank.account.adapters.outbound.persistence;

import com.bank.account.domain.model.Account;
import com.bank.account.domain.repository.AccountRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

/**
 * JPA implementation of AccountRepository using Panache.
 */
@ApplicationScoped
public class AccountRepositoryImpl implements AccountRepository, PanacheRepository<Account> {

    @Override
    public Account save(Account account) {
        persist(account);
        return account;
    }

    @Override
    public Optional<Account> findByUserId(String userId) {
        return find("userId", userId).firstResultOptional();
    }

    @Override
    public Optional<Account> findById(String accountId) {
        return findByIdOptional(Long.valueOf(accountId));
    }

    @Override
    public boolean existsByUserId(String userId) {
        return count("userId", userId) > 0;
    }
}
