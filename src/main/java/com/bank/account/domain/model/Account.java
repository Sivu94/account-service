package com.bank.account.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "accounts")
public class Account extends PanacheEntityBase {
    @Id
    @GeneratedValue
    private String accountId;
    private String accountNumber;

    private String userId;

    private String firstName;

    private String surname;

    private String address;

    public Account() {}

    public Account(String accountId, String accountNumber, String userId, String firstName, String surname, String address) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
    }
    public String getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSurname() {
        return surname;
    }
    public String getAddress() {
        return address;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
