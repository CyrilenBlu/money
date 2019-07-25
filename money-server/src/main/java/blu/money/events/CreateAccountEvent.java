package blu.money.events;

import lombok.Getter;

@Getter
public class CreateAccountEvent extends _BaseEvent<String> {

    private final String accountHolder;

    private final double accountBalance;

    private final String currency;

    public CreateAccountEvent(String id, String accountHolder, double accountBalance, String currency) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
