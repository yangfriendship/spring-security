package io.security.corespringsecurity.security.service;

import io.security.corespringsecurity.domain.entity.Account;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Data
public class AccountContext extends User {

    private Account account;

    public AccountContext(Account account, ArrayList<GrantedAuthority> roles) {
        super(account.getUsername(), account.getPassword(), roles);
        this.account = account;
    }

    public AccountContext(Account account, List<GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), authorities);
        this.account = account;
    }
}
