package io.security.corespringsecurity.security.service;

import io.security.corespringsecurity.domain.entity.Account;
import java.util.ArrayList;
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
}
