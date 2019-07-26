package blu.money.commands;

import blu.money.enums.Status;
import lombok.Data;

@Data
public class CreateAccountCommand extends _BaseCommand<String> {

    private final String accountHolder;

    private final double accountBalance;

    private final String currency;

    private final String status;

    public CreateAccountCommand(String id, String accountHolder, double accountBalance, String currency) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = String.valueOf(Status.CREATED);
    }
}
