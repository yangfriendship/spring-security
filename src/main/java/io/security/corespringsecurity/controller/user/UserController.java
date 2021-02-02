package io.security.corespringsecurity.controller.user;


import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.domain.AccountDto;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

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
        RedirectAttributes redirectAttributes, Model model) {
        System.out.println("join Post");
        if (errors.hasErrors()) {
            System.out.println("join Post error");
            model.addAttribute(accountDto);
            return "user/login/register";
        }
        Account account = modelMapper.map(accountDto, Account.class);
        userService.createUser(account);

        return "redirect:/";
    }
}
