package io.security.corespringsecurity.controller.user;


import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.domain.AccountDto;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationProvider authenticationProvider;

    @GetMapping(value = "/mypage")
    public String myPage() throws Exception {

        return "user/mypage";
    }

    @GetMapping("/join")
    public String joinForm() {
        System.out.println("join Get");

        return "user/login/register";
    }

    @PostMapping("/join")
    public String joinFormSubmit(AccountDto accountDto, Errors errors,
        Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(accountDto);
            return "user/login/register";
        }
        Account account = modelMapper.map(accountDto, Account.class);
        Account savedAccount = userService.createUser(account);


        return "redirect:/login";
    }
}
