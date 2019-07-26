package blu.money.events;

import lombok.Getter;

@Getter
public class MoneyCreditedEvent extends _BaseEvent<String> {
    //no u

    private final String accountHolder;

    private final double creditAmount;

    private final String currency;

    public MoneyCreditedEvent(String id, String accountHolder, double creditAmount, String currency) {
        super(id);
        this.accountHolder = accountHolder;
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
