package pl.benzo.enzo.server.api.service.logic;

import pl.benzo.enzo.server.api.model.dto.AccountBuilder;

public interface AccountService {
    AccountBuilder create(AccountBuilder accountBuilder);
    AccountBuilder loggIn(AccountBuilder accountBuilder);
}
