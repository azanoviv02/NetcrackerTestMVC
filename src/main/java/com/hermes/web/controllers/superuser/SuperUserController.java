package com.hermes.web.controllers.superuser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuperUserController {

    private static final String SUPERUSER_VIEW_NAME = "superuser/superuser";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String superUserPage() {
        return SUPERUSER_VIEW_NAME;
    }
}
