package blu.money.commands;

import lombok.Getter;

@Getter
public class ActivateAccountCommand extends _BaseCommand<String> {

    public ActivateAccountCommand(String id) {
        super(id);
    }
}
