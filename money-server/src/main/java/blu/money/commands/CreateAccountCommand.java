package blu.money.commands;

public class CreateAccountCommand extends _BaseCommand<String> {

    private final String accountHolder;

    private final double accountBalance;

    private final String currency;

    public CreateAccountCommand(String id, String accountHolder, double accountBalance, String currency) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
