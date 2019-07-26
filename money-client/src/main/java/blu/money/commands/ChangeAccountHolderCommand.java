package blu.money.commands;

import lombok.Getter;

@Getter
public class ChangeAccountHolderCommand extends _BaseCommand<String> {

    private final String accountHolder;

    public ChangeAccountHolderCommand(String id, String accountHolder) {
        super(id);
        this.accountHolder = accountHolder;
    }
}
