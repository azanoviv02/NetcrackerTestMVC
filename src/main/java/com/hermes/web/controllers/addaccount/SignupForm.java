package com.hermes.web.controllers.addaccount;

import com.hermes.core.domain.accounts.Role;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotNull
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String login;

    @NotNull
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;

    @NotNull
	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String name;

    @NotNull
	private Role role;

    public SignupForm() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
