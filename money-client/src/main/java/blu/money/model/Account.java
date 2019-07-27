package blu.money.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Account {
    private final String id;
    private final String accountHolder;
    private double accountBalance;
    private String currency;
    private String status;
}
