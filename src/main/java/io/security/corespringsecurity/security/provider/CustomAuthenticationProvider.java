package io.security.corespringsecurity.security.provider;

import io.security.corespringsecurity.security.service.AccountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;


    /*
     * 파라미터로 전달받는 Authentication은 폼에서 들어온 username과 password만 들어있다.
     * */
    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {

        String username = authentication.getName();
        String rawPassword = (String) authentication.getPrincipal();

        AccountContext accountContext = (AccountContext) userDetailsService
            .loadUserByUsername(username);
        if (!passwordEncoder.matches(rawPassword, accountContext.getPassword())) {
            throw new BadCredentialsException("인증 실패");
        }

        return new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null,
            accountContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
