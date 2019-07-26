package blu.money.commands;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
public class _BaseCommand<T> {

    @TargetAggregateIdentifier
    private final T id;

    public _BaseCommand(T id) {
        this.id = id;
    }
}
