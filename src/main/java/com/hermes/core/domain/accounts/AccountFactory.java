package com.hermes.core.domain.accounts;

/**
 * Created by ivan on 05.11.16.
 */
public interface AccountFactory {
    BasicAccount createAccount(String login, String password, String name, Role role)
            throws LoginAlreadyTakenException, NoSuchRoleException;
}
