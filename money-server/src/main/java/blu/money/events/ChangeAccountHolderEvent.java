package blu.money.events;

import lombok.Getter;

@Getter
public class ChangeAccountHolderEvent extends _BaseEvent<String> {

    private final String accountHolder;

    public ChangeAccountHolderEvent(String id, String accountHolder) {
        super(id);
        this.accountHolder = accountHolder;
    }
}
