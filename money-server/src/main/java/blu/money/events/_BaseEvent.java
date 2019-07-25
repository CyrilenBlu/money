package blu.money.events;

import lombok.Getter;

@Getter
public class _BaseEvent<T> {

    private final T id;

    public _BaseEvent(T id) {
        this.id = id;
    }
}
