package io.security.corespringsecurity.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_URL = "/login?error=true&exception=";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "Invalid Username Or Password";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid Username Or Password";
        }
        if (exception instanceof InsufficientAuthenticationException) {
            errorMessage = "Invalid Secret Key";
        }
        System.out.println(DEFAULT_FAILURE_URL + errorMessage);
        setDefaultFailureUrl(DEFAULT_FAILURE_URL + errorMessage);

        super.onAuthenticationFailure(request, response, exception);

    }
}
