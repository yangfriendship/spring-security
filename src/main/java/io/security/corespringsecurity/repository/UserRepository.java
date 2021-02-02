package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<Account, Long> {

}
