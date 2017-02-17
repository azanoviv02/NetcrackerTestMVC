package com.hermes.core.infrastructure.dataaccess.services;

import com.hermes.core.domain.accounts.BasicAccount;
import com.hermes.core.infrastructure.dataaccess.repositories.GenericRepository;
import com.hermes.core.infrastructure.dataaccess.repositories.GenericRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ivan on 08.11.16.
 */
@Configuration
public class RepositoryConfig {

    @Autowired
    SessionFactory sessionFactory;

    @Bean(name = "accountRepository")
    public GenericRepository<BasicAccount> accountRepository(){
        return new GenericRepositoryImpl<>(BasicAccount.class, sessionFactory);
    }
}
