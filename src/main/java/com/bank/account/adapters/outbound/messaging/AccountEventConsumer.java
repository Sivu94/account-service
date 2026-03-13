package com.bank.account.adapters.outbound.messaging;

import com.bank.account.application.dto.AccountEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

/**
 * Consumer for incoming account events from Kafka.
 * Listens to 'account-events-in' channel and processes account events.
 */
@ApplicationScoped
public class AccountEventConsumer {

    private static final Logger LOG = Logger.getLogger(AccountEventConsumer.class);

    @Inject
    ObjectMapper objectMapper;


    /**
     * Process incoming account events from Kafka.
     * This method is triggered whenever a message arrives on the 'account-events-in' topic.
     *
     * @param eventMessage the event message as JSON string
     */
    @Incoming("account-events-in")
    public void consumeAccountEvent(String eventMessage) {
        try {
            LOG.infof("Received account event: %s", eventMessage);

            // Deserialize the JSON message to AccountEventDto
            AccountEventDto event = objectMapper.readValue(eventMessage, AccountEventDto.class);

            LOG.infof("Processing event - Type: %s, AccountId: %s, EventId: %s",
                    event.getEventType(), event.getAccountId(), event.getEventId());

            // Process the event based on its type
            processEventByType(event);

            LOG.infof("Successfully processed event: %s", event.getEventId());

        } catch (Exception e) {
            LOG.errorf(e, "Error processing account event: %s", eventMessage);
            // Consider implementing a dead-letter queue or retry mechanism here
        }
    }

    /**
     * Route event processing based on event type
     */
    private void processEventByType(AccountEventDto event) {
        switch (event.getEventType()) {
            case "ACCOUNT_CREATED":
                handleAccountCreated(event);
                break;
            case "ACCOUNT_UPDATED":
                handleAccountUpdated(event);
                break;
            case "ACCOUNT_DELETED":
                handleAccountDeleted(event);
                break;
            case "ACCOUNT_ACTIVATED":
                handleAccountActivated(event);
                break;
            case "ACCOUNT_DEACTIVATED":
                handleAccountDeactivated(event);
                break;
            default:
                LOG.warnf("Unknown event type: %s", event.getEventType());
        }
    }

    private void handleAccountCreated(AccountEventDto event) {
        LOG.infof("Handling ACCOUNT_CREATED event for account: %s", event.getAccountNumber());
        // Implement your business logic here
        // Example: Update account status, trigger downstream processes, etc.
    }

    private void handleAccountUpdated(AccountEventDto event) {
        LOG.infof("Handling ACCOUNT_UPDATED event for account: %s", event.getAccountNumber());
        // Implement your business logic here
    }

    private void handleAccountDeleted(AccountEventDto event) {
        LOG.infof("Handling ACCOUNT_DELETED event for account: %s", event.getAccountNumber());
        // Implement your business logic here
    }

    private void handleAccountActivated(AccountEventDto event) {
        LOG.infof("Handling ACCOUNT_ACTIVATED event for account: %s", event.getAccountNumber());
        // Implement your business logic here
    }

    private void handleAccountDeactivated(AccountEventDto event) {
        LOG.infof("Handling ACCOUNT_DEACTIVATED event for account: %s", event.getAccountNumber());
        // Implement your business logic here
    }
}

