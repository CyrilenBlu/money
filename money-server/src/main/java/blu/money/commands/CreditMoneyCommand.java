package blu.money.commands;

public class CreditMoneyCommand extends _BaseCommand<String> {

    private final String accountHolder;

    private final double creditAmount;

    private final String currency;

    public CreditMoneyCommand(String id, String accountHolder, double creditAmount, String currency) {
        super(id);
        this.accountHolder = accountHolder;
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
