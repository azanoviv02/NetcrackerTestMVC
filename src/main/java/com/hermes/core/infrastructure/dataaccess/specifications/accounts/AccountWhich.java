package com.hermes.core.infrastructure.dataaccess.specifications.accounts;


import com.hermes.core.domain.accounts.BasicAccount;
import com.hermes.core.domain.accounts.Role;
import com.hermes.core.infrastructure.dataaccess.specifications.AbstractWhich;
import com.hermes.core.infrastructure.dataaccess.specifications.Specification;
import org.springframework.stereotype.Component;

/**
 * Created by ivan on 02.11.16.
 */
@Component
public class AccountWhich extends AbstractWhich<BasicAccount> {

    public AccountWhich() {
    }

    public Specification<BasicAccount> hasLogin(String login){
        return new HasLogin(login);
    }

    public Specification<BasicAccount> hasRole(Role role){
        return new HasRole(role);
    }

    public Specification<BasicAccount> isAdmin(){
        return new HasRole(Role.ADMIN);
    }

    public Specification<BasicAccount> isDriver(){
        return new HasRole(Role.DRIVER);
    }

    public Specification<BasicAccount> isManager(){
        return new HasRole(Role.MANAGER);
    }

    public Specification<BasicAccount> isPlanner(){
        return new HasRole(Role.PLANNER);
    }

    public Specification<BasicAccount> isInformer(){
        return new HasRole(Role.INFORMER);
    }
}
