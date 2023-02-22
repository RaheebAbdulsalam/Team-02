package LoginSystem.com.configration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Add a cookie with the user's username
        String username = authentication.getName();
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);

        // Redirect the user to the home page
        response.sendRedirect(request.getContextPath() + "/");
    }
}
