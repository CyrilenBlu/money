package blu.money.events;

import lombok.Getter;

@Getter
public class CreateAccountEvent extends _BaseEvent<String> {

    private final String accountHolder;

    private final double accountBalance;

    private final String currency;

    private final String status;

    public CreateAccountEvent(String id, String accountHolder, double accountBalance, String currency, String status) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = status;
    }
}
