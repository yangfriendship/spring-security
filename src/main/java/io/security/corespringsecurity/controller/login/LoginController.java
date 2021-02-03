package io.security.corespringsecurity.controller.login;

import io.security.corespringsecurity.domain.Account;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping(value = {"/login", "/api/login"})
    public String loginForm(Model model,
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "exception", required = false) String exception) {

        if (error != null && exception != null) {
            model.addAttribute("error", error);
            model.addAttribute("exception", exception);
        }

        return "user/login/login";
    }

    @GetMapping("/logout")
    public String logoutForm(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

    @GetMapping("/denied")
    public String accessDenied(String errorPage, Model model,
        @RequestParam(value = "exception", required = false) String exception) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        model.addAttribute("username", account.getUsername());
        model.addAttribute("exception", exception);

        return "user/login/denied";

    }

}
