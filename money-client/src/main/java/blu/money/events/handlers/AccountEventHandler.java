package blu.money.events.handlers;

import blu.money.enums.Status;
import blu.money.events.AccountActivatedEvent;
import blu.money.events.CreateAccountEvent;
import blu.money.events.MoneyCreditedEvent;
import blu.money.events.MoneyDebitedEvent;
import blu.money.model.Account;
import blu.money.queries.FindAccountByIdQuery;
import blu.money.queries.FindAllAccountsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AccountEventHandler {
    private final Map<String, Account> accounts = new HashMap<>();
    private Account queryAccount = null;

    @EventHandler
    public void on(CreateAccountEvent event) {
        String userId = event.getId();
        accounts.put(userId, new Account(userId, event.getAccountHolder(), event.getAccountBalance(), event.getStatus()));
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        accounts.computeIfPresent(event.getId(), (userId, account) -> {
            account.setStatus(String.valueOf(Status.ACTIVATED));
            return account;
        });
    }

    @EventHandler
    public void on(MoneyCreditedEvent event) {
        accounts.computeIfPresent(event.getId(), (userId, account) -> {
            account.setAccountBalance(account.getAccountBalance() + event.getCreditAmount());
            return account;
        });
    }

    @EventHandler
    public void on(MoneyDebitedEvent event) {
        accounts.computeIfPresent(event.getId(), (userId, account) -> {
            account.setAccountBalance(account.getAccountBalance() - event.getDebitAmount());
            return account;
        });
    }

    @QueryHandler
    public List<Account> handle(FindAllAccountsQuery query) {
        return new ArrayList<>(accounts.values());
    }

    @QueryHandler
    public Account handle(FindAccountByIdQuery query) {
        accounts.forEach((s, account) -> {
            if (account.getId().equals(query.getId())) {
                queryAccount = new Account(account.getId(), account.getAccountHolder(), account.getAccountBalance(), account.getStatus());
            }
        });
        if (queryAccount == null) {
            log.warn("Id not found!");
            throw new RuntimeException("Id not found!");
        }

        return queryAccount;
    }
}
