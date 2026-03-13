package com.bank.account.adapters.outbound.messaging;

import com.bank.account.application.dto.AccountEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Producer for publishing account events to Kafka.
 * Publishes events to the 'account-events-out' channel.
 */
@ApplicationScoped
public class AccountEventProducer {

    private static final Logger LOG = Logger.getLogger(AccountEventProducer.class);

    @Inject
    @Channel("account-events-out")
    Emitter<String> eventEmitter;

    @Inject
    ObjectMapper objectMapper;

    /**
     * Publish an account event to Kafka
     *
     * @param accountEventDto the event to publish
     */
    public void publishAccountEvent(AccountEventDto accountEventDto) {
        try {
            // Generate an event ID if not already set
            if (accountEventDto.getEventId() == null) {
                accountEventDto.setEventId(UUID.randomUUID().toString());
            }

            // Set timestamp if not already set
            if (accountEventDto.getTimestamp() == null) {
                accountEventDto.setTimestamp(LocalDateTime.now());
            }

            // Set source if not already set
            if (accountEventDto.getSource() == null) {
                accountEventDto.setSource("account-service");
            }

            // Serialize the event to JSON
            String eventJson = objectMapper.writeValueAsString(accountEventDto);

            LOG.infof("Publishing account event - Type: %s, AccountId: %s, EventId: %s",
                    accountEventDto.getEventType(),
                    accountEventDto.getAccountId(),
                    accountEventDto.getEventId());

            // Send the event to Kafka
            eventEmitter.send(eventJson);

            LOG.infof("Successfully published event: %s", accountEventDto.getEventId());

        } catch (Exception e) {
            LOG.errorf(e, "Error publishing account event for account: %s",
                    accountEventDto.getAccountId());
            throw new RuntimeException("Failed to publish account event", e);
        }
    }

    /**
     * Convenience method to publish account created event
     */
    public void publishAccountCreated(String accountId, String accountNumber, String details) {
        AccountEventDto event = new AccountEventDto(
                UUID.randomUUID().toString(),
                "ACCOUNT_CREATED",
                accountId,
                accountNumber,
                details,
                LocalDateTime.now(),
                "account-service"
        );
        publishAccountEvent(event);
    }

    /**
     * Convenience method to publish account updated event
     */
    public void publishAccountUpdated(String accountId, String accountNumber, String details) {
        AccountEventDto event = new AccountEventDto(
                UUID.randomUUID().toString(),
                "ACCOUNT_UPDATED",
                accountId,
                accountNumber,
                details,
                LocalDateTime.now(),
                "account-service"
        );
        publishAccountEvent(event);
    }

    /**
     * Convenience method to publish account deleted event
     */
    public void publishAccountDeleted(String accountId, String accountNumber, String details) {
        AccountEventDto event = new AccountEventDto(
                UUID.randomUUID().toString(),
                "ACCOUNT_DELETED",
                accountId,
                accountNumber,
                details,
                LocalDateTime.now(),
                "account-service"
        );
        publishAccountEvent(event);
    }

    /**
     * Convenience method to publish account activated event
     */
    public void publishAccountActivated(String accountId, String accountNumber, String details) {
        AccountEventDto event = new AccountEventDto(
                UUID.randomUUID().toString(),
                "ACCOUNT_ACTIVATED",
                accountId,
                accountNumber,
                details,
                LocalDateTime.now(),
                "account-service"
        );
        publishAccountEvent(event);
    }

    /**
     * Convenience method to publish account deactivated event
     */
    public void publishAccountDeactivated(String accountId, String accountNumber, String details) {
        AccountEventDto event = new AccountEventDto(
                UUID.randomUUID().toString(),
                "ACCOUNT_DEACTIVATED",
                accountId,
                accountNumber,
                details,
                LocalDateTime.now(),
                "account-service"
        );
        publishAccountEvent(event);
    }
}

