package com.bank.account.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * DTO for Account events that are published/consumed via Kafka
 */
public class AccountEventDto {

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("eventType")
    private String eventType;

    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("eventDetails")
    private String eventDetails;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("source")
    private String source;

    // Constructors
    public AccountEventDto() {}

    public AccountEventDto(String eventId, String eventType, String accountId, 
                          String accountNumber, String eventDetails, 
                          LocalDateTime timestamp, String source) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.eventDetails = eventDetails;
        this.timestamp = timestamp;
        this.source = source;
    }

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "AccountEventDto{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", eventDetails='" + eventDetails + '\'' +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                '}';
    }
}

