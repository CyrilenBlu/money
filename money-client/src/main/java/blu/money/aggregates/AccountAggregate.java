package blu.money.aggregates;

import blu.money.commands.*;
import blu.money.enums.Status;
import blu.money.events.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Aggregate
@Entity
@Slf4j
@Getter
public class AccountAggregate {

    @AggregateIdentifier
    @Id
    private String id;

    private String accountHolder;

    private double accountBalance;

    private String currency;

    private String status;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(new CreateAccountEvent(command.getId(),
                command.getAccountHolder(),
                command.getAccountBalance(),
                command.getCurrency(),
                command.getStatus()));
    }

    @EventSourcingHandler
    protected void on(CreateAccountEvent event) {
        this.id = event.getId();
        this.accountHolder = event.getAccountHolder();
        this.accountBalance = event.getAccountBalance();
        this.currency = event.getCurrency();
        this.status = String.valueOf(Status.CREATED);

//        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
    }

    @CommandHandler
    protected void on(ActivateAccountCommand command) {
        AggregateLifecycle.apply(new AccountActivatedEvent(command.getId(), Status.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent event) {
        this.status = String.valueOf(Status.ACTIVATED);
    }

    @CommandHandler
    protected void on(CreditMoneyCommand command) {
        AggregateLifecycle.apply(new MoneyCreditedEvent(command.getId(),
                command.getAccountHolder(),
                command.getCreditAmount(),
                command.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent event) {
        if (this.accountBalance < 0 && (this.accountBalance + event.getCreditAmount() >= 0)) {
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
        }

        this.accountBalance += event.getCreditAmount();
    }

    @CommandHandler
    protected void on(DebitMoneyCommand command) {
        AggregateLifecycle.apply(new MoneyDebitedEvent(command.getId(),
                command.getAccountHolder(),
                command.getCurrency(),
                command.getDebitAmount()));
    }

    @EventSourcingHandler
    protected void on(MoneyDebitedEvent event) {
        if (this.accountBalance >= 0 && (this.accountBalance - event.getDebitAmount() < 0)) {
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
        }

        this.accountBalance -= event.getDebitAmount();
    }

    @EventSourcingHandler
    protected void on(AccountHeldEvent event) {
        this.status = String.valueOf(event.getStatus());
    }

    @CommandHandler
    protected void on(ChangeAccountHolderCommand command) {
        AggregateLifecycle.apply(new ChangeAccountHolderEvent(command.getId(), command.getAccountHolder()));
    }

    @EventSourcingHandler
    protected void on(ChangeAccountHolderEvent event) {
        if (event.getAccountHolder() == null || event.getAccountHolder().equals("")) {
            log.warn("NULL or BLANK account holder!");
            this.accountHolder = "Contact Administrator!";
        }
        this.accountHolder = event.getAccountHolder();
    }
}
