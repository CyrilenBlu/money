package blu.money.events;

import lombok.Getter;

@Getter
public class MoneyDebitedEvent extends _BaseEvent<String> {

    private final String accountHolder;

    private final String currency;

    private final double debitAmount;

    public MoneyDebitedEvent(String id, String accountHolder, String currency, double debitAmount) {
        super(id);
        this.accountHolder = accountHolder;
        this.currency = currency;
        this.debitAmount = debitAmount;
    }
}
