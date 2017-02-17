package com.hermes.web.controllers.viewtables;


import com.hermes.core.infrastructure.dataaccess.services.*;
import com.hermes.core.infrastructure.dataaccess.specifications.accounts.AccountWhich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewTablesController {

    private static final String ACCOUNTS_VIEW_NAME = "tables/accounts";

    @Autowired
    AccountService accountService;
    @Autowired
    AccountWhich accountWhich;

    @RequestMapping(value = "accounts")
    public String viewUsers(Model model) {
        model.addAttribute("admins", accountService.getEvery(accountWhich.isAdmin()));
        model.addAttribute("drivers", accountService.getEvery(accountWhich.isDriver()));
        model.addAttribute("managers", accountService.getEvery(accountWhich.isManager()));
        model.addAttribute("planners", accountService.getEvery(accountWhich.isPlanner()));
        model.addAttribute("informers", accountService.getEvery(accountWhich.isInformer()));
        return ACCOUNTS_VIEW_NAME;
    }
}
