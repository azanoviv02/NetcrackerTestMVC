package com.hermes.core.infrastructure.dataaccess.services;


import com.hermes.core.domain.accounts.BasicAccount;
import com.hermes.core.infrastructure.dataaccess.repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends GenericServiceImpl<BasicAccount> implements AccountService{

    @Autowired
    public AccountServiceImpl(GenericRepository<BasicAccount> repository) {
        super(repository);
    }
}