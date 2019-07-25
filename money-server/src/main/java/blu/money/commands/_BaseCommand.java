package blu.money.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;


public class _BaseCommand<T> {

    @TargetAggregateIdentifier
    private final T id;

    public _BaseCommand(T id) {
        this.id = id;
    }
}
