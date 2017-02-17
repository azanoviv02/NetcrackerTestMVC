package com.hermes.core.infrastructure.dataaccess.specifications.accounts;


import com.hermes.core.domain.accounts.BasicAccount;
import com.hermes.core.infrastructure.dataaccess.specifications.AbstractSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by ivan on 02.11.16.
 */
public class HasLogin extends AbstractSpecification<BasicAccount> {

    private String login;

    HasLogin(String login) {
        this.login = login;
    }

    public boolean isSatisfiedBy(BasicAccount account) {
        return account.getLogin() == this.login;
    }

    public Criterion toCriterion() {
        return Restrictions.eq("login", this.login);
    }
}
