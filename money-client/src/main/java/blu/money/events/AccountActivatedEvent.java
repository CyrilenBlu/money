package blu.money.events;

import blu.money.enums.Status;
import lombok.Getter;

@Getter
public class AccountActivatedEvent extends _BaseEvent<String> {

    private final Status status;

    public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
