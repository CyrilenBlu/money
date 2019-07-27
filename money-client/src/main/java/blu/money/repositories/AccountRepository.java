package blu.money.repositories;

import blu.money.aggregates.AccountAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountAggregate, String> {
}
