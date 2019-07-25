package blu.money.commands;

public class DebitMoneyCommand extends _BaseCommand<String> {

    private final String accountHolder;

    private final double debitAmount;

    private final String currency;

    public DebitMoneyCommand(String id, String accountHolder, double debitAmount, String currency) {
        super(id);
        this.accountHolder = accountHolder;
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
