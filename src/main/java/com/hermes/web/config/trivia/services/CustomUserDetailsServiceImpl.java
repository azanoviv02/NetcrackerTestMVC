package com.hermes.web.config.trivia.services;

import com.hermes.core.domain.accounts.BasicAccount;
import com.hermes.core.domain.accounts.Role;
import com.hermes.core.infrastructure.dataaccess.services.AccountService;
import com.hermes.core.infrastructure.dataaccess.specifications.accounts.AccountWhich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 08.11.16.
 */
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountWhich accountWhich;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException  {

        System.out.println("We got here!");

        try {
            System.out.println("Login: "+s);
            BasicAccount account = accountService.getOne(accountWhich.hasLogin(s));
            System.out.println("Found: "+s);
//            List<BasicAccount> allAccounts = accountService.getAll();
//            BasicAccount account = null;

//            List<AbstractEmployee> allEmployees = employeeService.getAll();
//
//            if(true) {
//                if (allEmployees == null) {
//                    throw new IllegalStateException("Haha, allEmployees empty");
//                } else {
//                    throw new IllegalArgumentException("All employees: " + allEmployees.size());
//                }
//            }

//            if(true) {
//                if (allAccounts == null) {
//                    throw new IllegalStateException("Haha, allAccounts empty");
//                } else {
//                    throw new IllegalArgumentException("All accounts: " + allAccounts.size());
//                }
//            }

//            for(BasicAccount oneAccount : allAccounts){
//                if(oneAccount.getLogin().equals(s)){
//                    account = oneAccount;
//                    break;
//                }
//            }

            if(account == null){
                throw new IllegalStateException("Haha gaga");
            }

            String username = account.getLogin();
            String password = account.getPassword();
            Role role = account.getRole();

            List<SimpleGrantedAuthority> authList = getAuthorities(role);
            String encodedPassword = passwordEncoder.encode(password);

            User user = new User(username, encodedPassword, authList);

            System.out.println("Finish security!");
            return user;
        }catch (NoResultException e){
            System.out.println("Login not found: "+s);
            throw e;
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(Role role) {

        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        switch(role){
            case ADMIN:
                authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case DRIVER:
            case MANAGER:
            case PLANNER:
            case INFORMER:
                break;
            default:
                throw new IllegalStateException();
        }

        return authList;
    }

//    public void signin(Account account) {
//        SecurityContextHolder.getContext().setAuthentication(authenticate(account));
//    }
//
//    private Authentication authenticate(Account account) {
//        return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
//    }
//
//    private User createUser(Account account) {
//        return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
//    }
//
//    private GrantedAuthority createAuthority(Account account) {
//        return new SimpleGrantedAuthority(account.getRole());
//    }
}
