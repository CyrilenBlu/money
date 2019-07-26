package blu.money.commands;

import lombok.Getter;

@Getter
public class ShowAccountCommand extends _BaseCommand<String> {

    private final String accountHolder;
    private final double accountBalance;
    private final String status;

    public ShowAccountCommand(String id, String accountHolder, double accountBalance, String status) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
        this.status = status;
    }
}
