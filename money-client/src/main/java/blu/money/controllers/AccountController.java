package blu.money.controllers;

import blu.money.commands.ActivateAccountCommand;
import blu.money.commands.CreateAccountCommand;
import blu.money.commands.CreditMoneyCommand;
import blu.money.commands.DebitMoneyCommand;
import blu.money.model.Account;
import blu.money.queries.FindAccountByIdQuery;
import blu.money.queries.FindAllAccountsQuery;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
public class AccountController {

    private final EurekaClient eurekaClient;
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping("/accounts")
    public List<Account> findAllAccounts() {
        return queryGateway.query(new FindAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
    }

    @GetMapping("/account/{id}/view")
    public Account findAccount(@PathVariable String id) throws ExecutionException, InterruptedException {
        return queryGateway.query(new FindAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).get();
    }

    @PostMapping("/account/{holder}/add")
    public void createAccount(@PathVariable("holder") String accountHolder) {
        String uuid = UUID.randomUUID().toString();
        commandGateway.send(new CreateAccountCommand(uuid, accountHolder, 0, "R"));
    }

    @PatchMapping("/account/{id}/activate")
    public void activateAccount(@PathVariable("id") String id) {
        commandGateway.send(new ActivateAccountCommand(id));
    }

    @PatchMapping("/account/{id}/credit")
    public void creditAccount(@PathVariable("id") String id, @RequestHeader("amount") String amount) throws ExecutionException, InterruptedException {
        commandGateway.send(new CreditMoneyCommand(id, findAccount(id).getAccountHolder(), Double.parseDouble(amount),"R"));
    }

    @PatchMapping("/account/{id}/debit")
    public void debitAccount(@PathVariable("id") String id, @RequestHeader("amount") String amount) throws ExecutionException, InterruptedException {
        commandGateway.send(new DebitMoneyCommand(id, findAccount(id).getAccountHolder(), Double.parseDouble(amount), "R"));
    }
}
