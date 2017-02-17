package com.hermes.core.domain.accounts;


import com.hermes.core.infrastructure.dataaccess.services.AccountService;
import com.hermes.core.infrastructure.dataaccess.specifications.accounts.AccountWhich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ivan on 02.11.16.
 */
@Component
public class AccountFactoryImpl implements AccountFactory {

    private final AccountService accountService;
    private final AccountWhich accountWhich;

    @Autowired
    public AccountFactoryImpl(AccountService accountService, AccountWhich accountWhich) {
        this.accountService = accountService;
        this.accountWhich = accountWhich;
    }

    @Override
    public BasicAccount createAccount(String login, String password, String name, Role role)
            throws LoginAlreadyTakenException{

        List<BasicAccount> possibleCollisions = accountService.getEvery(accountWhich.hasLogin(login));

        if(possibleCollisions.isEmpty() == false){
            throw new LoginAlreadyTakenException();
        }

        switch (role){
            case ADMIN:
            case MANAGER:
            case PLANNER:
            case INFORMER:
            case DRIVER:
                return new BasicAccount(login, password, name, role);
            default:
                throw new IllegalStateException();
        }
    }
}
