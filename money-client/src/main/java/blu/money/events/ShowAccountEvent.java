package blu.money.events;

import lombok.Getter;

@Getter
public class ShowAccountEvent extends _BaseEvent<String> {
    private final String accountHolder;
    private final double accountBalance;
    private final String status;

    public ShowAccountEvent(String id, String accountHolder, double accountBalance, String status) {
        super(id);
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
        this.status = status;
    }
}
