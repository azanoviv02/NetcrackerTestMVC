package com.hermes.core.domain.accounts;

import com.hermes.core.domain.AbstractPersistentObject;

import javax.persistence.*;

@Entity
@Table(name="ACCOUNTS", uniqueConstraints = {@UniqueConstraint(columnNames = "LOGIN"),})
public class BasicAccount extends AbstractPersistentObject {

    @Column(name="LOGIN", nullable = false, unique = true)
    protected String login;

    @Column(name="PASSWORD", nullable = false)
    protected String password;

    @Column(name="NAME", nullable = false)
    protected String name;

    @Enumerated(EnumType.STRING)
    @Column(name="ROLE", nullable = false)
    protected Role role;

    BasicAccount() {
    }

    BasicAccount(String login, String password, String name, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(login == null){
            throw new IllegalArgumentException("Can't be null");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null){
            throw new IllegalArgumentException("Can't be null");
        }
        this.password = password;
    }

    public boolean checkPassword(String login, String password) {
        if(this.login == login && this.password == password){
            return true;
        }else{
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null){
            throw new IllegalArgumentException("Can't be null");
        }
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if(role == null){
            throw new IllegalArgumentException("Can't be null");
        }
        this.role = role;
    }

    public void checkPassword(String password) throws InvalidPasswordException{
        if(this.getPassword().equals(password) == false){
            throw new InvalidPasswordException();
        }
    }

    @Override
    public String toString() {
        return "BasicAccount{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
